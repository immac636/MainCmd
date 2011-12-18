package plugin.maincmd.cfg;

import org.bukkit.configuration.file.FileConfiguration;

import plugin.maincmd.MainCmd;

public class ConfigLoader {
	public void LoadConfigs() {
		FileConfiguration config = MainCmd.plugin.getConfig();
		config.options().header("##########################################\nWelcome to the MainCmd config file.#\nPlease make yourself comfortable.#\nMainCmd was created by Ian Mackay (immac636) at an early hour.#\nIf it sucks, blame lack of sleep.#\n##########################################");
		MainCmd.plugin.saveConfig();
		if(config.getString("Messages.joinmessage") == null) {
			config.set("Messages.joinmessage", "joined the game!");
			MainCmd.plugin.saveConfig();
		}
		if(config.getString("Messages.welcomemessage") == null) {
			config.set("Messages.welcomemessage", "Welcome back!");
			MainCmd.plugin.saveConfig();
		}
		if(config.getString("Messages.firstjoinmessage") == null) {
			config.set("Messages.firstjoinmessage", "has joined this server for the first time! Welcome!");
			MainCmd.plugin.saveConfig();
		}
		if(config.getString("Messages.firstwelcomemessage") == null) {
			config.set("Messages.firstwelcomemessage", "Welcome to the server!");
			MainCmd.plugin.saveConfig();
		}
		if(config.getString("Messages.quitmessage") == null) {
			config.set("Messages.quitmessage", "has left the game.");
			MainCmd.plugin.saveConfig();
		}
	}
}