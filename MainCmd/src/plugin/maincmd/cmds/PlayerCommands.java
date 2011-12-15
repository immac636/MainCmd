package plugin.maincmd.cmds;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import plugin.maincmd.CommandList;
import plugin.maincmd.MainCmd;
import plugin.maincmd.hashmaps.ItemHashmap;

public class PlayerCommands implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		FileConfiguration config = MainCmd.plugin.getConfig();
		// /give
		// Give an item
		if (l.equalsIgnoreCase("give")) {
			if (args.length < 1) {
				if (s instanceof Player) {
					((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.givesyntax);
				}
				else {
					s.sendMessage("Usage: " + CommandList.givesyntax);
				}
			}
			else {
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if (t != null) {
					PlayerInventory tinv = t.getInventory();
					if (s instanceof Player) {
						if(s.hasPermission("MainCmd.player.give")) {
							if (args.length < 2) {
								((Player)s).sendMessage(ChatColor.RED + "Please specify an item or block");
							}
							else {
								Material mat = ItemHashmap.mats.get(args[1].toLowerCase());
								if (mat != null) {
									if (args.length < 3) {
										ItemStack item = new ItemStack(mat, mat.getMaxStackSize());
										tinv.addItem(item);
										((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " a stack of " + item.getType() + "!");
										t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you a stack of " + item.getType() + "!");
									}	
									else {
										ItemStack item = new ItemStack(mat, Integer.parseInt(args[2]));
										tinv.addItem(item);
										((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " " + item.getAmount() + " " + item.getType() + "!");
										t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you " + item.getAmount() + " " + item.getType() + "!");
									}
								}
								else {
									((Player)s).sendMessage(MainCmd.InvalidMat);
								}
							}
						}
						else {
							((Player)s).sendMessage(MainCmd.MissingPerms);
						}
					}
					else {
						if (args.length < 2) {
							s.sendMessage("Please specify an item or block");
						}
						else {
							Material mat = ItemHashmap.mats.get(args[1].toLowerCase());
							if (mat != null) {
								if (args.length < 3) {
									ItemStack item = new ItemStack(mat, mat.getMaxStackSize());
									tinv.addItem(item);
									s.sendMessage("You gave " + t.getName() + " a stack of " + item.getType() + "!");
									t.sendMessage(ChatColor.AQUA + "You were given a stack of " + item.getType() + "!");
								}
								else {
									ItemStack item = new ItemStack(mat, Integer.parseInt(args[2]));
									tinv.addItem(item);
									s.sendMessage("You gave " + t.getName() + " " + item.getAmount() + " " + item.getType() + "!");
									t.sendMessage(ChatColor.AQUA + "You were given " + item.getAmount() + " " + item.getType() + "!");
								}
							}
							else {
								s.sendMessage(MainCmd.InvalidMat);
							}
						}
					}
				}
				else {
					if (s instanceof Player) {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.PlayerOffline);
					}
					else {
						s.sendMessage(MainCmd.PlayerOffline);
					}
				}
			}
		}
		
		// /i
		// Receive an item.
		if (l.equalsIgnoreCase("i") || l.equalsIgnoreCase("item")) {
			if (s instanceof Player) {
				PlayerInventory inv = ((Player)s).getInventory();
				if (args.length < 1) {
					((Player)s).sendMessage(ChatColor.RED + CommandList.isyntax);
				}
				else if (s.hasPermission("MainCmd.player.item")) {
					Material mat = ItemHashmap.mats.get(args[0].toLowerCase());
					if (mat != null) {
						if (args.length < 2) {
							ItemStack item = new ItemStack(mat, mat.getMaxStackSize());
							inv.addItem(item);
							((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself a stack of " + item.getType());
						}
						else {
							ItemStack item = new ItemStack(mat, Integer.parseInt(args[1]));
							inv.addItem(item);
							((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself " + item.getAmount() + " " + item.getType());
						}
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + MainCmd.InvalidMat);
					}
				}
				else {
					((Player)s).sendMessage(ChatColor.RED + MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage(MainCmd.MustBePlayer);
			}
		}
		
		// /god
		// Sets godmode on if player.god = false
		// sets godmode off if player.god = true
		if (l.equalsIgnoreCase("god")) {
			if (args.length < 1) {
				if ((s instanceof Player)) {
					((Player)s).sendMessage("/" + l.toString() + " [on/off]");
				}
				else {
					s.sendMessage("/god [on/off]");
				}
			}
			else if ((s instanceof Player)) {
				if (((Player)s).hasPermission("MainCmd.player.god")) {
					if (args[0].equalsIgnoreCase("on")) {
						config.set(((Player)s).getName() + ".god", Boolean.valueOf(true));
						((Player)s).sendMessage(ChatColor.GREEN + "Godmode enabled!");
						MainCmd.plugin.saveConfig();
					}
					if (args[0].equalsIgnoreCase("off")) {
						config.set(((Player)s).getName() + ".god", Boolean.valueOf(false));
						((Player)s).sendMessage(ChatColor.GREEN + "Godmode disabled!");
						MainCmd.plugin.saveConfig();
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage(ChatColor.RED + "You can't turn godmode on in-console.");
			}
		}
		return false;
	}
}
