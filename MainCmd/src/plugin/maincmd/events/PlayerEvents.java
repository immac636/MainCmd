package plugin.maincmd.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import plugin.maincmd.MainCmd;

public class PlayerEvents extends PlayerListener{
	FileConfiguration config = MainCmd.plugin.getConfig();
	//On Join
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		e.setJoinMessage(ChatColor.GREEN + pname + " joined the game!");
		if(p.hasPlayedBefore() == true) {
			p.sendMessage(ChatColor.BLUE + "Welcome back, " + pname);
			if(config.getConfigurationSection(pname) == null) {
				config.createSection(pname);
				MainCmd.plugin.saveConfig();
			}
		}
		if(p.hasPlayedBefore() == false) {
			e.setJoinMessage(ChatColor.GREEN + pname + " joined the game!");
			p.sendMessage(ChatColor.BLUE + "Welcome to the server, " + pname);
			config.createSection(pname);
			MainCmd.plugin.saveConfig();
		}
	}
	
	//On Leave
	public void onPlayerQuit(PlayerQuitEvent e) {
		// Should put code for a removegod(or other thing)onquit
		e.setQuitMessage(ChatColor.DARK_GREEN + e.getPlayer().getName() + " has left the game.");
	} 
}
