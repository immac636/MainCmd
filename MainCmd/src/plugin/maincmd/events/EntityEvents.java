package plugin.maincmd.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

import plugin.maincmd.MainCmd;

public class EntityEvents extends EntityListener{
	FileConfiguration config = MainCmd.plugin.getConfig();
	
	//Check for god
	public void God(EntityDamageEvent e){
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player)e.getEntity();
			if (config.getBoolean(p.getName() + ".god")) {
				e.setCancelled(true);
			}
		}
	}
}