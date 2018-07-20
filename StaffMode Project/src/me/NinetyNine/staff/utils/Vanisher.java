package me.NinetyNine.staff.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.NinetyNine.staff.listeners.StaffVanish;

public class Vanisher {

	public static void vanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (!isInVanish(player)) {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					all.hidePlayer(player);
				}
			} else
				return;
		} else
			return;
	}

	public static void unvanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (isInVanish(player)) {
				for (Player all : Bukkit.getServer().getOnlinePlayers())
					all.showPlayer(player);
			} else
				return;
		} else
			return;
	}

	public static boolean isInVanish(Player player) {
		if (StaffVanish.getVanishedPlayers().contains(player))
			return true;
		else
			return false;
	}
}