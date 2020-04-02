package org.aiwolf.ui.bin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Team;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.common.net.TcpipClient;
import org.aiwolf.common.util.Counter;
import org.aiwolf.common.util.Pair;
import org.aiwolf.server.AIWolfGame;
import org.aiwolf.server.GameData;
import org.aiwolf.server.LostClientException;
import org.aiwolf.server.net.ServerListener;
import org.aiwolf.server.net.TcpipServer;
import org.aiwolf.server.util.FileGameLogger;
import org.aiwolf.server.util.GameLogger;
import org.aiwolf.server.util.MultiGameLogger;
import org.aiwolf.ui.GameViewer;
import org.aiwolf.ui.HumanPlayer;
import org.aiwolf.ui.log.ContestResource;
import org.aiwolf.ui.res.AIWolfResource;
import org.aiwolf.ui.res.DefaultResource;
import org.aiwolf.ui.res.JapaneseResource;
import org.aiwolf.ui.util.AgentLibraryReader;


/**
 * Start Server and Client from init file
 * @author tori
 *
 */
public class AutoStarter {

	private Map<String, Pair<String, Role>> roleAgentMap;
	private File libraryDir;
	
	int agentNum = -1;
	int port = 10000;
	int gameNum = 100;
	String logDirName ="./log/";
	
	private TcpipServer gameServer;
	
	private String settingFileName; 
	private GameSetting gameSetting;
	boolean isRunning;
	boolean isSuccessToFinish;
	Thread serverThread;
	boolean isVisualize = false;
	
	boolean initServer=false;
	
