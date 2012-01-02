package plugin.maincmd.events;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
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
			MainCmd.sendChunk((net.minecraft.server.Chunk) p.getLocation().getChunk(), p);
			String welcome = MainCmd.plugin.replaceColors(config.getString("Messages.welcomemessage"));
			String join = MainCmd.plugin.replaceColors(config.getString("Messages.joinmessage"));
			p.sendMessage(welcome);
			e.setJoinMessage(join.replaceAll("<pname>", pname));
		} else {
			MainCmd.sendChunk((net.minecraft.server.Chunk) p.getWorld().getSpawnLocation().getChunk(), p);
			String welcome = MainCmd.plugin.replaceColors(config.getString("Messages.firstwelcomemessage"));
			String join = MainCmd.plugin.replaceColors(config.getString("Messages.firstjoinmessage"));
			p.sendMessage(welcome);
			e.setJoinMessage(join.replaceAll("<pname>", pname));
			this.config.createSection("Players." + pname);
			MainCmd.plugin.saveConfig();
		}
	}
	public void onPlayerLogin(PlayerLoginEvent e) {
		if (e.getPlayer().isBanned()) {
			((Player)e).kickPlayer(config.getString("Players." + ((Player)e).getName() + ".banreason"));
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
		Player p = (Player) e;
		Location bed = p.getBedSpawnLocation();
		Location spawn = p.getWorld().getSpawnLocation();
		if (bed == null) {
			MainCmd.sendChunk((net.minecraft.server.Chunk) spawn.getChunk(), p) ;
		}
		else {
			MainCmd.sendChunk((net.minecraft.server.Chunk) bed.getChunk(), p);
		}
	}
}