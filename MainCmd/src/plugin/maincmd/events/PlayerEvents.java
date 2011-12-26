package plugin.maincmd.events;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import plugin.maincmd.MainCmd;

public class PlayerEvents extends PlayerListener {
	
	FileConfiguration config = MainCmd.plugin.getConfig();
	File players = new File(MainCmd.plugin.getDataFolder(), "players.yml");
	Logger log = Logger.getLogger("Minecraft");

	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		if (this.config.getConfigurationSection("Players." + pname) != null) {
			String welcome = MainCmd.plugin.replaceColors(config.getString("Messages.welcomemessage"));
			String join = MainCmd.plugin.replaceColors(config.getString("Messages.joinmessage"));
			p.sendMessage(welcome);
			e.setJoinMessage(join.replaceAll("<pname>", pname));
		}
		else {
			String welcome = MainCmd.plugin.replaceColors(config.getString("Messages.firstwelcomemessage"));
			String join = MainCmd.plugin.replaceColors(config.getString("Messages.firstjoinmessage"));
			p.sendMessage(welcome);
			e.setJoinMessage(join.replaceAll("<pname>", pname));
			this.config.createSection("Players." + pname);
			MainCmd.plugin.saveConfig();
		}
	}

	public void onPlayerChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (MainCmd.plugin.permsCheck(p, "MainCmd.chatcolor")) {
			String msg = MainCmd.plugin.replaceColors(e.getMessage());
			e.setMessage(msg);
		}
		else {
			String msg = e.getMessage().replaceAll("(?i)&([a-f0-9])", "");
			e.setMessage(msg);
		}
	}

	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();

		String quit = MainCmd.plugin.replaceColors(config.getString("Messages.quitmessage"));
		e.setQuitMessage(quit.replaceAll("<pname>", pname));
		MainCmd.plugin.saveConfig();
	}
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (config.getBoolean("MainCmd.spawnathome", true)) {
			if ((config.contains("Players." + ((Player)e).getName() + ".home.x")) && (config.contains("Players." + ((Player)e).getName() + ".home.y")) && 
					(config.contains("Players." + ((Player)e).getName() + ".home.z")) && (config.contains("Players." + ((Player)e).getName() + ".home.world"))) {
				double x = config.getDouble("Players." + ((Player)e).getName() + ".home.x");
				double y = config.getDouble("Players." + ((Player)e).getName() + ".home.y");
				double z = config.getDouble("Players." + ((Player)e).getName() + ".home.z");
				String world = (String)config.get("Players." + ((Player)e).getName() + ".home.world");
				Location home = new Location(Bukkit.getServer().getWorld(world), x, y, z); 
				e.setRespawnLocation(home);
			}
			else {
				((Player)e).sendMessage(ChatColor.GOLD + "Home not set! Spawned at the default spawn!");
			}
		}
	}
}