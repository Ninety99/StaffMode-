package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.fly.StaffFly;

public class Flyer implements Listener {

	public static void setFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			StaffFly.getFly().add(player);
			player.setAllowFlight(true);
			return;
		} else {
			player.performCommand("fly");
			return;
		}
	}

	public static void removeFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			StaffFly.getFly().remove(player);
			player.setAllowFlight(false);
			return;
		} else {
			player.performCommand("fly");
			return;
		}
	}

	public static boolean isInFly(Player player) {
		return StaffFly.getFly().contains(player);
	}
}