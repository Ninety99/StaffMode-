package me.NinetyNine.staff.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

public class StaffUtils {

	@Getter
	private static HashMap<Player, ItemStack[]> staff = new HashMap<Player, ItemStack[]>();

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', "&7[&c!&7] " + message);
	}

	public static void toggleStaff(Player player) {
		if (!isInStaffMode(player)) {
			getStaff().put(player, player.getInventory().getContents());
			player.getInventory().clear();
			Vanisher.vanish(player);
			StaffItems.addStaffItems(player);
			player.setFoodLevel(20);
			player.setHealth(20);
			player.sendMessage(format("&9Staff Mode has been &aenabled&9. &7(You have been vanished)"));
			return;
		} else
			removeStaff(player);
	}

	private static void removeStaff(Player player) {
		if (isInStaffMode(player)) {
			player.getInventory().clear();
			player.getInventory().setContents(getStaff().get(player));
			getStaff().remove(player);
			player.setGameMode(GameMode.SURVIVAL);
			Vanisher.unvanish(player);
			player.sendMessage(format("&9Staff Mode has been &cdisabled&9. &7(You have been unvanished)"));
		} else
			return;
	}

	public static boolean isInStaffMode(Player player) {
		if (getStaff().containsKey(player))
			return true;
		else
			return false;
	}
}