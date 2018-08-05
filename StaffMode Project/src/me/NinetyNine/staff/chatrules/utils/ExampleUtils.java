package me.NinetyNine.staff.chatrules.utils;

import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.ChatColor.YELLOW;

import org.bukkit.entity.Player;

import lombok.Getter;

public class ExampleUtils {
	private static String a = "\n";
	private static String playername = "Player";

	private static String prefix = DARK_GRAY + "[" + YELLOW + "Member" + DARK_GRAY + "] " + GRAY + playername;

	private static String example = prefix + ":" + WHITE + " ";

	@Getter
	private static String exampleflood = example + "heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";

	@Getter
	private static String examplerandomcs = example + "jtbalsbtaldsjaaastnjntsadtast231hi2";

	@Getter
	private static String examplespam = example + "Hey!" + a + example + "Hey!" + a + example + "Hey!" + a + example
			+ "HEY!";

	@Getter
	private static String examplecountspam = example + "5" + a + example + "4" + a + example + "3" + a + example + "2"
			+ a + example + "1" + a + example + "GO!";

	@Getter
	private static String examplecaps = example + "I AM SO BORED RIGHT NOW";

	@Getter
	private static String exampleaccuse = example + "Omfg " + GOLD + "hacker" + WHITE + " hacks!!" + a + example
			+ "Ban this " + GOLD + "hacker" + WHITE + " he hacks";

	@Getter
	private static String exampleargue = example + "Why was " + DARK_GREEN + "player123" + WHITE
			+ " banned? He didn't do anything wrong!!" + a + example + "Unban " + DARK_GREEN + "player123" + WHITE
			+ " he didn't do anything!" + a + example + "Please unban him he will never hack again!!";

	@Getter
	private static String examplechatt = example + "Press Alt+F4 for gamemode creative!";

	@Getter
	private static String examplechattstaff = example + "I'm a " + RED + "Staff" + WHITE + " and I'm going to ban you!";

	@Getter
	private static String exampleaskstaff = example + "Hey! " + RED + "Admin" + WHITE
			+ " can you give me diamonds please!" + a + example + RED + "Admin" + WHITE
			+ " I will pay you if you give me the 'item'!";

	@Getter
	private static String exampleadv = DARK_GRAY + "[" + GRAY + playername + GOLD + " > " + RED + "me" + DARK_GRAY
			+ "] " + WHITE + " Come play my." + playername + ".com it has awesome games!";

	@Getter
	private static String exampleytadv = example + "Come watch my video at youtube.com/" + playername
			+ " and subscribe!";

	@Getter
	private static String exampleswear = example + "Fuck you! Go fuck yourself!!";

	@Getter
	private static String exampleswear2 = example + "Retard idiot! You're a fucking piece of shit!";

	@Getter
	private static String exampleharassment = example + "You're fucking gay. You suck!";

	@Getter
	private static String exampleswear3 = example + "I hope you fucking die of cancer!" + a + example
			+ "Do you have autism or something jeez." + a + example + "Fuck you nigger!" + a + example
			+ "Burn all the jews!!";

	@Getter
	private static String examplestaff = example + "Guys, the staff member " + RED + "(Staff)" + WHITE
			+ " sucks and is so bad!";

	@Getter
	private static String exampleserver = example + "Guys why do you even play this server, it sucks so hard!" + a
			+ example + "This server is so badddd. Shit server.";

	@Getter
	private static String examplebypass = example + "Come suck on my nuts!" + a + DARK_GRAY + "[" + GRAY + playername
			+ GOLD + " > " + RED + "me" + DARK_GRAY + "] " + WHITE + "You are such a prostitute.";

	@Getter
	private static String exampleddos = example + "This server is going down, bye bye!" + a + example
			+ "I'm gonna ddos this server!" + a + example
			+ "Bye to your internet! You're going to need to buy a new router soon.";

	@Getter
	private static String examplehack = example + "I'm going to hack you and your account!";

	@Getter
	private static String exampleinapp = example + "B=====D" + a + example + "( . Y . )" + a + example
			+ "Any dirty boys wanna skype and be naughty?" + a + example + "I want to rub your humps!!";

	public static void sendExample(Player player, ExampleType type) {
		String prefixe = GRAY + "[" + GREEN + "ChatRules" + GRAY + "]" + DARK_GRAY + " Example of " + type.name();
		if (type == ExampleType.FLOOD)
			player.sendMessage(prefixe + ":" + a + getExampleflood());
		else if (type == ExampleType.SPAM)
			player.sendMessage(prefixe + ":" + a + getExamplespam());
		else if (type == ExampleType.CAPS)
			player.sendMessage(prefixe + ":" + a + getExamplecaps());
		else if (type == ExampleType.HACKUSATING)
			player.sendMessage(prefixe + ":" + a + getExampleaccuse());
		else if (type == ExampleType.ARGUING)
			player.sendMessage(prefixe + ":" + a + getExampleargue());
		else if (type == ExampleType.CHAT_TROLLING)
			player.sendMessage(prefixe + ":" + a + getExamplechatt());
		else if (type == ExampleType.ASKING_STAFF_FOR_ITEMS)
			player.sendMessage(prefixe + ":" + a + getExampleaskstaff());
		else if (type == ExampleType.ADVERTISEMENT)
			player.sendMessage(prefixe + ":" + a + getExampleadv());
		else if (type == ExampleType.YOUTUBE_ADVERTISEMENT)
			player.sendMessage(prefixe + ":" + a + getExampleytadv());
		else if (type == ExampleType.SWEARING)
			player.sendMessage(prefixe + ":" + a + getExampleswear());
		else if (type == ExampleType.HARASSMENT)
			player.sendMessage(prefixe + ":" + a + getExampleharassment());
		else if (type == ExampleType.SERVERDIS)
			player.sendMessage(prefixe + ":" + a + getExampleserver());
		else if (type == ExampleType.STAFFDIS)
			player.sendMessage(prefixe + ":" + a + getExamplestaff());
		else if (type == ExampleType.BYPASSING_FILTER)
			player.sendMessage(prefixe + ":" + a + getExamplebypass());
		else if (type == ExampleType.DDOS)
			player.sendMessage(prefixe + ":" + a + getExampleddos());
		else if (type == ExampleType.THREATHACK)
			player.sendMessage(prefixe + ":" + a + getExamplehack());
		else if (type == ExampleType.INAPPB)
			player.sendMessage(prefixe + ":" + a + getExampleinapp());
	}
}