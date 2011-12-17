package plugin.maincmd;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;

public class Permissions {

     public static PermissionHandler permissionHandler;
     static Logger log = Logger.getLogger("Minecraft");
     
	 public static boolean permsCheck(Player player, String l) {
		 Plugin permissionsPlugin = MainCmd.plugin.getServer().getPluginManager().getPlugin("Permissions");
		 if (permissionsPlugin == null) {
			 return (player.hasPermission(l)) || (player.isOp());
		 }
		 return permissionHandler.has(player, l);
	 }
	 
	 public static void LoadPerms() {
		 if (permissionHandler != null) {
			 return;
		 }
		 Plugin permissionsPlugin = MainCmd.plugin.getServer().getPluginManager().getPlugin("Permissions");
		 if (permissionsPlugin == null) {
			 log.info("[MainCmd] PermissionsEX not found. Defaulting to Op.");
			 return;
		 }
		 log.info("[MainCmd] PermissionsEX found.");
	 }

}
