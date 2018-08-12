package me.NinetyNine.staff.chatrules.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.commands.StaffCommands;
import me.NinetyNine.staff.utils.StaffConfig;

public class DetailUtils implements Listener {

	public static void sendDetails(Player player, ExampleType type) {
		if (type == ExampleType.FLOOD)
			player.sendMessage(getDetails("flood"));
		else if (type == ExampleType.SPAM)
			player.sendMessage(getDetails("spam"));
		else if (type == ExampleType.CAPS)
			player.sendMessage(getDetails("caps"));
		else if (type == ExampleType.HACKUSATING)
			player.sendMessage(getDetails("hackusating"));
		else if (type == ExampleType.ARGUING)
			player.sendMessage(getDetails("arguing"));
		else if (type == ExampleType.CHAT_TROLLING)
			player.sendMessage(getDetails("chattrolling"));
		else if (type == ExampleType.ASKING_STAFF_FOR_ITEMS)
			player.sendMessage(getDetails("askingstaffforthings"));
		else if (type == ExampleType.ADVERTISEMENT)
			player.sendMessage(getDetails("advertisement"));
		else if (type == ExampleType.YOUTUBE_ADVERTISEMENT)
			player.sendMessage(ChatColor.RED + "There are no details about YT Advertisment yet!");
		else if (type == ExampleType.SWEARING)
			player.sendMessage(getDetails("swearing"));
		else if (type == ExampleType.HARASSMENT)
			player.sendMessage(getDetails("harassment"));
		else if (type == ExampleType.SERVERDIS)
			player.sendMessage(getDetails("serverdis"));
		else if (type == ExampleType.STAFFDIS)
			player.sendMessage(getDetails("staffdis"));
		else if (type == ExampleType.BYPASSING_FILTER)
			player.sendMessage(getDetails("bypassingfilter"));
		else if (type == ExampleType.DDOS)
			player.sendMessage(getDetails("ddos"));
		else if (type == ExampleType.THREATHACK)
			player.sendMessage(getDetails("threathack"));
		else if (type == ExampleType.INAPPB)
			player.sendMessage(getDetails("inappb"));
	}

	private static String getDetails(String type) {
		return ChatColor.translateAlternateColorCodes('&', "&9Details of: " + type + "\n"
				+ StaffConfig.getString("chatrules." + type.replace("_", "").toLowerCase()));

	}

	public static void setRuleMessage(String message) {
		String path = StaffCommands.getPath();

		StaffConfig.set("chatrules." + path, message);
		StaffConfig.save();
	}

}
