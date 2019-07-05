package net.kanolab.agent.random;

import org.aiwolf.sample.lib.*;
import org.aiwolf.common.data.Player;

public class RandomRoleAssignPlayer extends AbstractRoleAssignPlayer {
	private Player villagerPlayer, seerPlayer, mediumPlayer;
	private Player bodyguardPlayer, possessedPlayer, werewolfPlayer,rolePlayer;
	
	public Player getRolePlayer() {
		return rolePlayer;
	}


	public void setRolePlayer(Player rolePlayer) {
		this.rolePlayer = rolePlayer;
	}


	public String getName() {
		return "RandomSamplePlayer";
	}
}
