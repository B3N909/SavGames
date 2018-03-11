package me.savant.util;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config
{
	public YamlConfiguration yml;
	private File f;
	
	public Config(File f)
	{
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		if(!f.exists())
		{
			try
			{
				yml.save(f);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		this.yml = yml;
		this.f = f;
	}
	
	public boolean save()
	{
		try
		{
			yml.save(f);
			return true;
		}
		catch (Exception e)
		{
			System.err.println("[NekroRP] Failed to save printers!");
			return false;
		}
	}
	
	public static String toString(Location l)
	{
		return l.getWorld().getUID() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	}
	
	public static String toString(UUID uuid)
	{
		return uuid.toString();
	}
	
	public static UUID toUUID(String s)
	{
		return UUID.fromString(s);
	}
	
	public static Location toLocation(String s)
	{
		String[] p = s.split(",");
		return new Location(
				Bukkit.getWorld(UUID.fromString(p[0])),
				Integer.parseInt(p[1]),
				Integer.parseInt(p[2]),
				Integer.parseInt(p[3]));
	}
	
}