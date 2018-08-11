package me.NinetyNine.staff.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.guildcraft.gcbanz.GCBanz;
import org.guildcraft.gcbanz.data.Type;
import org.guildcraft.gcbanz.data.Violation;

import lombok.Getter;
import me.NinetyNine.staff.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.bminfo.StaffBMInfoInterface;

public class StaffItems {

	@Getter
	private static List<ItemStack> staffItems = new ArrayList<ItemStack>();

	public static void addStaffItems(Player player) {
		addInspect(player);
		addRandomTP(player);
		addBeacon(player);
		addVanishItem(player);
		addFlyItem(player);
		addGMChanger(player);
	}

	private static ItemStack addInspect(Player player) {
		return createItem(player.getInventory(), 0, Material.STICK, ChatColor.GREEN + "Inspect", null, true);
	}

	private static ItemStack addRandomTP(Player player) {
		return createItem(player.getInventory(), 1, Material.BLAZE_ROD, ChatColor.AQUA + "Random TP",
				Arrays.asList(" ", "Teleportation I"), true);
	}

	private static ItemStack addBeacon(Player player) {
		return createItem(player.getInventory(), 3, Material.BEACON,
				ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)", null, true);
	}

	private static ItemStack addVanishItem(Player player) {
		return createItemWithEnch(player.getInventory(), 5, Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Vanish",
				null, Enchantment.DURABILITY, 1);
	}

	private static ItemStack addFlyItem(Player player) {
		return createItemWithEnch(player.getInventory(), 6, Material.COOKIE, ChatColor.GREEN + "Fly", null,
				Enchantment.DURABILITY, 1);
	}

	private static ItemStack addGMChanger(Player player) {
		return createItem(player.getInventory(), 8, Material.WATCH,
				ChatColor.DARK_BLUE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)", null, true);
	}

	public static boolean isStaffItem(ItemStack item) {
		if (item.hasItemMeta()) {
			for (ItemStack i : getStaffItems()) {
				if (i == item)
					return true;
			}
		} else
			return false;
		return false;
	}

	public static ItemStack createItem(Inventory inventory, int slot, Material item, String displayName,
			List<String> lore, boolean isStaff) {

		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		item2.setItemMeta(meta);

		inventory.setItem(slot, item2);

		if (isStaff) {
			if (!getStaffItems().contains(item2))
				getStaffItems().add(item2);
		}

		return item2;
	}

	public static ItemStack createItemWithEnch(Inventory inventory, int slot, Material item, String displayName,
			List<String> lore, Enchantment enchantment, int level) {
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		meta.addEnchant(enchantment, level, true);
		item2.setItemMeta(meta);

		inventory.setItem(slot, item2);

		return item2;
	}

	public static void createItemWithBMInfo(Player target, Inventory inventory, int slot, Material item,
			String displayName) {
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		List<String> lore = new ArrayList<String>();

		StaffBMInfoInterface bminfo = new StaffBMInfoHook();

		lore.add(ChatColor.AQUA + target.getName());
		lore.add(" ");
		lore.add(ChatColor.RED + "Active: ");
		lore.add(ChatColor.RED + "Ban: " + getViolation(Type.BAN, target.getName()));
		lore.add(ChatColor.RED + "Mute: " + getViolation(Type.MUTE, target.getName()));
		lore.add(ChatColor.RED + "Bans: " + ChatColor.GOLD + bminfo.getBans(target));
		lore.add(ChatColor.RED + "Mutes: " + ChatColor.GOLD + bminfo.getMutes(target));
		lore.add(ChatColor.RED + "Kicks: " + ChatColor.GOLD + bminfo.getKicks(target));
		lore.add(ChatColor.RED + "Warns: " + ChatColor.GOLD + bminfo.getWarns(target));

		meta.setLore(lore);
		item2.setItemMeta(meta);

		inventory.setItem(slot, item2);
	}

	public static void createGlassWithColor(Inventory inventory, int slot, String displayName, short durability) {
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta meta = glass.getItemMeta();
		meta.setDisplayName(displayName);
		glass.setDurability(durability);
		glass.setItemMeta(meta);

		inventory.setItem(slot, glass);
	}

	public static void createSkullsWithBMInfo(Inventory inventory, Player all) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(all.getName());
		meta.setOwner(all.getName());

		List<String> lore = new ArrayList<String>();

		StaffBMInfoInterface bminfo = new StaffBMInfoHook();

		lore.add(ChatColor.AQUA + "BMInfo:");
		lore.add(" ");
		lore.add(ChatColor.RED + "Bans: " + ChatColor.GOLD + bminfo.getBans(all));
		lore.add(ChatColor.RED + "Mutes: " + ChatColor.GOLD + bminfo.getMutes(all));
		lore.add(ChatColor.RED + "Kicks: " + ChatColor.GOLD + bminfo.getKicks(all));
		lore.add(ChatColor.RED + "Warns: " + ChatColor.GOLD + bminfo.getWarns(all));

		meta.setLore(lore);
		skull.setItemMeta(meta);

		if (!inventory.contains(skull))
			inventory.addItem(skull);
		else
			return;

	}

	public static void createItemWithColor(Inventory inventory, Material item, int slot, String displayName, int data) {

		ItemStack it = new ItemStack(item, 1, (short) data);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayName);
		it.setItemMeta(meta);
		inventory.setItem(slot, it);
	}

	private static int getViolation(Type type, String target) {
		Violation vban = GCBanz.sql.getVl(type, target);

		if (vban == null)
			return 0;

		Violation vmute = GCBanz.sql.getVl(type, target);

		if (vmute == null)
			return 0;

		return 0;
	}
}