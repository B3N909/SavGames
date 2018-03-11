package me.savant.minigame;

import org.bukkit.plugin.java.JavaPlugin;

import me.savant.exceptions.InvalidNameException;
import me.savant.minigame.lobby.instances.LobbyGame;
import me.savant.minigame.sign.SignConstructor;
import me.savant.util.TextUtil;

public class Minigame extends JavaPlugin
{
	boolean willStartup = true;
	
	String uniqueName;
	
	SignConstructor signConstructor;
	
	public Minigame(String uniqueName)
	{
		if(GameHelper.exists(uniqueName))
		{
			try
			{
				throw new InvalidNameException("Minigame with similar name has already been created!");
			}
			catch (InvalidNameException e)
			{
				e.printStackTrace();
			}
		}
		if(uniqueName.length() > 19)
		{
			try
			{
				throw new InvalidNameException("Minigame name exceeds maximum 19 characters length!");
			}
			catch (InvalidNameException e)
			{
				e.printStackTrace();
			}
			uniqueName = "error";
		}
		this.uniqueName = uniqueName;

		GameHelper.instances.add(this);
	}
	
	@Override
	public void onEnable()
	{
		if(willStartup)
		{
			System.out.println(" ");
			System.out.println("=======================");
			System.out.println("|       (Loaded)      |");
			System.out.println("|---------------------|");
			System.out.println("|" + TextUtil.center(uniqueName, 21) + "|");
			System.out.println("=======================");			
		}
		else
		{
			System.out.println(" ");
			System.out.println("=======================");
			System.out.println("|       (Failed)      |");
			System.out.println("|---------------------|");
			System.out.println("|" + TextUtil.center(uniqueName, 21) + "|");
			System.out.println("=======================");
		}
	}
	
	public void disable()
	{
		willStartup = false;
	}
	
	public String getGameName()
	{
		return uniqueName;
	}
	
	LobbyGame listener;
	public void setListener(LobbyGame listener)
	{
		this.listener = listener;
	}
	
	public LobbyGame getListener()
	{
		return listener;
	}
}