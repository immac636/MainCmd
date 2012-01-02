package plugin.maincmd.cmds;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import plugin.maincmd.CommandList;
import plugin.maincmd.MainCmd;

public class PlayerCommands
	implements CommandExecutor
{
	Logger log = Logger.getLogger("Minecraft");

	Configuration config = MainCmd.plugin.getConfig();

	private void give(CommandSender s, Command c, String l, String[] args) { if (args.length < 1) {
			if ((s instanceof Player)) {
				((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.givesyntax);
			}
			else
				s.sendMessage("Usage: " + CommandList.givesyntax);
		}
		else
		{
			Player t = Bukkit.getServer().getPlayer(args[0]);
			if (t != null) {
				PlayerInventory tinv = t.getInventory();
				if ((s instanceof Player)) {
					if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.give")) {
						if (args.length < 2) {
							((Player)s).sendMessage(ChatColor.RED + "Please specify an item or block");
						}
						else {
							String[] itemAndData = args[1].split(":");
							int item = 0;
							int damage = 0;
							Material mat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
							if (itemAndData.length == 2) {
								try {
									damage = Integer.parseInt(itemAndData[1]);
								}
								catch (NumberFormatException ex) {
									((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.InvalidData"));
								}
							}
							if (mat != null) {
								if (args.length < 3) {
									ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short)damage);
									tinv.addItem(new ItemStack[] { itemStack });
									((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " a stack of " + mat.toString().toLowerCase() + "!");
									t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you a stack of " + mat.toString().toLowerCase() + "!");
									log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName() + " " + mat.toString().toLowerCase());
								}
								else {
									ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[2]), (short)damage);
									tinv.addItem(new ItemStack[] { itemStack });
									((Player)s).sendMessage(ChatColor.GREEN + "You gave " + t.getName() + " " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
									t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " gave you " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
									log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + t.getName() + " " + mat.toString().toLowerCase() + " " + Integer.parseInt(args[2]));
								}
							}
							else {
								((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.InvalidMaterial"));
							}
						}
					}
					else {
						((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
					}

				}
				else if (args.length < 2) {
					s.sendMessage("Please specify an item or block");
				}
				else {
					String[] itemAndData = args[1].split(":");
					int item = 0;
					int damage = 0;
					Material mat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
					if (itemAndData.length == 2) {
						try {
							damage = Integer.parseInt(itemAndData[1]);
						}
						catch (NumberFormatException ex) {
							s.sendMessage(config.getString("Messages.InvalidData"));
						}
					}
					if (mat != null) {
						if (args.length < 3) {
							ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short)damage);
							tinv.addItem(new ItemStack[] { itemStack });
							s.sendMessage("You gave " + t.getName() + " a stack of " + mat.toString().toLowerCase() + "!");
							t.sendMessage(ChatColor.AQUA + "You were given a stack of " + mat.toString().toLowerCase() + "!");
						}
						else {
							ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[2]), (short)damage);
							tinv.addItem(new ItemStack[] { itemStack });
							s.sendMessage("You gave " + t.getName() + " " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
							t.sendMessage(ChatColor.AQUA + "You were given " + itemStack.getAmount() + " " + mat.toString().toLowerCase() + "!");
						}
					}
					else {
						s.sendMessage(config.getString("Messages.InvalidMaterial"));
					}

				}

			}
			else if ((s instanceof Player)) {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.PlayerOffline"));
			}
			else {
				s.sendMessage(config.getString("Messages.PlayerOffline"));
			}
		} }

	private void item(CommandSender s, Command c, String l, String[] args)
	{
		if ((s instanceof Player)) {
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
					}
					catch (NumberFormatException ex) {
						((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.InvalidData"));
					}
				}
				if (mat != null) {
					if (args.length < 2) {
						ItemStack itemStack = new ItemStack(mat, mat.getMaxStackSize(), (short)damage);
						inv.addItem(new ItemStack[] { itemStack });
						((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself a stack of " + mat.toString().toLowerCase());
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + mat.toString().toLowerCase());
					}
					else {
						ItemStack itemStack = new ItemStack(mat, Integer.parseInt(args[1]), (short)damage);
						inv.addItem(new ItemStack[] { itemStack });
						((Player)s).sendMessage(ChatColor.GREEN + "You gave yourself " + itemStack.getAmount() + " " + mat.toString().toLowerCase());
						log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " " + mat.toString().toLowerCase() + " " + Integer.parseInt(args[1]));
					}
				}
				else
					((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.InvalidMaterial"));
			}
			else
			{
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else {
			s.sendMessage(MainCmd.MustBePlayer);
		}
	}

	private void god(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.god")) {
				if (config.getBoolean("Players." + ((Player)s).getName() + ".god")) {
					config.set("Players." + ((Player)s).getName() + ".god", Boolean.valueOf(false));
					((Player)s).sendMessage(ChatColor.GREEN + "Godmode disabled!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " (Disabled)");
				}
				else {
					config.set("Players." + ((Player)s).getName() + ".god", Boolean.valueOf(true));
					((Player)s).sendMessage(ChatColor.GREEN + "Godmode enabled!");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " (Enabled)");
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		}
		else
			s.sendMessage("Use /god (player) in-console");
	}

	private void godOthers(CommandSender s, Command c, String l, String[] args) {
		Player t = Bukkit.getServer().getPlayer(args[0]);
		if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.god.others")) {
			if (t != null) {
				if (config.getBoolean("Players." + t.getName() + ".god")) {
					config.set("Players." + t.getName() + ".god", Boolean.valueOf(false));
					((Player)s).sendMessage(ChatColor.GREEN + "God disabled for " + t.getName());
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " disabled god for you.");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " (Disabled)");
				}
				else {
					config.set("Players." + t.getName() + ".god", Boolean.valueOf(true));
					((Player)s).sendMessage(ChatColor.GREEN + "God enabled for " + t.getName());
					t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " enabled god for you.");
					log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + " (Enabled)");
				}
			}
			else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.OfflinePlayer"));
			}
		}
		else
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
	}

	private void godOthersConsole(CommandSender s, Command c, String l, String[] args) {
		Player t = Bukkit.getServer().getPlayer(args[0]);
		if (t != null) {
			if (config.getBoolean("Players." + t.getName() + ".god")) {
				config.set("Players." + t.getName() + ".god", Boolean.valueOf(false));
				s.sendMessage(ChatColor.GREEN + "God disabled for " + t.getName());
				t.sendMessage(ChatColor.AQUA + "Your godmode was disabled.");
			}
			else {
				config.set("Players." + t.getName() + ".god", Boolean.valueOf(true));
				s.sendMessage(ChatColor.GREEN + "God enabled for " + t.getName());
				t.sendMessage(ChatColor.AQUA + "Your godmode was enabled.");
			}
		}
		else
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.OfflinePlayer"));
	}
	private void creative(CommandSender s, Command c, String l, String[] args) {
		if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.creative")) {
			((Player)s).setGameMode(GameMode.CREATIVE);
			((Player)s).sendMessage(ChatColor.GREEN + "Switched to creative!");
			log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
		} else {
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
		}
	}
	private void creativeOthers(CommandSender s, Command c, String l, String[] args) {
		if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.creative.others")) {
			Player t = Bukkit.getPlayer(args[0]);
			t.setGameMode(GameMode.CREATIVE);
			((Player)s).sendMessage(ChatColor.GREEN + "Switched " + t.getName() + " to creative.");
			t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " switched you to creative!");
			log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
		} else {
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
		}
	}
	private void creativeConsole(CommandSender s, Command c, String l, String[] args) {
		Player t = Bukkit.getPlayer(args[0]);
		t.setGameMode(GameMode.CREATIVE);
		s.sendMessage("Switched " + t.getName() + " to creative.");
		t.sendMessage(ChatColor.AQUA + "You were switched to creative!");
	}
	private void survival(CommandSender s, Command c, String l, String[] args) {
		if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.survival")) {
			((Player)s).setGameMode(GameMode.SURVIVAL);
			((Player)s).sendMessage(ChatColor.GREEN + "Switched to survival!");
			log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
		} else {
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
		}
	}
	private void survivalOthers(CommandSender s, Command c, String l, String[] args) {
		if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.survival.others")) {
			Player t = Bukkit.getPlayer(args[0]);
			t.setGameMode(GameMode.SURVIVAL);
			((Player)s).sendMessage(ChatColor.GREEN + "Switched " + t.getName() + " to survival.");
			t.sendMessage(ChatColor.AQUA + ((Player)s).getName() + " switched you to survival!");
			log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString() + t.getName());
		} else {
			((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
		}
	}
	private void survivalConsole(CommandSender s, Command c, String l, String[] args) {
		Player t = Bukkit.getPlayer(args[0]);
		t.setGameMode(GameMode.SURVIVAL);
		s.sendMessage("Switched " + t.getName() + " to survival.");
		t.sendMessage(ChatColor.AQUA + "You were switched to survival!");
	}
	private void afk(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.afk")) {
				String playername = ((Player)s).getDisplayName();
				String pname = ((Player)s).getName();
				if (config.contains("Players." + pname + ".afk")) {
					if (config.getBoolean("Players." + pname + ".afk")) {
						config.set("Players." + pname + ".afk", Boolean.valueOf(false));
						((Player)s).setDisplayName(config.getString("Players." + pname + ".displayname"));
						((Player)s).sendMessage(ChatColor.GREEN + "You are no longer afk!");
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + pname + " is no longer afk.");
						log.info("[MainCmd] " + pname + " succesfully used the command /" + l.toString() + " off");
					}
					else {
						config.set("Players." + pname + ".displayname", playername);
						config.set("Players." + pname + ".afk", Boolean.valueOf(true));
						((Player)s).setDisplayName(ChatColor.GRAY + playername + "[AFK]");
						((Player)s).sendMessage(ChatColor.GREEN + "You are now afk!");
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + pname + " is now afk.");
						log.info("[MainCmd] " + pname + " succesfully used the command /" + l.toString() + " on");
					}
				}
				else {
					config.set("Players." + pname + ".displayname", playername);
					config.set("Players." + pname + ".afk", Boolean.valueOf(true));
					((Player)s).setDisplayName(ChatColor.GRAY + playername + "[AFK]");
					((Player)s).sendMessage(ChatColor.GREEN + "You are now afk!");
					Bukkit.broadcastMessage(ChatColor.DARK_AQUA + pname + " is now afk.");
					log.info("[MainCmd] " + pname + " succesfully used the command /" + l.toString() + " on");
				}
			} else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		} else s.sendMessage("You can't use /afk from console!");
		// ((Player)s).sendMessage(ChatColor.DARK_RED + "AFK Is not yet implemented, nag immac636 to put it in :(");
	}
	private void heal(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.heal")) {
				((Player)s).setHealth(20);
				((Player)s).sendMessage(ChatColor.GREEN + "You have been healed!");
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
			} else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		} else s.sendMessage("You can't use /heal from console!"); 
	}
	private void feed(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			if (MainCmd.plugin.permsCheck((Player)s, "MainCmd.player.feed")) {
				((Player)s).setFoodLevel(20);
				((Player)s).sendMessage(ChatColor.GREEN + "You have been fed!");
				log.info("[MainCmd] " + ((Player)s).getName() + " succesfully used the command /" + l.toString());
			} else {
				((Player)s).sendMessage(ChatColor.RED + config.getString("Messages.MissingPermissions"));
			}
		} else s.sendMessage("You can't use /feed from console!"); 
	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if ((s instanceof Player)) {
			log.info("[MainCmd] User " + ((Player)s).getName() + " used (or attempted to use) the command " + l.toString());
		}
		if (l.equalsIgnoreCase("give")) {
			give(s, c, l, args);
		}
		if ((l.equalsIgnoreCase("i")) || (l.equalsIgnoreCase("item"))) {
			item(s, c, l, args);
		}
		if (l.equalsIgnoreCase("god")) {
			if (args.length < 1) {
				god(s, c, l, args);
			}
			else if ((s instanceof Player)) {
				godOthers(s, c, l, args);
			}
			else {
				godOthersConsole(s, c, l, args);
			}
		}

		if (l.equalsIgnoreCase("creative")) {
			if (args.length < 1) {
				if ((s instanceof Player))
					creative(s, c, l, args);
				else {
					s.sendMessage("Usage: /creative (player)");
				}
			}
			else if ((s instanceof Player)) {
				creativeOthers(s, c, l, args);
			}
			else {
				creativeConsole(s, c, l, args);
			}
		}

		if (l.equalsIgnoreCase("survival")) {
			if (args.length < 1) {
				if ((s instanceof Player))
					survival(s, c, l, args);
				else {
					s.sendMessage("Usage: /survival (player)");
				}
			}
			else if ((s instanceof Player)) {
				survivalOthers(s, c, l, args);
			}
			else {
				survivalConsole(s, c, l, args);
			}
		}

		if (l.equalsIgnoreCase("afk")) {
			afk(s, c, l, args);
		}
		if (l.equalsIgnoreCase("heal")) {
			heal(s, c, l, args);
		}
		if (l.equalsIgnoreCase("feed")) {
			feed(s, c, l, args);
		}
		return false;
	}
}