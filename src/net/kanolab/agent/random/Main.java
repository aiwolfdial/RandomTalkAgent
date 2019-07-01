package net.kanolab.agent.random;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Main {
	private static List<String> talkList;
	private static Random random;
	
	Main(){
		talkList = readLog("./source/2019071_44011_AIWolfTalkLogs.txt");
		System.out.println("talkList.size="+talkList.size());
		
		random = new Random();
	}
	public static void main(String args[]) {
//		RandomRoleAssignPlayer agent = new RandomRoleAssignPlayer();
//		System.out.println(agent.getName());
		
		Main m = new Main();
		for(int i = 0; i < 500; i++) {
			System.out.println(m.talk());
		}
	}
	
	String talk() {
		int i = random.nextInt(talkList.size());
		return i+":"+talkList.get(i);
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
}
