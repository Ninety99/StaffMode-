package me.NinetyNine.staff.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.NinetyNine.staff.Staff;
import me.NinetyNine.staff.listeners.StaffFly;
import me.NinetyNine.staff.listeners.StaffVanish;

public class StaffUtils {

	@Getter
	private static HashMap<Player, ItemStack[]> staff = new HashMap<Player, ItemStack[]>();

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', "&7[&c!&7] " + message);
	}

	public static void toggleStaff(Player player) {
		if (staff != null) {
			if (!isInStaffMode(player)) {
				getStaff().put(player, player.getInventory().getContents());
				player.getInventory().clear();
				Vanisher.vanish(player);
				StaffVanish.getVanishedPlayers().add(player);
				Flyer.setFly(player);
				StaffFly.getFly().add(player);
				StaffItems.addStaffItems(player);
				player.setFoodLevel(20);
				player.setHealth(20);
				player.sendMessage(format("&9Staff Mode has been &aenabled&9. &7(&6Vanish &7and &6Fly &aenabled&7)"));
				return;
			} else
				removeStaff(player);
		} else
			return;
	}

	private static void removeStaff(Player player) {
		if (staff != null) {
			if (isInStaffMode(player)) {
				Location playerloc = player.getLocation();
				if (playerloc.clone().subtract(0, 5, 0).getBlock().getType() != Material.AIR) {
					remove(player);
				} else {
					PotionEffect p = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2, true);
					player.addPotionEffect(p);
					remove(player);
					addCheck(player);
					player.sendMessage(format(
							"&aYou have recieved damage resistance to not get as much as fall damage since you were above the air by 5+ blocks!"));
					return;
				}
			} else
				return;
		} else
			return;
	}

	private static void remove(Player player) {
		player.getInventory().clear();
		player.getInventory().setContents(getStaff().get(player));
		Flyer.removeFly(player);
		StaffFly.getFly().remove(player);
		Vanisher.unvanish(player);
		StaffVanish.getVanishedPlayers().remove(player);
		getStaff().remove(player);
		player.setGameMode(GameMode.SURVIVAL);
		player.sendMessage(format("&9Staff Mode has been &cdisabled&9. &7(&6Vanish &7and &6Fly &cdisabled&7)"));
	}

	private static void addCheck(Player player) {
		new BukkitRunnable() {
			public void run() {
				if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
					Location playerLoc = player.getLocation().clone();
					Location newLocation = new Location(player.getWorld(), playerLoc.getBlockX(),
							playerLoc.getBlockY() - 1, playerLoc.getBlockZ());
					if (newLocation.getBlock().getType() != Material.AIR)
						player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					cancel();
				} else
					cancel();
				return;
			}
		}.runTaskTimer(Staff.getInstance(), 20L, 20L);
	}

	public static boolean isInStaffMode(Player player) {
		if (getStaff().containsKey(player))
			return true;
		else
			return false;
	}
}