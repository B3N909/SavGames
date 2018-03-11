package me.savant.minigame;

import java.util.ArrayList;
import java.util.List;

import me.savant.exceptions.ExceptionHandler;
import me.savant.exceptions.MinigameException;
import me.savant.util.TextUtil;

public class GameHelper
{
	public static List<Minigame> instances = new ArrayList<Minigame>();
	
	public static boolean exists(String uniqueName)
	{
		for(Minigame p : instances)
		{
			if(p.uniqueName.equalsIgnoreCase(uniqueName))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void fail()
	{
		for(Minigame p : instances)
		{
			System.out.println(" ");
			System.out.println("=============================");
			System.out.println("|" + TextUtil.center("Closed: " + p.getName(), 27) + "|");
			System.out.println("=============================");
			p.disable();
		}
		ExceptionHandler.throwException();
	}
	
	public static Minigame getMinigame(String uniqueName)
	{
		for(Minigame p : instances)
		{
			if(p.getGameName().equalsIgnoreCase(uniqueName))
			{
				return p;
			}
		}
		try
		{
			throw new MinigameException("Cannot find a minigame with that name!");
		}
		catch (MinigameException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
