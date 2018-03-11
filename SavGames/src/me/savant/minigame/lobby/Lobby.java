package me.savant.minigame.lobby;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.savant.exceptions.LobbyException;
import me.savant.minigame.GameHelper;
import me.savant.minigame.Minigame;
import me.savant.util.Config;
import net.md_5.bungee.api.ChatColor;

public class Lobby
{
	public static final String LOBBY_PREFIX = "savgames\\lobby_";
	public static final String LOBBY_SHORT_PREFIX = "lobby_";
	
	Minigame minigame;
	String uniqueName;
	Location spawn;
	int maxPlayers;
	Config c;
	
	boolean failed = false;
	
	private Lobby(Minigame minigame, String uniqueName, Location spawn, int maxPlayers)
	{
		this.minigame = minigame;
		this.uniqueName = uniqueName;
		this.spawn = spawn;
		this.maxPlayers=  maxPlayers;
		LobbyHelper.downloadInstances();
	}
	
	public Lobby(String uniqueName)
	{
		if(uniqueName.contains(LOBBY_SHORT_PREFIX))
		{
			uniqueName = uniqueName.replace(LOBBY_SHORT_PREFIX, "");
		}
		if(uniqueName.contains("."))
		{
			uniqueName = uniqueName.split("\\.")[0];
		}
		if(Lobby.exists(uniqueName))
		{
			this.c = new Config(new File(LOBBY_PREFIX + uniqueName + ".yml"));
			String minigame = c.yml.getString("minigame");
			if(!GameHelper.exists(minigame))
			{
				try
				{
					failed = true;
					throw new LobbyException("Minigame is not created");
				}
				catch (LobbyException e)
				{
					e.printStackTrace();
				}
				return;
			}
			this.minigame = GameHelper.getMinigame(c.yml.getString("minigame"));
			this.uniqueName = uniqueName;
			this.spawn = Config.toLocation(c.yml.getString("spawn"));
			this.maxPlayers = c.yml.getInt("maxPlayers");
		}
		else
		{
			try
			{
				throw new LobbyException("Lobby is not created!");
			}
			catch (LobbyException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Lobby(File f)
	{
		this(f.getName().replace(LOBBY_PREFIX, ""));
	}
	
	public static Lobby create(Minigame minigame, String uniqueName, Location spawn, int maxPlayers)
	{
		if(exists(uniqueName))
		{
			try
			{
				throw new LobbyException("Lobby is already created!");
			}
			catch (LobbyException e)
			{
				e.printStackTrace();
			}
		}
		Config c = new Config(new File(LOBBY_PREFIX + uniqueName + ".yml"));
		
		c.yml.set("minigame", minigame.getGameName());
		c.yml.set("name", uniqueName);
		c.yml.set("spawn", Config.toString(spawn));
		c.yml.set("maxPlayers", Integer.valueOf(maxPlayers));
		c.save();
		
		return new Lobby(minigame, uniqueName, spawn, maxPlayers);
	}
	
	public static boolean exists(String uniqueName)
	{
		return new File(LOBBY_PREFIX + uniqueName + ".yml").exists();
	}
	
	public String getLobbyName()
	{
		return uniqueName;
	}

	public Location getSpawn()
	{
		return Config.toLocation(c.yml.getString("spawn"));
	}
	
	public void join(Player p)
	{
		p.teleport(getSpawn());
		p.sendMessage(ChatColor.BLUE + " You joined the lobby!");
		minigame.getListener().onJoin(p);
	}
}
