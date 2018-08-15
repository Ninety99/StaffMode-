package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;

import me.NinetyNine.staff.vanish.StaffVanish;

public class Vanisher {

	public static void vanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (!isInVanish(player)) {
				for (Player all : StaffUtils.getOnlinePlayers()) {
					all.hidePlayer(player);

					if (all.hasPermission("staffmode.vanishbypass"))
						all.showPlayer(player);

					StaffVanish.getVanishedPlayers().add(player);
					if (!(StaffUtils.getOnlinePlayers().contains(all)))
						StaffUtils.getOnlinePlayers().add(all);
				}
			} else
				return;
		} else
			return;
	}

	public static void unvanish(Player player) {
		if (StaffUtils.isInStaffMode(player)) {
			if (isInVanish(player)) {
				for (Player all : StaffUtils.getOnlinePlayers()) {
					all.showPlayer(player);
					StaffVanish.getVanishedPlayers().remove(player);
					if (StaffUtils.getOnlinePlayers().contains(all))
						StaffUtils.getOnlinePlayers().remove(all);
				}
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