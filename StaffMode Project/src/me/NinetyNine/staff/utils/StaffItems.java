package me.NinetyNine.staff.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

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
		return createItem(player.getInventory(), 0, new ItemStack(Material.BOOK), ChatColor.GREEN + "Inspect", null,
				Enchantment.DURABILITY, 1);
	}

	private static ItemStack addRandomTP(Player player) {
		return createItem(player.getInventory(), 1, new ItemStack(Material.BLAZE_ROD), ChatColor.AQUA + "Random TP",
				Arrays.asList("Teleportation I"), Enchantment.DURABILITY, 1);
	}

	private static ItemStack addBeacon(Player player) {
		return createItem(player.getInventory(), 3, new ItemStack(Material.BEACON),
				ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)", null, Enchantment.DURABILITY, 1);
	}

	private static ItemStack addVanishItem(Player player) {
		return createItem(player.getInventory(), 5, new ItemStack(Material.INK_SACK, 1, (byte) 10),
				ChatColor.RED + "Vanish", null, Enchantment.DURABILITY, 1);
	}

	private static ItemStack addFlyItem(Player player) {
		return createItem(player.getInventory(), 6, new ItemStack(Material.FEATHER), ChatColor.DARK_BLUE + "Fly", null,
				Enchantment.DURABILITY, 1);
	}

	private static ItemStack addGMChanger(Player player) {
		return createItem(player.getInventory(), 8, new ItemStack(Material.MELON),
				ChatColor.DARK_BLUE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)", null,
				Enchantment.DURABILITY, 1);
	}

	public static ItemStack createItem(Inventory inventory, int slot, ItemStack item, String displayName,
			List<String> lore, Enchantment enchantment, int level) {

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		meta.addEnchant(enchantment, level, true);
		item.setItemMeta(meta);

		inventory.setItem(slot, item);

		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createWool(Inventory inventory, int slot, ItemStack item, String displayName, int data) {

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();
		item.setData(new MaterialData(data));
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		
		inventory.setItem(slot, item);

		return item;
	}
}
