package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;

import me.NinetyNine.staff.listeners.StaffFly;

public class Flyer {

	public static void setFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		player.setAllowFlight(true);
		return;
	}

	public static void removeFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		player.setAllowFlight(false);
		return;
	}

	public static boolean isInFly(Player player) {
		if (StaffFly.getFly().contains(player))
			return true;
		else
			return false;
	}
}