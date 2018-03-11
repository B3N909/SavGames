package me.savant.minigame.lobby;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.savant.util.TextUtil;

public class LobbyHelper
{
	public static List<Lobby> instances = new ArrayList<Lobby>();
	
	public static boolean isInstanced(String uniqueName)
	{
		for(Lobby l : instances)
		{
			if(l.uniqueName.equalsIgnoreCase(uniqueName))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void downloadInstances()
	{
		instances.clear();
		File f = new File("savgames");
		if(f.exists())
		{
			File[] files = f.listFiles();
			for(int i = 0; i < files.length; i++)
			{
				instances.add(new Lobby(files[i]));
			}
			
			System.out.println(" ");
			System.out.println("=========================");
			System.out.println("| " + TextUtil.center("Loaded " + files.length + " lobbies!", 21) + " |");
			System.out.println("=========================");
			
			for(Lobby l : instances)
			{
				String n = l.getLobbyName();
				if(l.getLobbyName() == null)
					n = "Failed loading";
				System.out.println("| " + TextUtil.center(" *" + n, 21) + " |");
				System.out.println("=========================");
			}
		}
		else
		{
			System.out.println(" ");
			System.out.println("=======================");
			System.out.println("| No lobbies created! |");
			System.out.println("=======================");
		}
	}
}
