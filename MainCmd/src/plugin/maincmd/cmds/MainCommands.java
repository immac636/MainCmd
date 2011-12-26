package plugin.maincmd.cmds;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.maincmd.CommandList;
import plugin.maincmd.Description;
import plugin.maincmd.MainCmd;

public class MainCommands
	implements CommandExecutor
{
	Logger log = Logger.getLogger("Minecraft");

	private void maincmd(CommandSender s, Command c, String l, String[] args) {
		if (args.length < 1) {
			if ((s instanceof Player)) {
				((Player)s).sendMessage(ChatColor.GOLD + Description.Desc1);
				((Player)s).sendMessage(ChatColor.GOLD + Description.Desc2);
				((Player)s).sendMessage(ChatColor.GOLD + Description.Desc3);
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
			}
			else {
				s.sendMessage(Description.Desc1);
				s.sendMessage(Description.Desc2);
				s.sendMessage(Description.Desc3);
			}

		}
		else if ((!args[0].equalsIgnoreCase("help")) && (!args[0].equalsIgnoreCase("reload"))) {
			if ((s instanceof Player)) {
				((Player)s).sendMessage(ChatColor.RED + "Valid commands are: /maincmd help, /maincmd reload");
			}
			else {
				s.sendMessage("Valid commands are: /maincmd help, /maincmd reload");
			}

		}
		else if (args[0].equalsIgnoreCase("help")) {
			if (args.length < 2) {
				if ((s instanceof Player)) {
					((Player)s).sendMessage(ChatColor.RED + "Please specify a page number.");
				}
				else {
					s.sendMessage("Please specify a page number.");
				}

			}
			// ((Player)s).sendMessage(ChatColor.RED + args1 + ChatColor.WHITE + " -- " + ChatColor.GOLD + args2);
			// s.sendMessage(args1 + " -- " + args2);
			else if (args[1].equalsIgnoreCase("1")) {
				if ((s instanceof Player)) {
					((Player)s).sendMessage(ChatColor.BLUE + "++ Page 1 of 4 ++");
					((Player)s).sendMessage(ChatColor.BLUE + CommandList.syntax);
					((Player)s).sendMessage(ChatColor.RED + CommandList.mainsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.main);
					((Player)s).sendMessage(ChatColor.RED + CommandList.tpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tp);
					((Player)s).sendMessage(ChatColor.RED + CommandList.tpheresyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tphere);
					((Player)s).sendMessage(ChatColor.RED + CommandList.tptosyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.tpto);
					((Player)s).sendMessage(ChatColor.RED + CommandList.jumpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.jump);
					((Player)s).sendMessage(ChatColor.RED + CommandList.sendsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.send);
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " help 1");
				}
				else {
					s.sendMessage("++ Page 1 of 4 ++");
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
				if ((s instanceof Player)) {
					((Player)s).sendMessage(ChatColor.BLUE + "++ Page 2 of 4 ++");
					((Player)s).sendMessage(ChatColor.RED + CommandList.spawnsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.spawn);
					((Player)s).sendMessage(ChatColor.RED + CommandList.setspawnsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.setspawn);
					((Player)s).sendMessage(ChatColor.RED + CommandList.boomsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.boom);
					((Player)s).sendMessage(ChatColor.RED + CommandList.pingsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.ping);
					((Player)s).sendMessage(ChatColor.RED + CommandList.givesyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.give);
					((Player)s).sendMessage(ChatColor.RED + CommandList.homesyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.home);
					((Player)s).sendMessage(ChatColor.RED + CommandList.sethomesyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.sethome);
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " help 2");
				}
				else {
					s.sendMessage("++ Page 2 of 4 ++");
					s.sendMessage(CommandList.spawnsyntax + " -- " + CommandList.spawn);
					s.sendMessage(CommandList.setspawnsyntax + " -- " + CommandList.setspawn);
					s.sendMessage(CommandList.boomsyntax + " -- " + CommandList.boom);
					s.sendMessage(CommandList.pingsyntax + " -- " + CommandList.ping);
					s.sendMessage(CommandList.givesyntax + " -- " + CommandList.give);
					s.sendMessage(CommandList.homesyntax + " -- " + CommandList.home);
					s.sendMessage(CommandList.sethomesyntax + " -- " + CommandList.sethome);
				}
			}
			else if (args[1].equalsIgnoreCase("3")) {
				if ((s instanceof Player)) {
					((Player)s).sendMessage(ChatColor.BLUE + "++ Page 3 of 4 ++");
					((Player)s).sendMessage(ChatColor.RED + CommandList.isyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.i);
					((Player)s).sendMessage(ChatColor.RED + CommandList.warpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.warp);
					((Player)s).sendMessage(ChatColor.RED + CommandList.setwarpsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.setwarp);
					((Player)s).sendMessage(ChatColor.RED + CommandList.godsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.god);
					((Player)s).sendMessage(ChatColor.RED + CommandList.creativesyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.creative);
					((Player)s).sendMessage(ChatColor.RED + CommandList.survivalsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.survival);
					((Player)s).sendMessage(ChatColor.RED + CommandList.afksyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.afk);
				}
				else {
					s.sendMessage("++ Page 3 of 4 ++");
					s.sendMessage(CommandList.isyntax + " -- " + CommandList.i);
					s.sendMessage(CommandList.warpsyntax + " -- " + CommandList.warp);
					s.sendMessage(CommandList.setwarpsyntax + " -- " + CommandList.setwarp);
					s.sendMessage(CommandList.godsyntax + " -- " + CommandList.god);
					s.sendMessage(CommandList.creativesyntax + " -- " + CommandList.creative);
					s.sendMessage(CommandList.survivalsyntax + " -- " + CommandList.survival);
					s.sendMessage(CommandList.afksyntax + " -- " + CommandList.afk);
				}
			}
			else if (args[1].equalsIgnoreCase("4")) {
				if (s instanceof Player) {
					((Player)s).sendMessage(ChatColor.BLUE + "++ Page 4 of 4 ++");
					((Player)s).sendMessage(ChatColor.RED + CommandList.healsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.heal);
					((Player)s).sendMessage(ChatColor.RED + CommandList.feedsyntax + ChatColor.WHITE + " -- " + ChatColor.GOLD + CommandList.feed);
				}
				else {
					s.sendMessage("++ Page 4 of 4 ++ ");
					s.sendMessage(CommandList.healsyntax + " -- " + CommandList.heal);
					s.sendMessage(CommandList.feedsyntax + " -- " + CommandList.feed);
				}
			}
		}
		else if (args[0].equalsIgnoreCase("reload")) {
			if ((s instanceof Player)) {
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.main.reload")) {
					MainCmd.plugin.reloadConfig();
					((Player)s).sendMessage(ChatColor.GOLD + "[MainCmd] Configuration file successfully reloaded!");
					log.info("[MainCmd] Configuration file was reloaded by " + ((Player)s).getName());
				}
				else {
					((Player)s).sendMessage(MainCmd.plugin.getConfig().getString("Messages.MissingPermissions"));
				}
			}
			else {
				MainCmd.plugin.reloadConfig();
				s.sendMessage("[MainCmd] Configuration file successfully reloaded!");
			}
		}
	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args)
	{
		if ((s instanceof Player)) {
			log.info("[MainCmd] User " + ((Player)s).getName() + " used (or attempted to use) the command " + l.toString());
		}
		if (l.equalsIgnoreCase("maincmd")) {
			maincmd(s, c, l, args);
		}
		return false;
	}
}