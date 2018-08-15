package me.NinetyNine.staff.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.vanish.StaffVanish;

public class Vanisher implements Listener {

	public static void vanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (Bukkit.getPluginManager().getPlugin("Atials") == null) {
				if (!isInVanish(player)) {
					for (Player all : StaffUtils.getOnlinePlayers()) {
						if (!all.hasPermission("staffmode.vanishbypass")) {
							all.hidePlayer(player);

							StaffVanish.getVanishedPlayers().add(player);
							if (!(StaffUtils.getOnlinePlayers().contains(all)))
								StaffUtils.getOnlinePlayers().add(all);
						} else
							return;
					}
				} else
					return;
			} else {
				player.performCommand("vanish");
				return;
			}
		} else
			return;
	}

	public static void unvanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (Bukkit.getPluginManager().getPlugin("Atials") == null) {
				if (isInVanish(player)) {
					for (Player all : StaffUtils.getOnlinePlayers()) {
						all.showPlayer(player);
						StaffVanish.getVanishedPlayers().remove(player);
					}
				} else
					return;
			} else {
				player.performCommand("vanish");
				return;
			}
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