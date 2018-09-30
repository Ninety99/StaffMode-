package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.guildcraft.atials.TPlayer;

import me.NinetyNine.staff.fly.StaffFly;

public class Flyer implements Listener {

	public static void setFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			StaffFly.getFly().add(player);
			player.setAllowFlight(true);
			player.setFlying(true);
			return;
		} else {
			TPlayer p = TPlayer.getPlayer(player);

			p.setFlying(true);
			player.setAllowFlight(true);
			player.setFlying(true);
			StaffFly.getFly().add(player);
			return;
		}
	}

	public static void removeFly(Player player) {
		if (!StaffUtils.isInStaffMode(player))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			StaffFly.getFly().remove(player);
			player.setAllowFlight(false);
			player.setFlying(false);
			return;
		} else {
			TPlayer p = TPlayer.getPlayer(player);

			p.setFlying(false);
			player.setAllowFlight(false);
			player.setFlying(false);
			StaffFly.getFly().remove(player);
			return;
		}
	}

	public static boolean isInFly(Player player) {
		return StaffFly.getFly().contains(player);
	}
}