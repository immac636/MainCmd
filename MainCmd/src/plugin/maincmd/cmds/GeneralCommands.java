package plugin.maincmd.cmds;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.maincmd.MainCmd;

public class GeneralCommands implements CommandExecutor {
	private void whois(CommandSender s, Command c, String l, String[] args) {
		
	}

	private void list(CommandSender s, Command c, String l, String[] args) {
		
	}

	private void motd(CommandSender s, Command c, String l, String[] args) {
		
	}

	private void sun(CommandSender s, Command c, String l, String[] args){
		
	}

	private void rain(CommandSender s, Command c, String l, String[] args){
		
	}

	private void spawnmob(CommandSender s, Command c, String l, String[] args){
		
	}

	private void day(CommandSender s, Command c, String l, String[] args){
		if (s instanceof Player) {
			if (MainCmd.plugin.permsCheck(((Player)s), "MainCmd.general.time")) {
				((Player)s).getWorld().setTime(0);
			}
		}
	} 

	private void night(CommandSender s, Command c, String l, String[] args){
		
	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args)
	{
		return false;
	}
}