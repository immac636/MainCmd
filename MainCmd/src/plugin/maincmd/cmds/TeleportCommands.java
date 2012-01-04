package plugin.maincmd.cmds;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import plugin.maincmd.CommandList;
import plugin.maincmd.MainCmd;

public class TeleportCommands
	implements CommandExecutor
{
	Logger log = Logger.getLogger("Minecraft");
	Configuration config = MainCmd.plugin.getConfig();

	private void tp(CommandSender s, Command c, String l, String[] args) {
		if (args.length < 2) {
			if ((s instanceof Player)) {
				s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tpsyntax);
			}
			else {
				s.sendMessage("Usage: " + CommandList.tpsyntax);
			}
		}
		else if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tp")) {
				Player t1 = Bukkit.getServer().getPlayer(args[0]);
				Player t2 = Bukkit.getServer().getPlayer(args[1]);
				Chunk chunk = t2.getLocation().getChunk();
				if ((t1 != null) && (t2 != null)) {
					MainCmd.sendChunk(chunk, t1);
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
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else {
			Player t1 = Bukkit.getServer().getPlayer(args[0]);
			Player t2 = Bukkit.getServer().getPlayer(args[1]);
			Chunk chunk = t2.getLocation().getChunk();
			if ((t1 != null) && (t2 != null)) {
				MainCmd.sendChunk(chunk, t1);
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

	private void tphere(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (args.length < 1) {
				s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tpheresyntax);
			}
			else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tphere")) {
				Player t = Bukkit.getServer().getPlayer(args[0]);
				Chunk chunk = ((Player)s).getLocation().getChunk();
				if (t != null) {
					MainCmd.sendChunk(chunk, t);
					t.teleport(((Player)s).getLocation());
					((Player)s).sendMessage(ChatColor.GREEN + "You brought " + t.getName() + " to you!");
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " teleported you!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName());
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.PlayerOffline"));
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void tpto(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (args.length < 1) {
				s.sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.tptosyntax);
			}
			else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.tphere")) {
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if (t != null) {
					Chunk chunk = t.getLocation().getChunk();
					MainCmd.sendChunk(chunk, ((Player)s));
					((Player)s).teleport(t.getLocation());
					((Player)s).sendMessage(ChatColor.GREEN + "You teleported to " + t.getName() + "!");
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " teleported to you!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.PlayerOffline"));
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void jump(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.jump")) {
				Location PELoc = ((Player)s).getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();
				Chunk chunk = PELoc.getChunk();
				MainCmd.sendChunk(chunk, ((Player)s));
				((Player)s).teleport(PELoc);
				((Player)s).sendMessage(ChatColor.GREEN + "You jumped!");
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void send(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (args.length < 1) {
				((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.sendsyntax);
			}
			else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.send")) {
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if (t != null) {
					Location PELoc = ((Player)s).getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();
					Chunk chunk = ( PELoc.getChunk());
					MainCmd.sendChunk(chunk, t);
					t.teleport(PELoc);
					((Player)s).sendMessage(ChatColor.GREEN + "You sent " + t.getName() + " to where you were looking!");
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent you to where he/she was looking.");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.PlayerOffline"));
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void spawn(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			Location spawn = ((Player)s).getWorld().getSpawnLocation();
			if (args.length < 1) {
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.spawn")) {
					Chunk chunk = ( spawn.getChunk());
					MainCmd.sendChunk(chunk, ((Player)s));
					((Player)s).teleport(spawn);
					((Player)s).sendMessage(ChatColor.GREEN + "You teleported to the spawn!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
				}
			}
			else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.spawn.others")) {
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if (t != null) {
					Chunk chunk = ( spawn.getChunk());
					MainCmd.sendChunk(chunk, t);
					t.teleport(spawn);
					((Player)s).sendMessage(ChatColor.GREEN + "You sent " + t.getName() + " to the spawn!");
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " sent you to the spawn.");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.PlayerOffline"));
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}

		}
		else if (args.length < 1) {
			s.sendMessage("Usage: " + CommandList.spawnsyntax);
		}
		else {
			Player t = Bukkit.getServer().getPlayer(args[0]);
			if (t != null) {
				Location spawn = t.getWorld().getSpawnLocation();
				Chunk chunk = ( spawn.getChunk());
				MainCmd.sendChunk(chunk, t);
				t.teleport(spawn);
				s.sendMessage("You sent " + t.getName() + "to the spawn!");
				t.sendMessage(ChatColor.AQUA + "You were sent to the spawn.");
			}
			else {
				s.sendMessage(config.getString("Messages.PlayerOffline"));
			}
		}
	}

	private void setspawn(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.setspawn")) {
				((Player)s).getWorld().setSpawnLocation((int)((Player)s).getLocation().getX(), (int)((Player)s).getLocation().getY(), (int)((Player)s).getLocation().getZ());
				((Player)s).sendMessage(ChatColor.GREEN + "You set the spawn to your current location!");
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void home(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.home")) {
				if ((!config.contains("Players." + ((Player)s).getName() + ".home.x")) || (!config.contains("Players." + ((Player)s).getName() + ".home.y")) || 
					(!config.contains("Players." + ((Player)s).getName() + ".home.z")) || (!config.contains("Players." + ((Player)s).getName() + ".home.world"))) {
					((Player)s).sendMessage(ChatColor.RED + "Home is not set! Please use /sethome .");
				}
				else {
					double x = config.getDouble("Players." + ((Player)s).getName() + ".home.x");
					double y = config.getDouble("Players." + ((Player)s).getName() + ".home.y");
					double z = config.getDouble("Players." + ((Player)s).getName() + ".home.z");
					String world = config.getString("Players." + ((Player)s).getName() + ".home.world");
					Location home = new Location(Bukkit.getServer().getWorld(world), x, y, z);
					Chunk chunk = home.getChunk();
					MainCmd.sendChunk(chunk, ((Player)s));
					((Player)s).teleport(home);
					((Player)s).sendMessage(ChatColor.GREEN + "You teleported to your home!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void sethome(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.sethome")) {
				double x = ((Player)s).getLocation().getX();
				double y = ((Player)s).getLocation().getY();
				double z = ((Player)s).getLocation().getZ();
				String world = ((Player)s).getWorld().getName();
				config.set("Players." + ((Player)s).getName() + ".home.x", Double.valueOf(x));
				config.set("Players." + ((Player)s).getName() + ".home.y", Double.valueOf(y));
				config.set("Players." + ((Player)s).getName() + ".home.z", Double.valueOf(z));
				config.set("Players." + ((Player)s).getName() + ".home.world", world);
				MainCmd.plugin.saveConfig();
				((Player)s).sendMessage(ChatColor.GREEN + "Successfully set your home to your current location!");
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
				
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void warp(CommandSender s, Command c, String l, String[] args) { // TODO Add /warp others and console warp others
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.warp")) {
				if (args.length < 1) {
					String msg = MainCmd.plugin.configList(config, "Warps.");
					((Player)s).sendMessage(ChatColor.GOLD + "Warps: " + ChatColor.DARK_GREEN + msg);
				}
				else {
					String name = args[0].toLowerCase();
					if (name != null) {
						if ((config.contains("Warps." + name + ".x")) && (config.contains("Warps." + name + ".y")) && 
							(config.contains("Warps." + name + ".z")) && (config.contains("Warps." + name + ".world"))) {
							double x = config.getDouble("Warps." + name + ".x");
							double y = config.getDouble("Warps." + name + ".y");
							double z = config.getDouble("Warps." + name + ".z");
							String world = (String)config.get("Warps." + name + ".world");
							Location warp = new Location(Bukkit.getServer().getWorld(world), x, y, z);
							Chunk chunk = warp.getChunk();
							MainCmd.sendChunk(chunk, ((Player)s));
							((Player)s).teleport(warp);
							((Player)s).sendMessage(ChatColor.GREEN + "You teleported to the warp " + name + "!");
							log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + name);
						}
						else {
							((Player)s).sendMessage(ChatColor.RED + "Warp is not set! Please use /setwarp " + name + "!");
						}
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + "That is not a warp. Please check your spelling.");
					}
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void setwarp(CommandSender s, Command c, String l, String[] args)
	{
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.setwarp")) {
				if (args.length < 1) {
					((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.setwarpsyntax);
				}
				else {
					String name = args[0].toLowerCase();
					if (!config.contains("Warps." + name)) {
						double x = ((Player)s).getLocation().getX();
						double y = ((Player)s).getLocation().getY();
						double z = ((Player)s).getLocation().getZ();
						String world = ((Player)s).getWorld().getName();

						config.set("Warps." + name + ".x", Double.valueOf(x));
						config.set("Warps." + name + ".y", Double.valueOf(y));
						config.set("Warps." + name + ".z", Double.valueOf(z));
						config.set("Warps." + name + ".world", world);
						((Player)s).sendMessage(ChatColor.GREEN + "Warp " + name + " successfully set to your location!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + name);
						MainCmd.plugin.saveConfig();
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + "That warp name has been taken. Please use a different one.");
					}
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage(MainCmd.MustBePlayer);
	}

	private void rmwarp(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.teleport.rmwarp")) {
				if (args.length < 1) {
					((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.rmwarpsyntax);
				}
				else {
					String name = args[0].toLowerCase();
					if (config.contains("Warps." + name)) {
						config.set("Warps." + name, null);
						((Player)s).sendMessage(ChatColor.GREEN + "Warp " + name + " successfully removed!");
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + name);
						MainCmd.plugin.saveConfig();
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + "That is not a warp.");
					}
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}

		}
		else if (args.length < 1) {
			s.sendMessage("Usage: " + CommandList.rmwarpsyntax);
		}
		else {
			String name = args[0];
			if (config.contains("Warps." + name)) {
				config.set("Warps." + name, null);
				s.sendMessage("Warp " + name + " successfully removed!");
				MainCmd.plugin.saveConfig();
			}
			else {
				s.sendMessage(ChatColor.RED + "That is not a warp.");
			}
		}
	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args)
	{
		if ((s instanceof Player)) {
			log.info("[MainCmd] User " + ((Player)s).getName() + " used (or attempted to use) the command " + l.toString());
		}
		if (l.equalsIgnoreCase("tp")) {
			tp(s, c, l, args);
		}
		if ((l.equalsIgnoreCase("tphere")) || (l.equalsIgnoreCase("tph"))) {
			tphere(s, c, l, args);
		}
		if (l.equalsIgnoreCase("tpto")) {
			tpto(s, c, l, args);
		}
		if ((l.equalsIgnoreCase("jump")) || (l.equalsIgnoreCase("j"))) {
			jump(s, c, l, args);
		}
		if (l.equalsIgnoreCase("send")) {
			send(s, c, l, args);
		}
		if (l.equalsIgnoreCase("spawn")) {
			spawn(s, c, l, args);
		}
		if (l.equalsIgnoreCase("setspawn")) {
			setspawn(s, c, l, args);
		}
		if ((l.equalsIgnoreCase("home")) || (l.equalsIgnoreCase("h"))) {
			home(s, c, l, args);
		}
		if (l.equalsIgnoreCase("sethome")) {
			sethome(s, c, l, args);
		}
		if (l.equalsIgnoreCase("warp")) {
			warp(s, c, l, args);
		}
		if (l.equalsIgnoreCase("setwarp")) {
			setwarp(s, c, l, args);
		}
		if ((l.equalsIgnoreCase("removewarp")) || (l.equalsIgnoreCase("rmwarp"))) {
			rmwarp(s, c, l, args);
		}
		return false;
	}
}