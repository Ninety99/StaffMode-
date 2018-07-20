package me.NinetyNine.staff.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class StaffItems {

	public static void addStaffItems(Player player) {
		addInspect(player);
		addRandomTP(player);
		addBeacon(player);
		addVanishItem(player);
		addFlyItem(player);
		addGMChanger(player);
	}

	private static ItemStack addInspect(Player player) {
		return createItem(player.getInventory(), 0, new ItemStack(Material.BOOK), ChatColor.GREEN + "Inspect", null);
	}

	private static ItemStack addRandomTP(Player player) {
		return createItem(player.getInventory(), 1, new ItemStack(Material.BLAZE_ROD), ChatColor.AQUA + "Random TP",
				Arrays.asList("Teleportation I"));
	}

	private static ItemStack addBeacon(Player player) {
		return createItem(player.getInventory(), 3, new ItemStack(Material.BEACON),
				ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)", null);
	}

	@SuppressWarnings("deprecation")
	private static ItemStack addVanishItem(Player player) {
		return createItem(player.getInventory(), 5,
				new ItemStack(Material.INK_SACK, 1, (short) DyeColor.GRAY.getData()), ChatColor.RED + "Vanish", null);
	}

	private static ItemStack addFlyItem(Player player) {
		return createItem(player.getInventory(), 6, new ItemStack(Material.FEATHER), ChatColor.DARK_BLUE + "Fly", null);
	}

	private static ItemStack addGMChanger(Player player) {
		return createItem(player.getInventory(), 8, new ItemStack(Material.WATCH),
				ChatColor.DARK_BLUE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)", null);
	}

	public static ItemStack createItem(Inventory inventory, int slot, ItemStack item, String displayName,
			List<String> lore) {

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		item.setItemMeta(meta);

		inventory.setItem(slot, item);

		return item;
	}

	public static ItemStack createSkull(ItemStack item, String displayName, String owner, List<String> lore) {
		item = new ItemStack(item.getType());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setOwner(owner);
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}
}
