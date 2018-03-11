package me.savant.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.savant.main.SavGames;
import me.savant.minigame.GameHelper;
import me.savant.minigame.lobby.Lobby;
import me.savant.minigame.lobby.LobbyHelper;
import me.savant.util.TextUtil;

public class LobbyCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ltp"))
		{
			Player p = (Player) sender;
			if(SavGames.hasFailed())
			{
				p.sendMessage(TextUtil.error("SavGames did not start correctly"));
				return true;
			}
			if(args.length == 1)
			{
				String uniqueName = args[0];
				if(Lobby.exists(uniqueName))
				{
					Lobby l = new Lobby(uniqueName);
					p.teleport(l.getSpawn());
					p.sendMessage(TextUtil.body("Woosh!"));
				}
				else
				{
					p.sendMessage(TextUtil.body("Cannot find lobby with that name!"));
				}
			}
			else
			{
				p.sendMessage(TextUtil.body("Usage: /ltp <uniqueName>"));
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("l"))
		{
			Player p = (Player) sender;
			if(args.length > 0) 
			{
				if(args[0].equalsIgnoreCase("delete"))
				{
					if(args.length == 2)
					{
						String uniqueName = args[1];
						if(Lobby.exists(uniqueName))
						{
							new File(Lobby.LOBBY_PREFIX + uniqueName + ".yml").delete();
							LobbyHelper.downloadInstances();
							p.sendMessage(TextUtil.body("Successfully deleted lobby!"));
						}
						else
						{
							p.sendMessage(TextUtil.body("Cannot find lobby with that name!"));
						}
					}
					else
					{
						p.sendMessage(TextUtil.body("Usage: /l delete <uniqueName>"));
					}
					return true;
				}
				else if(args[0].equalsIgnoreCase("create"))
				{
					if(args.length == 4)
					{
						String uniqueName = args[1];
						String minigame = args[2];
						String maxPlayersString = args[3];
						
						if(!Lobby.exists(uniqueName))
						{
							if(GameHelper.exists(minigame))
							{
								int maxPlayers = 0;
								try
								{
									maxPlayers = Integer.parseInt(maxPlayersString);
								}
								catch (Exception e)
								{
									p.sendMessage(TextUtil.body("Max Players is not a number!"));
									return true;
								}
								Lobby.create(GameHelper.getMinigame(minigame), uniqueName, p.getLocation(), maxPlayers);
								p.sendMessage(TextUtil.body("Created lobby!"));
								return true;
							}
							else
							{
								p.sendMessage(TextUtil.body("Minigame does not exist, try: /sg list"));
							}
						}
						else
						{
							p.sendMessage(TextUtil.body("Lobby has already been created with that name!"));
						}
						return true;
					}
					else
					{
						p.sendMessage(TextUtil.body("Usage: /l create <uniqueName> <minigame> <maxPlayers>"));
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("list"))
				{
					if(!LobbyHelper.instances.isEmpty())
					{
						p.sendMessage(TextUtil.header("Lobbies"));
						for(Lobby l : LobbyHelper.instances)
						{
							p.sendMessage(TextUtil.body(" - " + l.getLobbyName()));
						}
					}
					else
					{						
						p.sendMessage(TextUtil.body("No lobbies found!"));
					}
					return true;
				}
				else if(args[0].equalsIgnoreCase("join"))
				{ 
					if(!LobbyHelper.instances.isEmpty())
					{
						if(Lobby.exists(args[0]))
						{
							Lobby l = new Lobby(args[0]);
							l.join(p);
						}
						else
						{
							p.sendMessage(TextUtil.body("Lobby does not exist!"));
						}
					}
					else
					{						
						p.sendMessage(TextUtil.body("No lobbies found!"));
					}
					return true;
				}
			}
			p.sendMessage(TextUtil.body("Usage: /l (join <uniqueName>, list, delete <uniqueName>, create <uniqueName> <minigame> <maxPlayers>)"));
			return true;
		}
		return false;
	}
}
