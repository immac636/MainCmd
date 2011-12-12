package plugin.maincmd.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import plugin.maincmd.CommandList;
import plugin.maincmd.MainCmd;

public class PlayerCommands implements CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (l.equalsIgnoreCase("give")) {
			Player t = Bukkit.getServer().getPlayer(args[0]);
			PlayerInventory tinv = t.getInventory();
			Material mat = Material.getMaterial(args[1]);
			if (args.length < 1) {
				if (s instanceof Player) {
					((Player)s).sendMessage(ChatColor.RED + "Usage: " + ChatColor.BLUE + CommandList.givesyntax);
				}
				else {
					s.sendMessage("Usage: " + CommandList.givesyntax);
				}
			}
			else {
				if (t != null) {
					if (s instanceof Player) {
						if(s.hasPermission("MainCmd.player.give")) {
							if (args.length < 2) {
								((Player)s).sendMessage(ChatColor.RED + "Please specify an item or block");
							}
							else {
								if (args.length < 3) {
									ItemStack item = new ItemStack(mat, 64);
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
							if (args.length < 3) {
								ItemStack item = new ItemStack(mat, 64);
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
		
		
		return false;
	}
}
