package plugin.maincmd.events;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import plugin.maincmd.MainCmd;

public class EntityEvents extends EntityListener {
	public void onEntityDamage(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player)e.getEntity();
			if (MainCmd.plugin.getConfig().getBoolean("Players." + p.getName() + ".god")) {
				e.setCancelled(true);
			}
		}
	}

	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		Player p = (Player)e.getEntity();
		if (MainCmd.plugin.getConfig().getBoolean("Players." + p.getName() + ".god")) {
			e.setCancelled(true);
		}
	}
}