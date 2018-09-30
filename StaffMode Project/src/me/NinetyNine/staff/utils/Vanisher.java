package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.guildcraft.atials.TPlayer;
import org.guildcraft.atials.VanishManager;

import me.NinetyNine.staff.vanish.StaffVanish;

public class Vanisher implements Listener {

	public static void vanish(Player player) {
		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			if (isInVanish(player))
				return;

			for (Player all : StaffUtils.getOnlinePlayers())
				all.hidePlayer(player);

			StaffVanish.getVanishedPlayers().add(player);
			
			for (Player op : StaffVanish.getVanishedPlayers()) {
				if (op.isOp())
					op.showPlayer(player);
			}
			
			return;
		} else {
			TPlayer.getPlayer(player).setVanish(true);
			VanishManager.Vanish(player, false);
			return;
		}
	}

	public static void unvanish(Player player) {
		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (!(StaffUtils.isAtialsEnabled())) {
			if (!(isInVanish(player)))
				return;

			for (Player all : StaffUtils.getOnlinePlayers())
				all.showPlayer(player);

			StaffVanish.getVanishedPlayers().remove(player);
			return;
		} else {
			TPlayer.getPlayer(player).setVanish(false);
			VanishManager.unVanish(player, false);
			return;
		}
	}

	public static boolean isInVanish(Player player) {
		return VanishManager.isGVanished(player);
	}
}