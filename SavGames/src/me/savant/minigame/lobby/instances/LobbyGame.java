package me.savant.minigame.lobby.instances;

import org.bukkit.entity.Player;

import me.savant.minigame.Minigame;

public abstract class LobbyGame
{	
	public LobbyGame(Minigame minigame)
	{
		minigame.setListener(this);
	}
	
	public abstract void onJoin(Player p);
}
