package plugin.maincmd;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Logger;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet;
import net.minecraft.server.Packet51MapChunk;
import net.minecraft.server.TileEntity;

import org.bukkit.configuration.Configuration;
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
	public File DataFolder = new File(this.getDataFolder() + File.separator + "data");
	//public File warpsFile = new File(DataFolder, "warps.yml");
	//public File playersFile = new File(DataFolder, "players.yml");
	CommandLoader CommandLoader = new CommandLoader();
	EventLoader EventLoader = new EventLoader();
	ConfigLoader ConfigLoader = new ConfigLoader();
	public boolean UsePermissions;
	public PermissionHandler Permissions;
	public static String newln = System.getProperty("line.separator");

	public void onEnable() {
		plugin = this;
		log.info("MainCmd Version " + plugin.getDescription().getVersion() + " started up!");
		CommandLoader.LoadCommands();
		EventLoader.LoadEvents();
		ConfigLoader.LoadConfigs();
		/*try {
			warpsFile.createNewFile();
			playersFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		LoadPerms();
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
				System.out.println("[MainCmd] Permissions system detected!");
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
				if (l_line != null) {
					s.append(l_line + ", ");
				}
				else {
					s.append("++ End of file ++");
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "There was an error performing this command (File is missing?)";
		}
		return s.toString();
	}
	
	public void fileWrite(String s, File f) {
		try{
			// Create file 
			FileWriter fstream = new FileWriter(f, true);
			BufferedWriter out = new BufferedWriter(fstream);
			if (f.length() > 0) {
				out.newLine();
				out.write(s);
				out.flush();
			}
			else {
				out.write(s);
				out.flush();
			}
			System.out.println("Writing to " + f.toString());
			//Close the output stream
			out.close();
		} catch (Exception e){ //Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String configList(Configuration file, String node) {
		try	{
			String keys = file.getConfigurationSection(node).getKeys(false).toString().replaceAll("[", "\"").replaceAll("]", "\"");
			return keys;
		} catch(Exception e) {
			e.printStackTrace();
			return "There was an error performing this command (File is missing?)";
		}
	}
	
	public boolean permsCheck(Player p, String string) {
		/*Boolean kyle = p.getName().equals("kyleman572");
		Boolean liam = p.getName().equals("liamk97");
		Boolean brett = p.getName().equals("dgrox");
		// Boolean player = p.getName().equals("Player"); // Debug
		if (kyle || liam || brett || player) {
			log.warning("A blocked player [" + p.getName() + "] was just kicked. Please ban.");
			p.kickPlayer("Don't mess around.");
			return false;
		}
		else {*/
			if (UsePermissions) {
				if (p.hasPermission("MainCmd.*")) {
					return true;
				}
				else {
					return Permissions.has(p, string);
				}
			}
			else if (p.isOp()) {
				return true;
			}
			else {
				return false;
			}
		//}
	}

	public String replaceColors(String String) {
		return String.replaceAll("(?i)&([a-f0-9])", "§$1");
	}
	public static void sendChunk(net.minecraft.server.Chunk c, Player t) {
		EntityPlayer player = (EntityPlayer) t;
		byte[] data = new byte[81920];
		System.arraycopy(c.b, 0, data, 0, 32768);
		System.arraycopy(c.g.a, 0, data, 32768, 16384);
		System.arraycopy(c.i.a, 0, data, 49152, 16384);
		System.arraycopy(c.h.a, 0, data, 65536, 16384);

		player.netServerHandler.sendPacket(new Packet51MapChunk(c.x << 4, 0, c.z << 4, 16, 128, 16, data));
		Packet p;
		for (Object o : c.tileEntities.values()) {
			p = ((TileEntity) o).k();
			if (p != null) player.netServerHandler.sendPacket(p);
		}
	}
}