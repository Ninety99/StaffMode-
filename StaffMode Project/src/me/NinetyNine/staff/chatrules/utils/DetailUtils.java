package me.NinetyNine.staff.chatrules.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.utils.StaffConfig;

public class DetailUtils implements Listener {

	public static void sendDetails(Player player, RuleType type) {
		if (type == RuleType.FLOOD)
			player.sendMessage(getDetails("flood"));
		else if (type == RuleType.SPAM)
			player.sendMessage(getDetails("spam"));
		else if (type == RuleType.CAPS)
			player.sendMessage(getDetails("caps"));
		else if (type == RuleType.HACKUSATING)
			player.sendMessage(getDetails("hackusating"));
		else if (type == RuleType.ARGUING)
			player.sendMessage(getDetails("arguing"));
		else if (type == RuleType.CHAT_TROLLING)
			player.sendMessage(getDetails("chattrolling"));
		else if (type == RuleType.ASKING_STAFF_FOR_ITEMS)
			player.sendMessage(getDetails("askingstaffforthings"));
		else if (type == RuleType.ADVERTISEMENT)
			player.sendMessage(getDetails("advertisement"));
		else if (type == RuleType.YOUTUBE_ADVERTISEMENT)
			player.sendMessage(ChatColor.RED + "There are no details about YT Advertisment yet!");
		else if (type == RuleType.SWEARING)
			player.sendMessage(getDetails("swearing"));
		else if (type == RuleType.SWEARING_2 || type == RuleType.SWEARING_3)
			player.sendMessage(ChatColor.RED + "Same as for swearing severity 1!");
		else if (type == RuleType.HARASSMENT)
			player.sendMessage(getDetails("harassment"));
		else if (type == RuleType.SERVERDIS)
			player.sendMessage(getDetails("serverdis"));
		else if (type == RuleType.STAFFDIS)
			player.sendMessage(getDetails("staffdis"));
		else if (type == RuleType.BYPASSING_FILTER)
			player.sendMessage(getDetails("bypassingfilter"));
		else if (type == RuleType.DDOS)
			player.sendMessage(getDetails("ddos"));
		else if (type == RuleType.THREATHACK)
			player.sendMessage(getDetails("threathack"));
		else if (type == RuleType.INAPPB)
			player.sendMessage(getDetails("inappb"));
	}

	private static String getDetails(String type) {
		return ChatColor.translateAlternateColorCodes('&',
				"&9Details of: " + ("" + type.charAt(0)).toUpperCase() + type.substring(1) + "\n&r"
						+ StaffConfig.getString("chatrules." + type.replace("_", "").toLowerCase()));

	}

	public static void setRuleMessage(String path, String message) {
		StaffConfig.set("chatrules." + path, message);
		StaffConfig.save();
	}
}