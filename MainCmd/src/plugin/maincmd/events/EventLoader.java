package plugin.maincmd.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import plugin.maincmd.MainCmd;

public class EventLoader {
	MainCmd events;

	public void LoadEvents() {
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, new PlayerEvents(), Event.Priority.Normal, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_CHAT, new PlayerEvents(), Event.Priority.Normal, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, new PlayerEvents(), Event.Priority.Normal, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.FOOD_LEVEL_CHANGE, new EntityEvents(), Event.Priority.High, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new EntityEvents(), Event.Priority.High, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, new PlayerEvents(), Event.Priority.High, MainCmd.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_LOGIN, new PlayerEvents(), Event.Priority.High, MainCmd.plugin);
	}
}