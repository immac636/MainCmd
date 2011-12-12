package plugin.maincmd;

public class CommandList {
	// Pain...
	// I will organize these when I'm done adding the basic commands.
	// Until then, they will be in the order that I implemented them. (minus /main)
	public static String syntax = "/<command> (required) [Optional]";
	public static String mainsyntax = "/main [help(#)]";
	public static String main = "Gives a description of MainCmd. [help] [#] will show the corresponding help page.";
	public static String tpsyntax = "/tp (player) (player)";
	public static String tp = "Teleport a player to another player";
	public static String tpheresyntax = "/tphere (player)";
	public static String tphere = "Teleport a player to you";
	public static String tptosyntax = "/tpto (player)";
	public static String tpto = "Teleport to a player";
	public static String jumpsyntax = "/jump";
	public static String jump = "Jump to where you're looking";
	public static String sendsyntax = "/send (Player)";
	public static String send = "Send a player to where you're looking";
	public static String spawnsyntax = "/spawn [player]";
	public static String spawn = "Send yourself or another player to the spawn";
	public static String setspawnsyntax = "/setspawn";
	public static String setspawn = "Set the spawn of your current world to your current location";
	public static String boomsyntax = "/boom [player]";
	public static String boom = "Boom!";
	public static String pingsyntax = "/ping";
	public static String ping = "Returns: Pong!";
	public static String givesyntax = "/give (Player) (block-or-item id) [amount]";
	public static String give = "Give a player a stack (or other amount) of an item or  block";
	public static String isyntax = "/i (block-or-item id) [amount]";
	public static String i = "Give yourself a stack (or other amount) of an item or block";
}
