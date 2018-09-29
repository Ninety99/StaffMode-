package me.NinetyNine.staff.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.vanish.StaffVanish;

public class Vanisher implements Listener {

	public static void vanish(Player player) {
		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (Bukkit.getPluginManager().getPlugin("Atials") == null) {
			if (isInVanish(player))
				return;

			StaffVanish.getVanishedPlayers().add(player);
			for (Player all : StaffUtils.getOnlinePlayers()) {
//				if (all.hasPermission("staffmode.vanishbypass")) {
//					all.showPlayer(player);
//					return;
//				} else {
					all.hidePlayer(player);
					return;
//				}
			}
		} else {
			player.performCommand("vanish");
			return;
		}
	}

	public static void unvanish(Player player) {
		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (Bukkit.getPluginManager().getPlugin("Atials") == null) {
			if (!(isInVanish(player)))
				return;

			for (Player all : StaffUtils.getOnlinePlayers()) {
				all.showPlayer(player);
				StaffVanish.getVanishedPlayers().remove(player);
			}
		} else {
			player.performCommand("vanish");
			return;
		}
	}

	public static boolean isInVanish(Player player) {
		return StaffVanish.getVanishedPlayers().contains(player);
	}
}