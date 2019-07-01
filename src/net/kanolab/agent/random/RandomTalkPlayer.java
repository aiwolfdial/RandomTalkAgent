package net.kanolab.agent.random;

import org.aiwolf.common.data.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;

/**
 * 
 * 
 * @author ymaki
 *
 */
public class RandomTalkPlayer implements Player{
	Agent me;
	int day;
	boolean canTalk;
	GameInfo currentGameInfo;
	
	List<Agent> aliveOthers;
	String name = "RandomTalkPlayer";
	
	private static List<String> talkList;
	private static Random random;

	RandomTalkPlayer(){
		talkList = readLog("./source/2019071_44011_AIWolfTalkLogs.txt");
//		System.out.println("talkList.size="+talkList.size());
		
		random = new Random();
	}
	
	public String getName() {
		return name;
	}
	
	public void update(GameInfo gameInfo) {
		currentGameInfo = gameInfo;
		
	}
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		day = -1;
		
	}
	
	public void dayStart() {
		
	}
	
	public String talk() {
		return "randomTalkplayer.";
	}
	
	public String whisper() {
		return "Over";
	}
	
	public Agent vote() {
		return currentGameInfo.getAliveAgentList().get(0);
	}
	public Agent attack() {
		return currentGameInfo.getAliveAgentList().get(0);
	}
	
	public Agent divine() {
		return currentGameInfo.getAliveAgentList().get(0);
	}
	
	public Agent guard() {
		return currentGameInfo.getAliveAgentList().get(0);
	}
	
	public void finish() {
		
	}
	
	List<String> readLog(String path){
		List<String> list = null;
		try {
			list = Files.readAllLines(Paths.get(path));
		}catch(IOException ie) {
			ie.printStackTrace();
		}
		return list;
	}
	
	public static void main(String args[]) {
		RandomTalkPlayer player = new RandomTalkPlayer();
		System.out.println(player.getName());
	}
}
