package plugin.maincmd;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import plugin.maincmd.cfg.ConfigLoader;
import plugin.maincmd.cmds.CommandLoader;
import plugin.maincmd.events.EventLoader;

public class MainCmd extends JavaPlugin {
	
	public static final String InvalidMat = "That is not a block or an item. Please check your spelling.";
	public static final String InvalidData = "That data value doesn't work with that item.";
	Logger log = Logger.getLogger("Minecraft");
	public static MainCmd plugin;
	public static String MissingPerms = ChatColor.RED + "You do not have the required permissions to use this command!";
	public static String PlayerOffline = "That player may be offline. Please check your spelling.";
	public static String MustBePlayer = "You must be a player to use this command!";
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
		log.info("MainCmd Version " + MainCmd.plugin.getDescription().getVersion() + " started up!");
	}
	
	public void onDisable() {
		log.info("MainCmd is shutting down...");
		plugin.saveConfig();
	}
	
	private void LoadPerms() {
	    Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (this.Permissions == null) {
	        if (test != null) {
	            UsePermissions = true;
	            this.Permissions = ((Permissions) test).getHandler();
	            System.out.println("MainCmd] Permissions system detected!");
	        } else {
	            log.info("[MainCmd] Permission system not detected, defaulting to OP");
	            UsePermissions = false;
	        }
	    }
	}
	
    public boolean permsCheck(Player p, String string) {
        if (UsePermissions == true) {
            return this.Permissions.has(p, string);
        }
        if(p.hasPermission("MainCmd.*")) {
        	return true;
        } else {
            return p.hasPermission(string);	
        }
    }
    
}
