package plugin.maincmd.cmds;

import org.bukkit.command.CommandExecutor;
import plugin.maincmd.MainCmd;

public class CommandLoader {
	public void LoadCommands() {
		CommandExecutor Help = new MainCommands();
		MainCmd.plugin.getCommand("maincmd").setExecutor(Help);

		CommandExecutor Teleport = new TeleportCommands();
		MainCmd.plugin.getCommand("tp").setExecutor(Teleport);
		MainCmd.plugin.getCommand("tphere").setExecutor(Teleport);
		MainCmd.plugin.getCommand("tpto").setExecutor(Teleport);
		MainCmd.plugin.getCommand("jump").setExecutor(Teleport);
		MainCmd.plugin.getCommand("send").setExecutor(Teleport);
		MainCmd.plugin.getCommand("spawn").setExecutor(Teleport);
		MainCmd.plugin.getCommand("setspawn").setExecutor(Teleport);
		MainCmd.plugin.getCommand("home").setExecutor(Teleport);
		MainCmd.plugin.getCommand("sethome").setExecutor(Teleport);
		MainCmd.plugin.getCommand("warp").setExecutor(Teleport);
		MainCmd.plugin.getCommand("setwarp").setExecutor(Teleport);
		MainCmd.plugin.getCommand("removewarp").setExecutor(Teleport);
		MainCmd.plugin.getCommand("rmwarp").setExecutor(Teleport);

		CommandExecutor Test = new TestCommands();
		MainCmd.plugin.getCommand("boom").setExecutor(Test);
		MainCmd.plugin.getCommand("ping").setExecutor(Test);
		MainCmd.plugin.getCommand("say").setExecutor(Test);

		CommandExecutor Player = new PlayerCommands();
		MainCmd.plugin.getCommand("give").setExecutor(Player);
		MainCmd.plugin.getCommand("i").setExecutor(Player);
		MainCmd.plugin.getCommand("item").setExecutor(Player);
		MainCmd.plugin.getCommand("god").setExecutor(Player);
		MainCmd.plugin.getCommand("creative").setExecutor(Player);
		MainCmd.plugin.getCommand("survival").setExecutor(Player);
		MainCmd.plugin.getCommand("afk").setExecutor(Player);
		MainCmd.plugin.getCommand("heal").setExecutor(Player);
		MainCmd.plugin.getCommand("feed").setExecutor(Player);
		
		CommandExecutor Admin = new AdminCommands();
		MainCmd.plugin.getCommand("kick").setExecutor(Admin);
		MainCmd.plugin.getCommand("ban").setExecutor(Admin);
		MainCmd.plugin.getCommand("unban").setExecutor(Admin);
		MainCmd.plugin.getCommand("admin").setExecutor(Admin);
		MainCmd.plugin.getCommand("mute").setExecutor(Admin);
		MainCmd.plugin.getCommand("unmute").setExecutor(Admin);
		
	}
}