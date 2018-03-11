package me.savant.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.savant.commands.HelpCommands;
import me.savant.commands.LobbyCommands;
import me.savant.minigame.lobby.LobbyHelper;

public class SavGames extends JavaPlugin
{
	public static boolean hasFailed = false;
	public static void fail()
	{
		hasFailed = true;
		System.out.println("===================");
		System.out.println("| Fatal exception |");
		System.out.println("|    stopping...  |");
		System.out.println("===================");
		System.out.println(" ");
		
		Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("SavGames"));
	}
	
	public static boolean hasFailed()
	{
		return hasFailed;
	}
	
	@Override
	public void onEnable()
	{
		System.out.println("=======================");
		System.out.println("|      (Enbaled)      |");
		System.out.println("|---------------------|");
		System.out.println("|       SavGames      |");
		System.out.println("|      Version 1.0    |");
		System.out.println("|       by Savant     |");
		System.out.println("|      Savant#0093    |");
		System.out.println("=======================");
		System.out.println(" ");

		LobbyHelper.downloadInstances();
		
		getCommand("sg").setExecutor(new HelpCommands());
		getCommand("l").setExecutor(new LobbyCommands());
		getCommand("ltp").setExecutor(new LobbyCommands());
	}
	
	@Override
	public void onDisable()
	{
		System.out.println("=======================");
		System.out.println("|      (Disabled)     |");
		System.out.println("|---------------------|");
		System.out.println("|       SavGames      |");
		System.out.println("|      Version 1.0    |");
		System.out.println("|       by Savant     |");
		System.out.println("|      Savant#0093    |");
		System.out.println("=======================");
		System.out.println(" ");
	}

	
	
}
