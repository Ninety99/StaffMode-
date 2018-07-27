package me.NinetyNine.staff.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.NinetyNine.staff.StaffFly;
import me.NinetyNine.staff.actionbar.StaffActionBar;

public class StaffUtils {

	@Getter
	private static HashMap<Player, ItemStack[]> staff = new HashMap<Player, ItemStack[]>();

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', "&7[&c!&7] " + message);
	}

	public static void toggleStaff(Player player) {
		if (staff != null) {
			if (!isInStaffMode(player)) {
				getStaff().put(player, player.getInventory().getContents());
				player.getInventory().clear();
				Vanisher.vanish(player);
				Flyer.setFly(player);
				StaffFly.getFly().add(player);
				StaffItems.addStaffItems(player);
				player.setFoodLevel(20);
				player.setHealth(20);
				StaffActionBar.sendActionBar(player, ChatColor.GREEN + "You are currently vanished", 5);
				player.sendMessage(format("&9Staff Mode has been &aenabled&9. &7(&6Vanish &7and &6Fly &aenabled&7)"));
				return;
			} else
				removeStaff(player);
		} else
			return;
	}

	private static void removeStaff(Player player) {
		if (staff != null) {
			if (isInStaffMode(player)) {
				player.getInventory().clear();
				player.getInventory().setContents(getStaff().get(player));
				Flyer.removeFly(player);
				StaffFly.getFly().remove(player);
				Vanisher.unvanish(player);
				getStaff().remove(player);
				StaffActionBar.sendActionBar(player, ChatColor.RED + "You are now unvanished", 5);
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(format("&9Staff Mode has been &cdisabled&9. &7(&6Vanish &7and &6Fly &cdisabled&7)"));
				return;
			} else
				return;
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