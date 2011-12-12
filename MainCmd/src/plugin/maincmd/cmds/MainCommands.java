package plugin.maincmd.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.maincmd.CommandList;
import plugin.maincmd.Description;

public class MainCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		// /maincmd
		//Displays general info (most is (*should be*) shown in the config header) & help
		if (l.equalsIgnoreCase("maincmd")) {
			if (args.length < 1) {
				if (s instanceof Player) {
					((Player)s).sendMessage(ChatColor.GOLD + Description.Desc1);
					((Player)s).sendMessage(ChatColor.GOLD + Description.Desc2);
					((Player)s).sendMessage(ChatColor.GOLD + Description.Desc3);
				}
				else {
					s.sendMessage(Description.Desc1);
					s.sendMessage(Description.Desc2);
					s.sendMessage(Description.Desc3);
				}
			}
			else if (args[0].equalsIgnoreCase("help")) {
				if (args.length < 2) {
					if (s instanceof Player) {
						((Player)s).sendMessage(ChatColor.RED + "Please specify a page number.");
					}
					else {
						s.sendMessage("Please specify a page number.");
					}
				}
				/* Max of 8 messages per line
				 Syntax: (for copy and pasting) ((Player)s).sendMessage(ChatColor.RED + args1 + ChatColor.WHITE + " -- " + ChatColor.GOLD + args2);
				 								s.sendMessage(args1 + " -- " + args2);
				 */
				else if (args[1].equalsIgnoreCase("1")) {
					if (s instanceof Player) {
						((Player)s).sendMessage(ChatColor.BLUE + "++ Page 1");
						((Player)s).sendMessage(ChatColor.BLUE + CommandList.syntax);
						((Player)s).sendMessage(ChatColor.RED + CommandList.mainsyntax + ChatColor.WHITE + " -- "  + ChatColor.GOLD + CommandList.main);
						((Player)s).sendMessage(ChatColor.RED + CommandList.tpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tp);
						((Player)s).sendMessage(ChatColor.RED + CommandList.tpheresyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tphere);
						((Player)s).sendMessage(ChatColor.RED + CommandList.tptosyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tpto);
						((Player)s).sendMessage(ChatColor.RED + CommandList.jumpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.jump);
						((Player)s).sendMessage(ChatColor.RED + CommandList.sendsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.send);
					}
					else {
						s.sendMessage("++ Page 1 ++");
						s.sendMessage(CommandList.syntax);
						s.sendMessage(CommandList.mainsyntax + " -- " + CommandList.main);
						s.sendMessage(CommandList.tpsyntax + " -- " + CommandList.tp);
						s.sendMessage(CommandList.tpheresyntax + " -- " + CommandList.tphere);
						s.sendMessage(CommandList.tptosyntax + " -- " + CommandList.tpto);
						s.sendMessage(CommandList.jumpsyntax + " -- " + CommandList.jump);
						s.sendMessage(CommandList.sendsyntax + " -- " + CommandList.send);
					}
				}
				else if (args[1].equalsIgnoreCase("2")) {
					if (s instanceof Player) {
						((Player)s).sendMessage(ChatColor.BLUE + "++ Page 2 ++");
						((Player)s).sendMessage(ChatColor.RED + CommandList.spawnsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.spawn);
						((Player)s).sendMessage(ChatColor.RED + CommandList.setspawnsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.setspawn);
						((Player)s).sendMessage(ChatColor.RED + CommandList.boomsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.boom);
						((Player)s).sendMessage(ChatColor.RED + CommandList.pingsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.ping);
					}
					else {
						s.sendMessage("++ Page 2 ++");
						s.sendMessage(CommandList.spawnsyntax + " -- " + CommandList.spawn);
						s.sendMessage(CommandList.setspawnsyntax + " -- " + CommandList.setspawn);
						s.sendMessage(CommandList.boomsyntax + " -- " + CommandList.boom);
						s.sendMessage(CommandList.pingsyntax + " -- " + CommandList.ping);
					}
				}
			}
		}
		return false;
	}

}
