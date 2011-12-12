package plugin.maincmd;

<<<<<<< HEAD
import hashmaps.ItemHashmap;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.maincmd.cfg.ConfigLoader;
import plugin.maincmd.cmds.CommandLoader;
import plugin.maincmd.events.EventLoader;

public class MainCmd extends JavaPlugin {
	
	public static final String InvalidMat = "That is not a block or an item. Please check your spelling.";
	Logger log = Logger.getLogger("Minecraft");
	public static MainCmd plugin;
	public static String MissingPerms = ChatColor.RED + "You do not have the required permissions to use this command!";
	public static String PlayerOffline = "That player may be offline. Please check your spelling.";
	public static String MustBePlayer = "You must be a player to use this command!";
	public void onEnable() {
		plugin = this;
		CommandLoader.LoadCommands();
		EventLoader.LoadEvents();
		ConfigLoader.LoadConfigs();
		ItemHashmap.loadItems();
=======
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.maincmd.cfg.ConfigLoader;
import plugin.maincmd.cmds.CommandLoader;
import plugin.maincmd.events.EventLoader;

public class MainCmd extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	public static MainCmd plugin;
	public static String MissingPerms = ChatColor.RED + "You do not have the required permissions to use this command!";
	public static String PlayerOffline = "That player may be offline. Please check your spelling.";
	
	public void onEnable() {
		plugin = this;
		CommandLoader.LoadCommands();
		EventLoader.LoadEvents();
		ConfigLoader.LoadConfigs();
>>>>>>> branch 'master' of git@github.com:immac636/MainCmd.git
		log.info("MainCmd (Dev) has been enabled");
	}	
	public void onDisable() {
		log.info("MainCmd (Dev) is shutting down...");
	}
	
}
