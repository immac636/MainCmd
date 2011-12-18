package plugin.maincmd.events;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import plugin.maincmd.MainCmd;

public class PlayerEvents extends PlayerListener{
	FileConfiguration config = MainCmd.plugin.getConfig();
	Logger log = Logger.getLogger("Minecraft");
	//On Join
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		e.setJoinMessage(ChatColor.GREEN + pname + " " + config.getString("Messages.joinmessage"));
		if(p.hasPlayedBefore() == true) {
			p.sendMessage(ChatColor.BLUE + config.getString("Messages.welcomemessage"));
			if(config.getConfigurationSection(pname) == null) {
				config.createSection(pname);
				MainCmd.plugin.saveConfig();
			}
		}
		if(p.hasPlayedBefore() == false) {
			e.setJoinMessage(ChatColor.GREEN + pname + config.getString("Messages.firstjoinmessage"));
			p.sendMessage(ChatColor.BLUE + config.getString("Messages.firstwelcomemessage"));
			config.createSection(pname);
			MainCmd.plugin.saveConfig();
		}
	}
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.toString();
		log.info("[testevent] " + e.getPlayer().getName() + " used the command " + cmd);
	}
	
	//On Leave
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		// Should put code for a removegod(or other thing)onquit
		e.setQuitMessage(ChatColor.DARK_GREEN + pname + " " + config.getString("Messages.quitmessage"));
	} 
}
