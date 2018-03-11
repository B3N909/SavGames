package me.savant.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.savant.main.SavGames;
import me.savant.minigame.GameHelper;
import me.savant.minigame.Minigame;
import me.savant.util.TextUtil;

public class HelpCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("sg"))
		{
			Player p = (Player) sender;
			if(SavGames.hasFailed())
			{
				p.sendMessage(TextUtil.error("SavGames did not start correctly"));
				return true;
			}
			if(args.length > 0) 
			{
				if(args.length == 1)
				{
					if(args[0].equalsIgnoreCase("help"))
					{
						p.sendMessage(TextUtil.header("SavGames Help"));
						p.sendMessage(TextUtil.body(" - /sg (help, version, list)"));
						p.sendMessage(TextUtil.body(" - /l (list, delete <uniqueName>, create <uniqueName> <minigame> <maxPlayers>)"));
						p.sendMessage(TextUtil.body(" - /ltp <uniqueName>  'Teleports to lobby spawn!'"));
					}
					else if(args[0].equalsIgnoreCase("version")) 
					{
						p.sendMessage(TextUtil.body("SavGames created by _Savant version 1.0"));
					}
					else if(args[0].equalsIgnoreCase("list"))
					{
						if(GameHelper.instances.isEmpty())
						{
							p.sendMessage(TextUtil.body("No minigames initialized"));
						}
						else
						{
							p.sendMessage(TextUtil.header("Minigames"));
							for(Minigame m : GameHelper.instances)
							{
								p.sendMessage(TextUtil.body(" - " + m.getGameName()));
							}
						}
						return true;
					}
				}
				else
				{
					p.sendMessage(TextUtil.body("Usage: /sg (help, version, list)"));
				}
				return true;
			}
			p.sendMessage(TextUtil.body("SavGames created by _Savant version 1.0"));
			return true;
		}
		return false;
	}
}
