package plugin.maincmd.hashmaps;

import java.util.HashMap;

import org.bukkit.Material;

public class ItemHashmap { //Name is for the command, not the type
	public static HashMap<String, Material> mats = new HashMap<String, Material>();
	public static void loadMats() {
		//I could use someone elses hashmap for this, but that would be cheating.
		//I'd like to make this plugin without apis, etc
		//Only api i may use is PermissionsBukkit, but thats in the future
		//Remind me to remove my ramblings some time later
		// PAIN TIME!!! Oh and remind me to put in data values too.
		mats.put("stone", Material.STONE); //1
		mats.put("1", Material.STONE);
		mats.put("grass", Material.GRASS); //2
		mats.put("2", Material.GRASS);
		mats.put("dirt", Material.DIRT); //3
		mats.put("3", Material.DIRT);
		mats.put("cobble", Material.COBBLESTONE); //4
		mats.put("4", Material.COBBLESTONE);
		mats.put("planks", Material.WOOD); //5
		mats.put("wood", Material.WOOD); 
		mats.put("5", Material.WOOD);
		mats.put("sapling", Material.SAPLING);
		mats.put("6", Material.SAPLING);
		mats.put("bedrock", Material.BEDROCK);
		mats.put("7", Material.BEDROCK);
		mats.put("water", Material.WATER);
		mats.put("8", Material.WATER);
		mats.put("swater", Material.STATIONARY_WATER);
		mats.put("9", Material.STATIONARY_WATER);
		mats.put("lava", Material.LAVA);
		mats.put("10", Material.LAVA);
		mats.put("slava", Material.STATIONARY_LAVA);
		mats.put("11", Material.STATIONARY_LAVA);
		mats.put("sand", Material.SAND);
		mats.put("12", Material.SAND);
		mats.put("gravel", Material.GRAVEL);
		mats.put("13", Material.GRAVEL);
		mats.put("goldore", Material.GOLD_ORE);
		mats.put("14", Material.GOLD_ORE);
		mats.put("ironore", Material.IRON_ORE);
		mats.put("15", Material.IRON_ORE);
		mats.put("coalore", Material.COAL_ORE);
		mats.put("16", Material.COAL_ORE);
		mats.put("log", Material.LOG);
		mats.put("17", Material.LOG);
		mats.put("leaves", Material.LEAVES);
		mats.put("18", Material.LEAVES);
		mats.put("sponge", Material.SPONGE);
		mats.put("19", Material.SPONGE);
		mats.put("glass", Material.GLASS);
		mats.put("20", Material.GLASS);
		// Thats 20
		// Not done
		// I will put in 20 new materials per prerelease and 20 new items per prerelease, cause im lazy
		
		// *******************************
		// ITEMS :
		// *******************************
		
		mats.put("ispade", Material.IRON_SPADE);
		mats.put("256", Material.IRON_SPADE);
		mats.put("ipick", Material.IRON_PICKAXE);
		mats.put("257", Material.IRON_PICKAXE);
		mats.put("iaxe", Material.IRON_AXE);
		mats.put("258", Material.IRON_AXE);
		mats.put("lighter", Material.FLINT_AND_STEEL);
		mats.put("259", Material.FLINT_AND_STEEL);
		mats.put("apple", Material.APPLE);
		mats.put("260", Material.APPLE);
		mats.put("bow", Material.BOW);
		mats.put("261", Material.BOW);
		mats.put("arrow", Material.ARROW);
		mats.put("262", Material.ARROW);
		mats.put("coal", Material.COAL);
		mats.put("263", Material.COAL);
		mats.put("diamond", Material.DIAMOND);
		mats.put("264", Material.DIAMOND);
		mats.put("iingot", Material.IRON_INGOT);
		mats.put("265", Material.IRON_INGOT);
		mats.put("gingot", Material.GOLD_INGOT);
		mats.put("266", Material.GOLD_INGOT);
		mats.put("isword", Material.IRON_SWORD);
		mats.put("267", Material.IRON_SWORD);
		mats.put("wsword", Material.WOOD_SWORD);
		mats.put("268", Material.WOOD_SWORD);
		mats.put("wspade", Material.WOOD_SPADE);
		mats.put("269", Material.WOOD_SPADE);
		mats.put("wpick", Material.WOOD_PICKAXE);
		mats.put("270", Material.WOOD_PICKAXE);
		mats.put("waxe", Material.WOOD_AXE);
		mats.put("271", Material.WOOD_AXE);
		mats.put("ssword", Material.STONE_SWORD);
		mats.put("272", Material.STONE_SWORD);
		mats.put("sspade", Material.STONE_SPADE);
		mats.put("273", Material.STONE_SPADE);
		mats.put("spick", Material.STONE_PICKAXE);
		mats.put("274", Material.STONE_PICKAXE);
		mats.put("saxe", Material.STONE_AXE);
		mats.put("275", Material.STONE_AXE);
	}
}
