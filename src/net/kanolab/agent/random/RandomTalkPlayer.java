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
import org.aiwolf.common.data.Talk;
import org.aiwolf.client.lib.EstimateContentBuilder;

/**
 * 
 * An sample agent player class that makes random utterance from past game logs.
 * @author ymaki
 *
 */
public class RandomTalkPlayer implements Player{
	Agent me;
	int day;
	boolean canTalk;
	GameInfo currentGameInfo;
	
	List<Agent> aliveOthers;
	
	// 発話ログファイルのパス
	private final String logPath = "./source/2019071_44011_AIWolfTalkLogs.txt";
	// ログファイルから読み込んだ発話を保持
	private List<String> talkList;
	private Random random;
	
	Player player;
	
	public RandomTalkPlayer(){
		talkList = readLog(logPath);
//		System.out.println("talkList.size="+talkList.size());
		
		random = new Random();
	}
	
	public String getName() {
		return "RandomTalkPlayer";
	}
	
	public void update(GameInfo gameInfo) {
		currentGameInfo = gameInfo;
		
		if(currentGameInfo.getDay() == day+1) {
			day = currentGameInfo.getDay();
			return;
		}
	
	}
	
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		day = -1;
		me = gameInfo.getAgent();
		aliveOthers = new ArrayList<>(gameInfo.getAliveAgentList());
		aliveOthers.remove(me);
	}
	
	public void dayStart() {
		canTalk = true;
		aliveOthers = currentGameInfo.getAliveAgentList();
		aliveOthers.remove(me);
		
	}
	
	// 発話ログのリストからランダムに取得し発話
	public String talk() {
		int i = random.nextInt(talkList.size());
		return talkList.get(i);
	
	}
	
	public String whisper() {
		return "---";
	}
	
	public Agent vote() {
		canTalk = false;
		return aliveOthers.get(0);
	}
	
	public Agent attack() {
		return aliveOthers.get(0);
	}

	public Agent divine() {
		return aliveOthers.get(0);
	}
	
	public Agent guard() {
		return aliveOthers.get(0);
	}
	
	public void finish() {
		
	}
	
	/**
	 *  ログファイルから発話を読み込んでリストで返す
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
