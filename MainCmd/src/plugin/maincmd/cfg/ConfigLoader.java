package plugin.maincmd.cfg;

import org.bukkit.configuration.file.FileConfiguration;
import plugin.maincmd.MainCmd;

public class ConfigLoader {
	public void LoadConfigs() {
		FileConfiguration config = MainCmd.plugin.getConfig();
		config.options().header("##########################################\nWelcome to the MainCmd config file.\nPlease make yourself comfortable.\nMainCmd was created by Ian Mackay (immac636).\n##########################################");
		MainCmd.plugin.saveConfig();
		if (config.getString("Messages.joinmessage", null) == null) {
			config.set("Messages.joinmessage", "&2<pname> joined the game!");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.welcomemessage", null) == null) {
			config.set("Messages.welcomemessage", "&9Welcome back!");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.firstjoinmessage", null) == null) {
			config.set("Messages.firstjoinmessage", "&2<pname> has joined this server for the first time! Welcome!");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.firstwelcomemessage", null) == null) {
			config.set("Messages.firstwelcomemessage", "&9Welcome to the server!");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.quitmessage", null) == null) {
			config.set("Messages.quitmessage", "&e<pname> has left the game.");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.InvalidMaterial", null) == null) {
			config.set("Messages.InvalidMaterial", "That is not a block or an item. Please check your spelling.");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.InvalidData", null) == null) {
			config.set("Messages.InvalidData", "That data value doesn't work with that item.");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.MissingPermissions", null) == null) {
			config.set("Messages.MissingPermissions", "You do not have the required permissions to use this command!");
			MainCmd.plugin.saveConfig();
		}
		if (config.getString("Messages.PlayerOffline", null) == null) {
			config.set("Messages.PlayerOffline", "That player may be offline. Please check your spelling.");
			MainCmd.plugin.saveConfig();
		}
		if (config.get("Warps") == null) {
			config.createSection("Warps");
			MainCmd.plugin.saveConfig();
		}
		if (config.get("WarpsList") == null) {
			config.createSection("WarpsList");
			MainCmd.plugin.saveConfig();
		}
		if (config.get("MainCmd.spawnathome") == null) {
			config.set("MainCmd.spawnathome", true);
			MainCmd.plugin.saveConfig();
		}
	}
}