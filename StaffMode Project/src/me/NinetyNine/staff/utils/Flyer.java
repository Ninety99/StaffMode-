package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;

public class Flyer {

	public static void setFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;
		
		player.setAllowFlight(true);
	}
	
	public static void removeFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;
		
		player.setAllowFlight(false);
	}
}