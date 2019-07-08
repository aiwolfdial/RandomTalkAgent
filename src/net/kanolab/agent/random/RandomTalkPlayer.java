package net.kanolab.agent.random;

import org.aiwolf.common.data.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;

/**
 * 
 * An sample agent player class that makes random utterance from past game logs.
 * @author ymaki
 *
 */
public class RandomTalkPlayer implements Player{
	Agent me;
	int day;
	GameInfo currentGameInfo;	
	List<Agent> aliveOthers;
	
	// 発話ログファイルのパス
	// Log file of previous games, randomly selected to make talks
	private final String logPath = "./res/2019071_44011_AIWolfTalkLogs.txt";
	// ログファイルから読み込んだ発話を保持
	// List of String to cache all of the log file contents
	private List<String> talkList;
	// Random to select one of the log file utterances
	private Random random;
		
	/**
	 * constructor to read the logs
	 */
	public RandomTalkPlayer(){
		talkList = readLog(logPath);
//		System.out.println("talkList.size="+talkList.size());
		
		random = new Random();
	}
	
	/**
	 * You should return a unique player name
	 */
	@Override
	public String getName() {
		return "RandomTalkPlayer";
	}
	
	/**
	 * Called at every start of turns. gameInfo contains a current information of the game, 
	 * e.g. who is alive, what was the divine results (if you are seer), list of previous talks, etc.
	 */
	@Override
	public void update(GameInfo gameInfo) {
		currentGameInfo = gameInfo;
		
		if(currentGameInfo.getDay() == day+1) {
			day = currentGameInfo.getDay();
			return;
		}
	
	}
	
	/**
	 * Called at the start of the entire game (not the start of the day).
	 * There may be a session which runs multiple games, then this initialize will be called every start of the games.
	 */
	@Override
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		day = -1;
		me = gameInfo.getAgent();
		aliveOthers = new ArrayList<>(gameInfo.getAliveAgentList());
		aliveOthers.remove(me);
	}
	
	/**
	 * called at a start of a day. You may wish to initialize something for that day.
	 */
	@Override
	public void dayStart() {
		aliveOthers.clear();
		aliveOthers.addAll(currentGameInfo.getAliveAgentList());
		aliveOthers.remove(me);		
	}
	
	// 発話ログのリストからランダムに取得し発話
	/**
	 * You should change/override to return your own talk.
	 * During days, Agents can communicate anything in natural language.  
	 * A talk should consists of normal letters and punctuations only. 
	 * An agent returns Talk.SKIP ("Skip") when nothing to talk, returns Talk.OVER ("Over") if nothing to talk anymore in that day.   
	 * Use Agent[0x] (e.g. Agent[05], x is 1-5)  to mention other agents．
	 * An anchor e.g. ">>Agent[0x]" could be inserted at the beginning of a talk to refer to another agent, 
	 * to whom your agent with to talk with. That agent is assumed to respond something to your agent by using an anchor.
	 */
	@Override
	public String talk() {
		int i = random.nextInt(talkList.size());
		return talkList.get(i);		
	}
	
	/**
	 * called when you are a werewolf and wish to talk with other werewolfs secretly, 
	 * but not used in the AIWolfDial shared task because there is just a single werewolf
	 */
	@Override
	public String whisper() {
		return null;
	}
	
	/**
	 * called in the night to vote, returns an agent to be voted
	 */
	@Override
	public Agent vote() {
		return aliveOthers.get(0);
	}
	
	/**
	 * called when you are the werewolf, returns an agent to attack (kill)
	 */
	@Override
	public Agent attack() {
		return aliveOthers.get(0);
	}

	/**
	 * called when you are the seer, returns an agent to divine
	 */
	@Override
	public Agent divine() {
		return aliveOthers.get(0);
	}
	
	/**
	 * called when you are the Guard role, but not used in the AIWolfDial shared task
	 */
	@Override
	public Agent guard() {
		return aliveOthers.get(0);
	}
	
	/**
	 * called when the game finishes
	 */
	@Override
	public void finish() {
		
	}
	
	/**
	 *  ログファイルから発話を読み込んでリストで返す
	 * read the log files
	 * @param path
	 * @return 発話(String)のリスト
	 */
	List<String> readLog(String path){
		List<String> list = null;
		try {
			list = Files.readAllLines(Paths.get(path));
		}catch(IOException ie) {
			ie.printStackTrace();
		}
		return list;
	}

}
