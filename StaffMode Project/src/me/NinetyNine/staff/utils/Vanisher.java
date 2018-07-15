package me.NinetyNine.staff.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanisher {

	public static void vanish(Player player) {
		for (Player all : Bukkit.getServer().getOnlinePlayers())
			all.hidePlayer(player);
	}
	
	public static void unvanish(Player player) {
		for (Player all : Bukkit.getServer().getOnlinePlayers())
			all.showPlayer(player);
	}
}