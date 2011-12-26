package plugin.maincmd;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.maincmd.cfg.ConfigLoader;
import plugin.maincmd.cmds.CommandLoader;
import plugin.maincmd.events.EventLoader;

public class MainCmd extends JavaPlugin
{
	Logger log = Logger.getLogger("Minecraft");
	public static MainCmd plugin;
	public static String MustBePlayer = "You must be a player to use this command!";
	public static Object configFile;
	public File folder = new File("plugins" + File.separator + "MainCmd");
	public File userDataFolder = new File(folder, "Players");
	CommandLoader CommandLoader = new CommandLoader();
	EventLoader EventLoader = new EventLoader();
	ConfigLoader ConfigLoader = new ConfigLoader();
	public boolean UsePermissions;
	public PermissionHandler Permissions;

	public void onEnable() {
		plugin = this;
		CommandLoader.LoadCommands();
		EventLoader.LoadEvents();
		ConfigLoader.LoadConfigs();
		LoadPerms();
		log.info("MainCmd Version " + plugin.getDescription().getVersion() + " started up!");
	}
	public void onDisable() {
		log.info("MainCmd is shutting down...");
	}

	private void LoadPerms() {
		Plugin test = getServer().getPluginManager().getPlugin("Permissions");
		if (Permissions == null) {
			if (test != null) {
				UsePermissions = true;
				Permissions = ((Permissions)test).getHandler();
				System.out.println("MainCmd] Permissions system detected!");
			}
			else {
				log.info("[MainCmd] Permission system not detected, defaulting to OP");
				UsePermissions = false;
			}
		}
	}
	public String getFileContents(File file) {
		
		StringBuilder s = new StringBuilder();
		try	{
			BufferedReader input =  new BufferedReader(new FileReader(file));
			while(true) {
				String l_line = input.readLine();
				if(l_line == null) {
					break;
				}
				s.append(l_line + ", ");
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "There was an error performing this command";
		}
		return s.toString();
	}
	
	public void fileWriter(String string, File file) {
		try{
			BufferedWriter input = new BufferedWriter(new FileWriter(file));
			input.newLine();
			input.write(string.toString().toLowerCase());
			System.out.println("Attempting to save " + string + " to " + file); // Debug
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean permsCheck(Player p, String string) {
		if (UsePermissions) {
			return Permissions.has(p, string);
		}
		if (p.hasPermission("MainCmd.*")) {
			return true;
		}

		return p.hasPermission(string);
	}

	public String replaceColors(String String) {
		return String.replaceAll("(?i)&([a-f0-9])", "§$1");
	}
}