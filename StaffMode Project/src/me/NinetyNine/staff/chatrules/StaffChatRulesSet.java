package me.NinetyNine.staff.chatrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.NinetyNine.staff.chatrules.utils.DetailUtils;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffMessageEditChatRules;

public class StaffChatRulesSet implements StaffMessageEditChatRules {

	@Override
	public void setChatRule(Player player, String message) {
		if (isValid(StaffChatRulesInventory.getPath())) {
			String path = StaffChatRulesInventory.getPath();

			if (StaffChatRulesInventory.getInEditMode().contains(player))
				StaffChatRulesInventory.getInEditMode().remove(player);

			player.sendMessage(StaffUtils.format("&cSet Rule Message:"));
			player.sendMessage(StaffUtils.format("&0- &6Chat Rule Type: &7" + path.toUpperCase()));
			player.sendMessage(StaffUtils.format("&0- &5From: &r"
					+ ChatColor.translateAlternateColorCodes('&', StaffConfig.getString("chatrules." + path))));
			DetailUtils.setRuleMessage(path, message);
			player.sendMessage(StaffUtils.format("&0- &5To: &r"
					+ ChatColor.translateAlternateColorCodes('&', StaffConfig.getString("chatrules." + path))));
			return;
		} else {
			player.sendMessage(StaffUtils.format("&cInvalid path."));
			return;
		}
	}

	public static boolean isValid(String path) {
		return getChatRules().contains(path);
	}

	public static List<String> getChatRules() {
		List<String> paths = new ArrayList<String>(Arrays.asList("inappb", "threathack", "ddos", "bypassingfilter",
				"staffdis", "serverdis", "harassment", "swearing", "advertisement", "askingstaffforthings",
				"chattrolling", "arguing", "hackusating", "caps", "spam", "flood"));

		return paths;
	}
}