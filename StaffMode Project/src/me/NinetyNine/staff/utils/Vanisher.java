package me.NinetyNine.staff.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.NinetyNine.staff.listeners.StaffVanish;

public class Vanisher {

	public static void vanish(Player player) {
		if (!StaffVanish.getVanishedPlayers().contains(player)) {
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				all.hidePlayer(player);
			}
		} else
			return;
	}

	public static void unvanish(Player player) {
		if (StaffVanish.getVanishedPlayers().contains(player)) {
			for (Player all : Bukkit.getServer().getOnlinePlayers())
				all.showPlayer(player);
		} else
			return;
	}
}