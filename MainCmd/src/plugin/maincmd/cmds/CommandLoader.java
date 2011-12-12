package plugin.maincmd.cmds;

import org.bukkit.command.CommandExecutor;
import plugin.maincmd.MainCmd;

public class CommandLoader {
	public static void LoadCommands() {
		//Main Commands
		CommandExecutor Help = new MainCommands();
		MainCmd.plugin.getCommand("maincmd").setExecutor(Help);
		//Teleport Commands
		CommandExecutor Teleport = new TeleportCommands();
		MainCmd.plugin.getCommand("tp").setExecutor(Teleport);
		MainCmd.plugin.getCommand("tphere").setExecutor(Teleport);
		MainCmd.plugin.getCommand("tpto").setExecutor(Teleport);
		MainCmd.plugin.getCommand("jump").setExecutor(Teleport);
		MainCmd.plugin.getCommand("send").setExecutor(Teleport);
		MainCmd.plugin.getCommand("spawn").setExecutor(Teleport);
		MainCmd.plugin.getCommand("setspawn").setExecutor(Teleport);
		
		CommandExecutor Test = new TestCommands();
		MainCmd.plugin.getCommand("boom").setExecutor(Test);
		MainCmd.plugin.getCommand("ping").setExecutor(Test);
		
		CommandExecutor Player = new PlayerCommands();
		MainCmd.plugin.getCommand("give").setExecutor(Player);
		MainCmd.plugin.getCommand("i").setExecutor(Player);
		MainCmd.plugin.getCommand("item").setExecutor(Player);
	}
}
