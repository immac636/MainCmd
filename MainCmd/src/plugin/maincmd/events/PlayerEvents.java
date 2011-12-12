package plugin.maincmd.events;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents extends PlayerListener{
	
	//On Join
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.GREEN + e.getPlayer().getName() + " joined the game!");
		e.getPlayer().sendMessage(ChatColor.BLUE + "Welcome to the server, " + e.getPlayer().getName());
	}
	
	//On Leave
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.DARK_GREEN + e.getPlayer().getName() + " has left the game.");
	}
}
