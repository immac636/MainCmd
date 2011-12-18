package plugin.maincmd.cmds;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.maincmd.CommandList;
import plugin.maincmd.MainCmd;

public class TeleportCommands implements CommandExecutor {
	Logger log = Logger.getLogger("Minecraft");

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			log.info("[MainCmd] User " + ((Player)s).getName() + " used (or attempted to use) the command " + l.toString());
		}
		// /tp
		//Teleport Player to Player
		if (l.equalsIgnoreCase("tp")) {		
			if (args.length < 1) {
				if (s instanceof Player) {
						s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tpsyntax);
				}
				else {
					s.sendMessage("Usage: " + CommandList.tpsyntax);
				}
			}
			else if (s instanceof Player) {
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tp")) {
					Player t1 = Bukkit.getServer().getPlayer(args[0]);
					Player t2 = Bukkit.getServer().getPlayer(args[1]);
					if ((t1 != null) && (t2 != null)) {
						t1.teleport(t2.getLocation());
						((Player)s).sendMessage(ChatColor.GREEN + "You sent " + t1.getName() + " to " + t2.getName() + ".");
						t1.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent you to " + t2.getName() + "!");
						t2.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent " + t1.getName() + " to you!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t1.getName() + " " + t2.getName());
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + "One or both of those players may be offline, please check your spelling.");
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				Player t1 = Bukkit.getServer().getPlayer(args[0]);
				Player t2 = Bukkit.getServer().getPlayer(args[1]);
				if ((t1 != null) && (t2 != null)) {
					t1.teleport(t2.getLocation());
					s.sendMessage("You sent " + t1.getName() + " to " + t2.getName() + ".");
					t1.sendMessage(ChatColor.AQUA + "You were sent to " + t2.getName() + "!");
					t2.sendMessage(ChatColor.AQUA + t1.getName() + " was sent to you!");
				}
				else {
					s.sendMessage("One or both of those players may be offline. Please check your spelling.");
				}
			}
		}	
			
		// /tphere
		//Teleport a player to you
		if (l.equalsIgnoreCase("tphere")) {
			if (s instanceof Player){
				if (args.length < 1) {
					s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tpheresyntax);
				}
				else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tphere")) {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					if (t != null) {
						t.teleport(((Player)s).getLocation());
						((Player)s).sendMessage(ChatColor.GREEN + "You brought " + t.getName() + " to you!");
						t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " teleported you!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName());
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.PlayerOffline);
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage("You must be a player!");
			}	
		}
		
		// /tpto
		//Teleport to a player
		if (l.equalsIgnoreCase("tpto")) {
			if (s instanceof Player) {
				if (args.length < 1) {
					s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tptosyntax);
				}
				else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tphere")) {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					if (t != null) {
						((Player)s).teleport(t.getLocation());
						((Player)s).sendMessage(ChatColor.GREEN + "You teleported to " + t.getName() + "!");
						t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " teleported to you!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.PlayerOffline);
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage("You must be a player!");
			}
		}
		
		// /jump
		//Jump to where you're looking at
		if (l.equalsIgnoreCase("jump")) {
			if (s instanceof Player) {					
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.jump")) {
					Location PELoc = ((Player)s).getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();	
					((Player)s).teleport(PELoc);
					((Player)s).sendMessage(ChatColor.GREEN + "You jumped!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage("You must be a player!");
			}
		}
		
		// /send
		//Send a person to where you're looking at
		if (l.equalsIgnoreCase("send")) {
			if (s instanceof Player) {
				if (args.length < 1) {
					((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.sendsyntax);
				}
				else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.send")) {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					if (t != null){
						Location PELoc = ((Player)s).getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();
						t.teleport(PELoc);
						((Player)s).sendMessage(ChatColor.GREEN + "You sent " + t.getName() + " to where you were looking!");
						t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent you to where he/she was looking.");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.PlayerOffline);
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage("You must be a player!");
			}
		}
		
		// /spawn
		//Teleports you to spawn
		if (l.equalsIgnoreCase("spawn")) {
			if (s instanceof Player) {
				Location spawn = ((Player)s).getWorld().getSpawnLocation();
				if (args.length < 1) {
					if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.spawn")) {
						((Player)s).teleport(spawn);
						((Player)s).sendMessage(ChatColor.GREEN + "You teleported to the spawn!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
					}
					else {
						((Player)s).sendMessage(MainCmd.MissingPerms);
					}
				}
				else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.spawn.others")) {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					if (t != null) {	
						t.teleport(spawn);
						((Player)s).sendMessage(ChatColor.GREEN + "You sent " + t.getName() + " to the spawn!");
						t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent you to the spawn.");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.PlayerOffline);
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else { 
				if (args.length < 1) {
					s.sendMessage("Usage: " + CommandList.spawnsyntax);
				}
				else {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					if (t != null) {
						Location spawn = t.getWorld().getSpawnLocation();
						t.teleport(spawn);
						s.sendMessage("You sent " + t.getName() + "to the spawn!");
						t.sendMessage(ChatColor.AQUA + "You were sent to the spawn.");
					}
					else {
						s.sendMessage(MainCmd.PlayerOffline);
					}
				}
			}
		}
		
		// /setspawn
		//Sets the spawn at your current location.
		if (l.equalsIgnoreCase("setspawn")) {
			if (s instanceof Player) {
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.setspawn")) {
					((Player)s).getWorld().setSpawnLocation((int)((Player)s).getLocation().getX(), (int)((Player)s).getLocation().getY(), (int)((Player)s).getLocation().getZ());
					((Player)s).sendMessage(ChatColor.GREEN + "You set the spawn to your current location!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage("You must be a player!");
			}
		}
		
		return false;
	}

}
