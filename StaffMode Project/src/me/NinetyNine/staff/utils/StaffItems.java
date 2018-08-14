package me.NinetyNine.staff.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.NinetyNine.staff.Staff;
import me.NinetyNine.staff.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.bminfo.StaffBMInfoInterface;

public class StaffItems {

	@Getter
	private static List<ItemStack> staffItems = new ArrayList<ItemStack>();

	public static void addStaffItems(Player player) {
		createItem(player.getInventory(), 0, Material.BOOK, ChatColor.GREEN + "Inspect", null);
		createItem(player.getInventory(), 1, Material.BLAZE_ROD, ChatColor.AQUA + "Random TP",
				Arrays.asList(ChatColor.GRAY + "Teleportation I"));
		createItem(player.getInventory(), 3, Material.BEACON,
				ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)", null);
		createItemWithEnch(player.getInventory(), 5, Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Vanish", null,
				Enchantment.DURABILITY, 1);
		createItemWithEnch(player.getInventory(), 6, Material.FEATHER, ChatColor.GREEN + "Fly", null,
				Enchantment.DURABILITY, 1);
		createItem(player.getInventory(), 8, Material.WATCH,
				ChatColor.DARK_BLUE + "Gamemode Changer" + ChatColor.GRAY + "(Right Click)", null);
	}

	public static boolean isStaffItem(ItemStack item) {
		return getStaffItems().contains(item);
	}

	public static void createItem(Inventory inventory, int slot, Material item, String displayName, List<String> lore) {

		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		item2.setItemMeta(meta);

		inventory.setItem(slot, item2);

		while (!getStaffItems().contains(item2))
			getStaffItems().add(item2);
	}

	public static void createItemWithEnch(Inventory inventory, int slot, Material item, String displayName,
			List<String> lore, Enchantment enchantment, int level) {
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		meta.addEnchant(enchantment, level, true);
		item2.setItemMeta(meta);

		inventory.setItem(slot, item2);

		while (!getStaffItems().contains(item2))
			getStaffItems().add(item2);

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
		lore.add(ChatColor.RED + "Bans: " + ChatColor.GOLD + bminfo.getBans(target));
		lore.add(ChatColor.RED + "Mutes: " + ChatColor.GOLD + bminfo.getMutes(target));
		lore.add(ChatColor.RED + "Kicks: " + ChatColor.GOLD + bminfo.getKicks(target));
		lore.add(ChatColor.RED + "Warns: " + ChatColor.GOLD + bminfo.getWarns(target));

		meta.setLore(lore);
		item2.setItemMeta(meta);

		while (!getStaffItems().contains(item2))
			getStaffItems().add(item2);

		inventory.setItem(slot, item2);
	}

	public static void createGlassWithColor(Inventory inventory, int slot, String displayName, short durability) {
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta meta = glass.getItemMeta();
		meta.setDisplayName(displayName);
		glass.setDurability(durability);
		glass.setItemMeta(meta);

		inventory.setItem(slot, glass);

		while (!getStaffItems().contains(glass))
			getStaffItems().add(glass);

	}

	public static void addSkullsWithBMInfo(Inventory inventory) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();

		List<String> lore = null;

		for (Player all : StaffUtils.getOnlinePlayers()) {
			meta.setDisplayName(all.getName());
			meta.setOwner(all.getName());

			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			lore = new ArrayList<String>();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: " + ChatColor.GOLD + bminfo.getBans(all));
			lore.add(ChatColor.RED + "Mutes: " + ChatColor.GOLD + bminfo.getMutes(all));
			lore.add(ChatColor.RED + "Kicks: " + ChatColor.GOLD + bminfo.getKicks(all));
			lore.add(ChatColor.RED + "Warns: " + ChatColor.GOLD + bminfo.getWarns(all));
		}

		meta.setLore(lore);
		skull.setItemMeta(meta);

		if (!inventory.contains(skull))
			inventory.addItem(skull);
	}

	public static void createItemWithColor(Inventory inventory, Material item, int slot, String displayName, int data) {
		ItemStack it = new ItemStack(item, 1, (short) data);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayName);
		it.setItemMeta(meta);
		inventory.setItem(slot, it);
	}

	public static void createArmor(Inventory inventory, int helmetSlot, int chestplateSlot, int leggingsSlot,
			int bootsSlot, Player owner) {
		ItemStack helmet = owner.getInventory().getHelmet();
		ItemStack chestplate = owner.getInventory().getChestplate();
		ItemStack leggings = owner.getInventory().getChestplate();
		ItemStack boots = owner.getInventory().getBoots();

		inventory.setItem(helmetSlot, helmet);
		inventory.setItem(chestplateSlot, chestplate);
		inventory.setItem(leggingsSlot, leggings);
		inventory.setItem(bootsSlot, boots);
	}

	public static void createPotionEffectWithUpdate(Inventory inventory, Player player, Material mat, List<String> lore) {
		for (PotionEffect effect : getPotionEffects(player)) {
			ItemStack potion = new ItemStack(mat);
			ItemMeta meta = potion.getItemMeta();

			lore.add("Active Potion Effect(s): " + ("" + effect.getType().getName().charAt(0)).toUpperCase()
					+ effect.getType().getName().substring(1));

			Map<List<String>, Integer> t = new HashMap<List<String>, Integer>();
			t.put(lore, effect.getDuration());
			int duration = t.get(lore);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (duration != 0) {
						int i = t.get(lore);
						t.put(lore, i--);
					} else
						cancel();
				}
			}.runTaskTimer(Staff.getInstance(), 20L, 20L);
			lore.add(ChatColor.AQUA + "Duration: " + t.get(lore));

			lore.add(ChatColor.AQUA + "Amplifier(Potion Level): " + effect.getAmplifier());
			meta.setLore(lore);
		}
	}

	private static List<PotionEffect> getPotionEffects(Player player) {
		List<PotionEffect> pots = new ArrayList<PotionEffect>();

		for (PotionEffect effect : player.getActivePotionEffects())
			if (effect != null)
				pots.add(effect);
			else
				return null;

		return pots;
	}

	public static void clear() {
		getStaffItems().clear();
	}
}