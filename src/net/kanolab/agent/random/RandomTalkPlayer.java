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
import org.aiwolf.client.lib.EstimateContentBuilder;

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
	}
	
	public String talk() {
		int i = random.nextInt(talkList.size());
		System.out.println("random talk:"+talkList.get(i));
		return i+":"+talkList.get(i);
//		return null;
	}
	
	public String whisper() {
		return "---";
	}
	
	public Agent vote() {
		canTalk = false;
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
	
//	public Player getVillagerPlayer() 	{	return this;	}
//	public Player getSeerPlayer() 		{	return this;	}
//	public Player getMediumPlayer() 	{	return this;	}
//	public Player getBodyguardPlayer() {	return this;	}
//	public Player getPossessedPlayer() {	return this;	}
//	public Player getWerewolfPlayer() 	{	return this;	}
//	public Player getRolePlayer() 		{	return this;	}
//
//	public void setVillagerPlayer(Player villagerPlayer) {	this.player = villagerPlayer; }
//	public void setSeerPlayer(Player seerPlayer) {	this.player = seerPlayer;	}
//	public void setMediumPlayer(Player mediumPlayer) {	this.player = mediumPlayer;}
//	public void setBodyguardPlayer(Player bodyguardPlayer) {	this.player = bodyguardPlayer;}
//	public void setPossessedPlayer(Player possessedPlayer) {	this.player = possessedPlayer;}
//	public void setWerewolfPlayer(Player werewolfPlayer) {	this.player = werewolfPlayer;}
//	public void setRolePlayer(Player rolePlayer) {	this.player = rolePlayer;}
	
//	public static void main(String args[]) {
//		RandomTalkPlayer player = new RandomTalkPlayer();
//		System.out.println(player.getName());
//		for(int i = 0; i < 30; i++) {
//			System.out.println(player.talk());
//		}
//	}
}