	Map<String, Counter<Role>> winCounterMap;
	Map<String, Counter<Role>> roleCounterMap;
	private Map<String, Class> playerClassMap;
	AIWolfResource resource;
	
	
	/**
	 * Start Human Agent Starter
	 * 
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		if(args.length == 0){
			System.err.println("Usage:"+AutoStarter.class.getName()+" initFileName");
			return;
		}
		
		AutoStarter ssbi = new AutoStarter(args[0]);
		ssbi.start();
		ssbi.result();
		System.exit(1);
	}
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public AutoStarter(String fileName) throws IOException{

		libraryDir = new File("./");
		roleAgentMap = new HashMap<>();
		File initFile = new File(fileName);
		Path src = initFile.toPath();
		resource = new DefaultResource();
		for(String line:Files.readAllLines(src, Charset.forName("UTF8"))){
			if(line.contains("=")){
				String[] data = line.split("=");
				if(data[0].trim().equals("lib")){
					libraryDir = new File(data[1].trim());
				}
				else if(data[0].trim().equals("log")){
					logDirName = data[1].trim();
				}
				else if(data[0].trim().equals("port")){
					port = Integer.parseInt(data[1].trim());
				}
				else if(data[0].trim().equals("agent")){
					agentNum = Integer.parseInt(data[1].trim());
				}
				else if(data[0].trim().equals("game")){
					gameNum = Integer.parseInt(data[1].trim());
				}
				else if(data[0].trim().equals("view")){
					isVisualize = "true".equals(data[1].trim().toLowerCase());
				}
				else if(data[0].trim().equals("setting")){
					settingFileName = data[1].trim();
				}
				else if(data[0].trim().equals("resource")){
					try {
						resource = (AIWolfResource) Class.forName(data[1].trim()).newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else{
				String[] data = line.split(",");
				if(data.length < 2 || line.startsWith("#")){
					continue;
				}
				String name = data[0];
				String classPath = data[1];
				Role role = null;
				if(data.length >= 3){
					try{
						role = Role.valueOf(data[2]);
					}catch(IllegalArgumentException e){
					}
				}
				roleAgentMap.put(name, new Pair<String, Role>(classPath, role));
			}
		}
		
		if(agentNum < 5){
			agentNum = roleAgentMap.size();
		}

		playerClassMap = getPlayerClassMap(libraryDir);

//		for(String name:roleAgentMap.keySet()){
//			String clsName = roleAgentMap.get(name).getKey();
//			if(!playerClassMap.containsKey(clsName)){
//				throw new IllegalArgumentException("No such agent as "+clsName);
//			}
//		}
		
	}
	
	/**
	 * Start server and client
	 * @throws SocketTimeoutException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public void start() throws SocketTimeoutException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		startServer();
		startClient();

		while(initServer || isRunning){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Start client from init files
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private void startClient() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//		List<String> nameList = new ArrayList<>(roleAgentMap.keySet());
//		Collections.shuffle(nameList);
		for(String playerName:roleAgentMap.keySet()){
			String clsName = roleAgentMap.get(playerName).getKey();
			Role role = roleAgentMap.get(playerName).getValue();
			
			Player player = null;
			Class<? extends Player> playerClass = null;
			if(playerClassMap.containsKey(clsName)){
				playerClass = playerClassMap.get(clsName);
//				player = (Player)playerClassMap.get(clsName).newInstance();
			}
			else{
				System.out.println(clsName);
				playerClass = (Class<Player>) Class.forName(clsName);
//				player = (Player)Class.forName(clsName).newInstance();
			}
			if(playerClass == HumanPlayer.class){
				player = new HumanPlayer(resource);
			}
			else{
				player = (Player)playerClass.newInstance();
			}
			
			//引数にRoleRequestを追加
			TcpipClient client = new TcpipClient("localhost", port, role);
			if(playerName != null){
				client.setName(playerName);
//				System.out.println("Set name "+client.getName());
			}
			if(client.connect(player)){
//				System.out.println("Player connected to server:"+player);
			}

		}
		
	}

	/**
	 * start server
	 * @throws SocketTimeoutException
	 * @throws IOException
	 */
	private void startServer() throws SocketTimeoutException, IOException {
		
		if(settingFileName == null){
			gameSetting = GameSetting.getDefaultGame(agentNum);
		}
		else{
			File file = new File(settingFileName);
			gameSetting = GameSetting.getCustomGame(settingFileName, agentNum);
		}
		gameServer = new TcpipServer(port, agentNum, gameSetting);
		gameServer.addServerListener(new ServerListener() {
			
			@Override
			public void unconnected(Socket socket, Agent agent, String name) {
				
			}
			
			@Override
			public void connected(Socket socket, Agent agent, String name) {
				System.out.println("Connected:"+name);
				
			}
		});
//		gameServer.waitForConnection();
//		
//		AIWolfGame game = new AIWolfGame(gameSetting, gameServer);
//		game.setRand(new Random());
//		if(logDirName != null){
//			game.setLogFile(new File(logDirName));
//		}
//		game.start();
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try{
					gameServer.waitForConnection();
					isRunning = true;
					initServer = false;
					winCounterMap = new HashMap<>();
					roleCounterMap = new HashMap<>();
					for(int i = 0; i < gameNum; i++){
						AIWolfGame game = new AIWolfGame(gameSetting, gameServer);

						game.setRand(new Random(i));
						File logFile = new File(String.format("%s/%03d.log", logDirName, i)); 
						GameLogger logger = new FileGameLogger(logFile);
						if(isVisualize){
//							ContestResource resource = new ContestResource(game);
							JapaneseResource resource = new JapaneseResource();
							for(Agent agent:gameServer.getConnectedAgentList()){
								resource.setName(agent.getAgentIdx(), gameServer.getName(agent));
							}
							GameViewer gameViewer = new GameViewer(resource, game);
//							Map<Agent, Role> agentRoleMap = new HashMap<>();
//							for(Agent agent:game.getGameData().getAgentList()){
//								agentRoleMap.put(agent, game.getGameData().getRole(agent));
//							}
//							GameViewer2 gameViewer = new GameViewer2(resource, gameSetting, agentRoleMap);
							gameViewer.setAutoClose(true);
							logger = new MultiGameLogger(logger, gameViewer);
						}
						game.setGameLogger(logger);
						
						try{
							game.start();
	
							Team winner = game.getWinner();
							GameData gameData = game.getGameData();
							for(Agent agent:gameData.getAgentList()){
								String agentName = game.getAgentName(agent);
								if(!winCounterMap.containsKey(agentName)){
									winCounterMap.put(agentName, new Counter<Role>());
								}
								if(!roleCounterMap.containsKey(agentName)){
									roleCounterMap.put(agentName, new Counter<Role>());
								}

								if(winner == gameData.getRole(agent).getTeam()){
									winCounterMap.get(agentName).add(gameData.getRole(agent));
								}
								roleCounterMap.get(agentName).add(gameData.getRole(agent));
							}
						}catch(LostClientException e){
							Agent agent = e.getAgent();
							String teamName = gameServer.getName(agent);
							System.out.println("Lost:"+teamName);
							logger.flush();
							throw e;
						}
						
					}
					isSuccessToFinish = true;
					gameServer.close();
				}catch(LostClientException e){
					String teamName = gameServer.getName(e.getAgent());
					if(teamName != null){
						System.out.println("Lost connection "+teamName);
					}
					e.printStackTrace();
				} catch(SocketTimeoutException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isRunning = false;
			}

		};

		initServer = true;
		serverThread = new Thread(r);
		serverThread.start();

		while(!gameServer.isWaitForClient() && initServer){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * TODO 外部接続エージェントの成績が表示されない
	 * show results
	 */
	private void result() {
		for(Role role:Role.values()){
			if(role == Role.FREEMASON){
				continue;
			}
			System.out.print("\t"+role);
		}
		System.out.println("\tTotal");
		for(String name:new TreeSet<>(roleAgentMap.keySet())){
			if(!winCounterMap.containsKey(name) || !roleCounterMap.containsKey(name)){
				continue;
			}
			System.out.print(name+"\t");
			double win = 0;
			double cnt = 0;
			for(Role role:Role.values()){
				if(role == Role.FREEMASON){
					continue;
				}
				System.out.printf("%d/%d\t", winCounterMap.get(name).get(role), roleCounterMap.get(name).get(role));
				win += winCounterMap.get(name).get(role);
				cnt += roleCounterMap.get(name).get(role);
			}
			System.out.printf("%.3f\n", win/cnt);
		}

		
	}


	
	/**
	 * ディレクトリ以下のライブラリから
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	private Map<String, Class> getPlayerClassMap(File dir) throws IOException {
		Map<String, Class> playerClassMap = new HashMap<>();
		for(File file:AgentLibraryReader.getJarFileList(dir)){
			for(Class c:AgentLibraryReader.getPlayerClassList(file)){
				playerClassMap.put(c.getName(), c);
			}
		}
		return playerClassMap;
	}


}
