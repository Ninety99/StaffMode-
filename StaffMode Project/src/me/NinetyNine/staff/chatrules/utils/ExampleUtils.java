package me.NinetyNine.staff.chatrules.utils;

import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.NinetyNine.staff.commands.StaffCommands;
import me.NinetyNine.staff.utils.StaffConfig;

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
		String prefixe = GRAY + "[" + GREEN + "ChatRules" + GRAY + "]" + DARK_GRAY + " Example of " + type.toString();
		if (type == ExampleType.FLOOD) {
			player.sendMessage(prefixe + ":" + a + getExampleflood());
		} else if (type == ExampleType.SPAM) {
			player.sendMessage(prefixe + ":" + a + getExamplespam());
		} else if (type == ExampleType.CAPS) {
			player.sendMessage(prefixe + ":" + a + getExamplecaps());
		} else if (type == ExampleType.ACCUSE) {
			player.sendMessage(prefixe + ":" + a + getExampleaccuse());
		} else if (type == ExampleType.ARGUE) {
			player.sendMessage(prefixe + ":" + a + getExampleaccuse());
		} else if (type == ExampleType.CHATT) {
			player.sendMessage(prefixe + ":" + a + getExamplechatt());
		} else if (type == ExampleType.ASKSTAFF) {
			player.sendMessage(prefixe + ":" + a + getExampleaskstaff());
		} else if (type == ExampleType.ADV) {
			player.sendMessage(prefixe + ":" + a + getExampleadv());
		} else if (type == ExampleType.YTADV) {
			player.sendMessage(prefixe + ":" + a + getExampleytadv());
		} else if (type == ExampleType.SWEAR) {
			player.sendMessage(prefixe + ":" + a + getExampleswear());
		} else if (type == ExampleType.HARASSMENT) {
			player.sendMessage(prefixe + ":" + a + getExampleharassment());
		} else if (type == ExampleType.SERVER) {
			player.sendMessage(prefixe + ":" + a + getExampleserver());
		} else if (type == ExampleType.STAFF) {
			player.sendMessage(prefixe + ":" + a + getExamplestaff());
		} else if (type == ExampleType.BYPASS) {
			player.sendMessage(prefixe + ":" + a + getExamplebypass());
		} else if (type == ExampleType.DDOS) {
			player.sendMessage(prefixe + ":" + a + getExampleddos());
		} else if (type == ExampleType.HACK) {
			player.sendMessage(prefixe + ":" + a + getExamplehack());
		} else if (type == ExampleType.INAPP) {
			player.sendMessage(prefixe + ":" + a + getExampleinapp());
		}
	}

	public static void sendDetails(Player player, ExampleType type) {
		if (type == ExampleType.FLOOD) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Saying &n1&r&7 or more letters constantly and it can also be saying different characters that intended to flood the chat. This can result in a &l&42-4 hour mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.SPAM) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Spamming &n3 sentences or phrases&r&7 / &nCountdown&4&7 spamming (3,2,1...) in the chat can result in a &l&42-4 hour mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.CAPS) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Capitalizing &n&l4 or more words&r&7 / Capitalizing more than &n15 characters&r&7. This can result in a &l&41-2 hour mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.ACCUSE) {
			player.sendMessage(translateAlternateColorCodes('&',
					"'&7Accusing someone of &nhacking&r&7. Thus, will lower the chances of Staff to ban them. Players who accuse must use /helpop! This can result in a &l&46 hour mute!"));
		} else if (type == ExampleType.ARGUE) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Someone &lconstantly contradicting what someone says &r&7/Someone &l&nprotesting a punishment that has been given to them&r&7 (Make an unban/unmute request at our forums!). This will result in a &l&412 hour mute&r&7!"));
		} else if (type == ExampleType.CHATT) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&n&7Intentionally&r &7saying things that are meant to &nconfuse &r&7or &nmislead players. &r&7| &nImpersonating a Staff member&r&7. This can result in a &c(Not showed in the guidelines, confirming it asap!)"));
		} else if (type == ExampleType.ASKSTAFF) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Asking a Staff member who is able to &nspawn in items and/or money for a player&r&7. This can result in a l42-4 hour mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.ADV) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Asking &nplayers to join a server &r&7via mentioning the servers name in &npublic &r&7conversation or by &nprivate &r&7messaging an &lIP&r&7. This can result in a &n&l&4PERMANENT &7ban!"));
		} else if (type == ExampleType.YTADV) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Advertising of &nYouTube videos, YouTube channels, or Twitch channels&r&7. This can result in a &l&42 day mute!"));
		} else if (type == ExampleType.SWEAR) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Cussing/Cursing/Swearing/being rude to a player with &nintent to hurt their &n&lfeelings&r&7. This can result in a &l&424 hours/36 hours/3 day mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.HARASSMENT) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Repeatedly being rude to a player with intent to &nhurt their &n&lfeelings&r&7. This can result in a &l&424 hour/36 hour/3 day mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.SERVER) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Giving the &lserver a &nbad name&r&7. &r&7If you see someone doing/did this, &lplease report at our forums or on discord &nimmediately!&r&7 This can result in a &l&4MUTE &7or a &l&4BAN."));
		} else if (type == ExampleType.STAFF) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Giving a &lStaff member/the Staff team a &nbad name&r&7. &r&7If you see someone doing/did this, &lplease report at our forums or on discord &nimmediately!&r&7 This can result in a &l&4MUTE &7or a &l&4BAN."));
		} else if (type == ExampleType.BYPASS) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Finding a way to use words that are &lfiltered/Using other &nvulgar words&r&7 that are not filtered but &lSHOULD &r&7be. This includes swearing in /msg! This can result in a &l&4mute! &c(Depends on how severe)"));
		} else if (type == ExampleType.DDOS) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Threatening a player/the server to be shut down/to put down someones internet/the server. This &n&lWILL&7 result in a &l&4PERMANENT&lban."));
		} else if (type == ExampleType.HACK) {
			player.sendMessage(translateAlternateColorCodes('&',
					"&7Threatening a player/the server to be &lhacked. This &n&lWILL&4&7 result in a &l&4PERMANENT &lban."));
		} else if (type == ExampleType.INAPP) {
			player.sendMessage(translateAlternateColorCodes('&',
					"'&7Saying something in chat that has &lsexual or generally inappropriate content&r&7. This can result in a &l&48-12 hour mute! &c(Depends on how severe)"));
		}
	}

	public static void setRuleMessage(String message) {
		String path = StaffCommands.getPath();
		
		StaffConfig.set("chatrules." + path, message);
		StaffConfig.save();
	}
}