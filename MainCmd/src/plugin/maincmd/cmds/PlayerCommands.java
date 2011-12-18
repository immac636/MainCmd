package plugin.maincmd.cmds;


import java.util.logging.Logger;

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

public class PlayerCommands implements CommandExecutor {
	Logger log = Logger.getLogger("Minecraft");
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			log.info("[MainCmd] User " + ((Player)s).getName() + " used (or attempted to use) the command " + l.toString());
		}
		@SuppressWarnings("unused")
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
						if(MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.give")) {
							if (args.length < 2) {
								((Player)s).sendMessage(ChatColor.RED + "Please specify an item or block");
							}
							else {
								String[] itemAndData = args[0].split(":");
							    int item = 0;
							    int damage = 0;
								Material mat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
								if (itemAndData.length == 2) {
					                try {
					                    damage = Integer.parseInt(itemAndData[1]);
					                } catch (NumberFormatException ex) {
					                    // Data was not an int, complain here
					                    ((Player)s).sendMessage(ChatColor.RED + MainCmd.InvalidData);
					                    return true;
					                }
					            }
								if (mat != null) {
									if (args.length < 3) {
										ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short) damage);
										tinv.addItem(itemStack);
										((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " a stack of " + mat.toString().toLowerCase() + "!");
										t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you a stack of " + mat.toString().toLowerCase() + "!");
										log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName() + " " + mat.toString().toLowerCase());
									}	
									else {
										ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[2]), (short) damage);
										tinv.addItem(itemStack);
										((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
										t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
										log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName() + " " + mat.toString().toLowerCase() + " " + Integer.parseInt(args[2]));
									}
								}
								else {
									((Player)s).sendMessage(ChatColor.RED + MainCmd.InvalidMat);
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
							String[] itemAndData = args[0].split(":");
						    int item = 0;
						    int damage = 0;
							Material mat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
							if (itemAndData.length == 2) {
				                try {
				                    damage = Integer.parseInt(itemAndData[1]);
				                } catch (NumberFormatException ex) {
				                    // Data was not an int, complain here
				                    s.sendMessage(MainCmd.InvalidData);
				                    return true;
				                }
				            }
							if (mat != null) {
								if (args.length < 3) {
									ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short) damage);
									tinv.addItem(itemStack);
									s.sendMessage("You gave " + t.getName() + " a stack of " + mat.toString().toLowerCase() + "!");
									t.sendMessage(ChatColor.AQUA + "You were given a stack of " + mat.toString().toLowerCase() + "!");
								}
								else {
									ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[2]), (short) damage);
									tinv.addItem(itemStack);
									s.sendMessage("You gave " + t.getName() + " " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
									t.sendMessage(ChatColor.AQUA + "You were given " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
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
				else if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.item")) {
					String[] itemAndData = args[0].split(":");
				    int item = 0;
				    int damage = 0;
					Material mat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
					if (itemAndData.length == 2) {
		                try {
		                    damage = Integer.parseInt(itemAndData[1]);
		                } catch (NumberFormatException ex) {
		                    // Data was not an int, complain here
		                    ((Player)s).sendMessage(ChatColor.RED + MainCmd.InvalidData);
		                    return true;
		                }
		            }
					if (mat != null) {
						if (args.length < 2) {
							ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short) damage);
							inv.addItem(itemStack);
							((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself a stack of " + mat.toString().toLowerCase());
							log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + mat.toString().toLowerCase());
						}
						else {
							ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[1]), (short) damage);
							inv.addItem(itemStack);
							((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself " + itemStack.getAmount() + " " + mat.toString().toLowerCase());
							log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + mat.toString().toLowerCase() + " " + Integer.parseInt(args[1]));
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
		/*f (l.equalsIgnoreCase("god")) {
			if (args.length < 1) {
				if ((s instanceof Player)) {
					((Player)s).sendMessage("/" + l.toString() + " [on/off]");
				}
				else {
					s.sendMessage("/god [on/off]");
				}
			}
			else if ((s instanceof Player)) {
				if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.god")) {
					if (args[0].equalsIgnoreCase("on")) {
						config.set(((Player)s).getName() + ".god", Boolean.valueOf(true));
						((Player)s).sendMessage(ChatColor.GREEN + "Godmode enabled!");
						MainCmd.plugin.saveConfig();
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " on");
					}
					if (args[0].equalsIgnoreCase("off")) {
						config.set(((Player)s).getName() + ".god", Boolean.valueOf(false));
						((Player)s).sendMessage(ChatColor.GREEN + "Godmode disabled!");
						MainCmd.plugin.saveConfig();
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " off");
					}
				}
				else {
					((Player)s).sendMessage(MainCmd.MissingPerms);
				}
			}
			else {
				s.sendMessage(ChatColor.RED + "You can't turn godmode on in-console.");
			}
		}*/
		return false;
	}
}
