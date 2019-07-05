package org.aiwolf.ui.bin;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
import org.aiwolf.ui.net.TcpipDirectServer;
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

	private Map<String, PlayerInfo> roleAgentMap;
	private File libraryDir;

	int agentNum = -1;
	int port = 10000;
	int gameNum = 100;
	String logDirName ="./log/";

	private TcpipDirectServer gameServer;

	private String settingFileName;
	private GameSetting gameSetting;
	boolean isRunning;
	boolean isSuccessToFinish;
	Thread serverThread;
	boolean isVisualize = false;

	boolean initServer=false;
	boolean isHumanPlayer;

	Map<String, Counter<Role>> winCounterMap;
	Map<String, Counter<Role>> roleCounterMap;

	private Map<String, Class> playerClassMap;
	private Map<String, ProgType> agentTypeMap;
	AIWolfResource resource;

	/**
	 * Path to C# ClientSterter.exe
	 */
	String csharpClientStarterPath;


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
		System.out.println(args[0]);
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
		agentTypeMap = new HashMap<>();

		File initFile = new File(fileName);
		Path src = initFile.toPath();
		resource = new DefaultResource();
		for(String line:Files.readAllLines(src, Charset.forName("UTF8"))){
			if(line.startsWith("#")) {
				continue;
			}
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
				else if(data[0].toUpperCase().trim().equals("C#")){
					csharpClientStarterPath = data[1].trim();
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
				if(data.length < 2){
					continue;
				}
				String name = data[0];
				ProgType progType = null;
				if(data[1].toUpperCase().equals("C#")) {
					progType = ProgType.C_SHARP;
				}
				else {
					progType = ProgType.valueOf(data[1].toUpperCase());
				}
				String target = data[2];
				Role role = null;
				if(data.length >= 4){
					try{
						role = Role.valueOf(data[3]);
					}catch(IllegalArgumentException e){
					}
				}
				if(roleAgentMap.containsKey(name)) {
					throw new IllegalArgumentException("Same player name is not allowed");
				}
				PlayerInfo playerInfo = new PlayerInfo(name, progType, target, role);
				roleAgentMap.put(name, playerInfo);
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
		createServer();
		startJavaClient();
		startServer();
		startTcpipClient();

		while(initServer || isRunning){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @throws IOException
	 */
	protected void createServer() throws IOException {
		if(settingFileName == null){
			gameSetting = GameSetting.getDefaultGame(agentNum);
		}
		else{
//			File file = new File(settingFileName);
			gameSetting = GameSetting.getCustomGame(settingFileName, agentNum);
		}
		if(isHumanPlayer) {
			gameSetting.setTimeLimit(-1);
		}
		gameSetting.setEnableRoleRequest(true);


		gameServer = new TcpipDirectServer(port, agentNum, gameSetting);
		gameServer.addServerListener(new ServerListener() {

			@Override
			public void unconnected(Socket socket, Agent agent, String name) {

			}

			@Override
			public void connected(Socket socket, Agent agent, String name) {
				System.out.println("Connected:"+name);

			}
		});
	}

	/**
	 * Start client from init files
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	protected void startJavaClient() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		List<String> nameList = new ArrayList<>(roleAgentMap.keySet());
//		Collections.shuffle(nameList);
		for(String playerName:nameList){
			PlayerInfo playerInfo = roleAgentMap.get(playerName);
			String classPath = playerInfo.getTarget();

			if(playerInfo.getProgType()!= ProgType.JAVA) {
				continue;
			}

			if(classPath.indexOf(":") >= 0) {
				String[] data = classPath.split(":");
				String jarPath = data[0];
				classPath = data[1];
				AgentLibraryReader.getPlayerClassList(new File(jarPath));
			}


//			List<File> jarInJarPath = getJarInJarPath(jarFile);
//			if(!jarInJarPath.isEmpty()){
//				for(File jarPath:jarInJarPath){
//					jars = jars+pathSplitter+jarPath.getPath();
//				}
//			}


			Role role = playerInfo.getRole();
			Player player = null;
			Class<? extends Player> playerClass = null;
			if(playerClassMap.containsKey(classPath)){
				playerClass = playerClassMap.get(classPath);
				player = (Player)playerClassMap.get(classPath).newInstance();
			}
			else{
				playerClass = (Class<Player>) Class.forName(classPath);
				System.out.println(classPath);
				player = (Player)Class.forName(classPath).newInstance();
			}
			if(playerClass == HumanPlayer.class){
				player = new HumanPlayer(resource);
				isHumanPlayer = true;
			}
			else{
				player = (Player)playerClass.newInstance();
//				isHumanPlayer = false;
			}

			gameServer.add(playerName, player, role);

//			//引数にRoleRequestを追加
//			final TcpipClient client = new TcpipClient("localhost", port, role);
//			if(playerName != null){
//				client.setName(playerName);
////				System.out.println("Set name "+client.getName());
//			}
//
//			final Player p = player;
//			Runnable r = new Runnable() {
//
//				@Override
//				public void run() {
//					if(client.connect(p)){
////						System.out.println("Player connected to server:"+player);
//					}
//				}
//			};
//			Thread t = new Thread(r);
//			t.start();

		}

	}

	/**
	 * start server
	 * @throws SocketTimeoutException
	 * @throws IOException
	 */
	protected void startServer() throws SocketTimeoutException, IOException {

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
							DefaultResource resource = new DefaultResource();
//							JapaneseResource resource = new JapaneseResource();
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
	 * Starte python player via tcpip
	 */
	protected void startTcpipClient() {
		List<String> nameList = new ArrayList<>(roleAgentMap.keySet());
//		Collections.shuffle(nameList);
		for(String playerName:nameList){
			PlayerInfo playerInfo = roleAgentMap.get(playerName);
			ProgType pt = playerInfo.getProgType();
//			String scriptPath = playerInfo.getTarget();
//			Role role = playerInfo.getRole();

			try{
				if(pt == ProgType.PYTHON) {
					startPythonClient(playerInfo);
				}
				else if(pt == ProgType.C_SHARP) {
					startCSharpClient(playerInfo);
				}
			}catch(IOException e){
//				outputExceptionToLogFile(outputFile, e);
//				processMap.put(teamName, null);
				e.printStackTrace();
			}

		}
	}

	private void startPythonClient(PlayerInfo playerInfo) throws IOException {

		String scriptPath = playerInfo.getTarget();

		List<String> command = new ArrayList<String>();

		command.add("python");
		command.add("-u");
		command.add(scriptPath);

		command.add("-p");
		command.add(""+port);
		command.add("-h");
		command.add("localhost");

//				logFile.getParentFile().mkdirs();
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
//		processBuilder.redirectErrorStream(true);
//				processBuilder.redirectError(Redirect.appendTo(outputFile));
//				processBuilder.redirectOutput(Redirect.appendTo(outputFile));
		Process process = processBuilder.start();
//				processMap.put(playerName, process);
	}


	/**
	 * Start .NET Cliets
	 * @param csharpFile
	 * @param className
	 * @throws IOException
	 */
	public void startCSharpClient(PlayerInfo playerInfo) throws IOException{
		if(csharpClientStarterPath == null) {
			throw new IllegalArgumentException("Path to C# ClientStarter must be expressed in AutoStarter.ini");
		}
		String[] paths = playerInfo.getTarget().split(":");
		if(paths.length < 2) {
			throw new IllegalArgumentException("C# player must be include DLL_PATH:CLASS_NAME");
		}
		String filePath = paths[0];
		String className = paths[1];

		List<String> command = new ArrayList<String>();
		command.add(csharpClientStarterPath);
		command.add("-h");
		command.add("localhost");
		command.add("-p");
		command.add(""+port);
		command.add("-c");
		command.add(className);
		command.add(filePath);
		command.add("-n");
		command.add(playerInfo.getName());

		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
//			processBuilder.redirectErrorStream(true);
//			processBuilder.redirectError(Redirect.);
//			processBuilder.redirectOutput(Redirect.appendTo(outputFile));
		Process process = processBuilder.start();
//			processMap.put(teamName, process);
	}


	/**
	 * TODO 外部接続エージェントの成績が表示されない
	 * show results
	 */
	private void result() {
		for(Role role:Role.values()){
			if(role == Role.FREEMASON || role == Role.FOX || role == Role.ANY){
				continue;
			}
			System.out.print("\t"+role);
		}
		System.out.println("\tTotal");
		TreeSet<String> nameSet = new TreeSet<>(winCounterMap.keySet());
		nameSet.addAll(roleCounterMap.keySet());

		for(String name:nameSet){
//		for(String name:new TreeSet<>(roleAgentMap.keySet())){
			if(!winCounterMap.containsKey(name) || !roleCounterMap.containsKey(name)){
				continue;
			}
			System.out.print(name+"\t");
			double win = 0;
			double cnt = 0;
			for(Role role:Role.values()){
				if(role == Role.FREEMASON || role == Role.FOX || role == Role.ANY){
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

enum ProgType{
	JAVA,
	PYTHON,
	C_SHARP,
	OTHERS
}

/**
 * 接続プレイヤーの情報
 * @author xtori
 *
 */
class PlayerInfo{
	String name;
	ProgType progType;
	String target;
	Role role;
	public PlayerInfo(String name, ProgType progType, String target, Role role) {
		super();
		this.name = name;
		this.progType = progType;
		this.target = target;
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public ProgType getProgType() {
		return progType;
	}
	public String getTarget() {
		return target;
	}
	public Role getRole() {
		return role;
	}


}
