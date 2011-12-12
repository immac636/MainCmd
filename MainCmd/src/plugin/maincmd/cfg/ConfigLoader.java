package plugin.maincmd.cfg;

import plugin.maincmd.MainCmd;

public class ConfigLoader {
	public static void LoadConfigs() {
		MainCmd.plugin.getConfig().options().header("##########################################\nWelcome to the MainCmd config file. Please make yourself comfortable.\nMainCmd was created by Ian Mackay (immac636) at an early hour.\nIf it sucks, blame lack of sleep.\n#################################################");
		MainCmd.plugin.saveConfig();
	}
}
