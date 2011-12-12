package plugin.maincmd.hashmaps;

import java.util.HashMap;

import org.bukkit.Material;

public class ItemHashmap {
	public static HashMap<String, Material> items = new HashMap<String, Material>();
	public static void loadItems() {
		//I could use someone elses hashmap for this, but that would be cheating.
		//I'd like to make this plugin without apis, etc
		//Only api i may use is PermissionsBukkit, but thats in the future
		//Remind me to remove my ramblings some time later
		// PAIN TIME!!! Oh and remind me to put in data values too.
		items.put("stone", Material.STONE); //1
		items.put("1", Material.STONE);
		items.put("grass", Material.GRASS); //2
		items.put("2", Material.GRASS);
		items.put("dirt", Material.DIRT); //3
		items.put("3", Material.DIRT);
		items.put("cobble", Material.COBBLESTONE); //4
		items.put("4", Material.COBBLESTONE);
		items.put("planks", Material.WOOD); //5
		items.put("wood", Material.WOOD); 
		items.put("5", Material.WOOD);
		items.put("sapling", Material.SAPLING);
		items.put("6", Material.SAPLING);
		items.put("bedrock", Material.BEDROCK);
		items.put("7", Material.BEDROCK);
		items.put("water", Material.WATER);
		items.put("swater", Material.STATIONARY_WATER);
		items.put("lava", Material.LAVA);
		items.put("slava", Material.STATIONARY_LAVA);
		items.put("sand", Material.SAND);
		items.put("gravel", Material.GRAVEL);
		items.put("goldore", Material.GOLD_ORE);
		items.put("ironore", Material.IRON_ORE);
		items.put("coalore", Material.COAL_ORE);
		items.put("log", Material.LOG);
		items.put("leaves", Material.LEAVES);
		items.put("sponge", Material.SPONGE);
		items.put("glass", Material.GLASS);
		// Not done
	}

}
