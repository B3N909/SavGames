package me.savant.util;

import java.util.Collections;

import org.bukkit.ChatColor;

public class TextUtil
{
	public static String header(String text)
	{
		return ChatColor.DARK_GREEN + "" + ChatColor.BOLD + text;
	}
	
	public static String body(String text)
	{
		return ChatColor.DARK_GREEN + text;
	}
	
	public static String error(String text)
	{
		return ChatColor.RED + text;
	}
	
	public static String center(String text, int max)
	{
		int l;
		int r;
		int rem = max - text.length();
		if(rem % 2 == 0)
		{
			l = rem / 2;
			r = l;
		}
		else
		{
			l = (int) Math.floor((float) rem / 2f);
			r = l + 1;
		}		
		
		String l1 = String.join("", Collections.nCopies(l, " "));
		String r1 = String.join("", Collections.nCopies(r, " "));
		return l1 + text + r1;
	}
	
	public static String swap(String placeholder, String value, String text)
	{
		return text.replace(placeholder, value);
	}

}
