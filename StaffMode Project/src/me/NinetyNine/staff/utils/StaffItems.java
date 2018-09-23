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
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import lombok.Getter;
import me.NinetyNine.staff.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.bminfo.StaffBMInfoInterface;

public class StaffItems implements Listener {

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

		if (!getStaffItems().contains(item2))
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

		if (!getStaffItems().contains(item2))
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

		if (!getStaffItems().contains(item2))
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

		if (!getStaffItems().contains(glass))
			getStaffItems().add(glass);
	}

	@Getter
	private static Map<Player, Inventory> In = new HashMap<Player, Inventory>();

	@Getter
	private static Map<Player, ItemStack> InWithSkull = new HashMap<Player, ItemStack>();

	public static void addSkullsWithBMInfo(Inventory inventory) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		for (Player all : StaffUtils.getOnlinePlayers()) {
			if (!getIn().containsKey(all))
				getIn().put(all, inventory);

			SkullMeta meta = (SkullMeta) skull.getItemMeta();

			meta.setDisplayName(all.getName());
			meta.setOwner(all.getName());

			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			List<String> lore = new ArrayList<String>();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: " + ChatColor.GOLD + bminfo.getBans(all));
			lore.add(ChatColor.RED + "Mutes: " + ChatColor.GOLD + bminfo.getMutes(all));
			lore.add(ChatColor.RED + "Kicks: " + ChatColor.GOLD + bminfo.getKicks(all));
			lore.add(ChatColor.RED + "Warns: " + ChatColor.GOLD + bminfo.getWarns(all));

			meta.setLore(lore);
			skull.setItemMeta(meta);

			if (!getStaffItems().contains(skull))
				getStaffItems().add(skull);

			if (!getIn().get(all).contains(skull)) {
				if (!getInWithSkull().containsKey(all)) {
					getInWithSkull().put(all, skull);
					inventory.addItem(skull);
				}
			}
		}
	}

	public static void createItemWithColor(Inventory inventory, Material item, int slot, String displayName, int data) {
		ItemStack it = new ItemStack(item, 1, (short) data);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayName);
		it.setItemMeta(meta);
		inventory.setItem(slot, it);

		if (!getStaffItems().contains(it))
			getStaffItems().add(it);
	}

	public static void createArmor(Player owner, Inventory inventory, int helmetSlot, int chestplateSlot,
			int leggingsSlot, int bootsSlot) {
		ItemStack helmet = null;
		ItemStack chestplate = null;
		ItemStack leggings = null;
		ItemStack boots = null;

		if (owner.getInventory().getHelmet() != null)
			helmet = owner.getInventory().getHelmet().clone();
		else
			createItem(inventory, helmetSlot, Material.ITEM_FRAME, ChatColor.RED + "None", null);

		if (owner.getInventory().getChestplate() != null)
			chestplate = owner.getInventory().getChestplate().clone();
		else
			createItem(inventory, chestplateSlot, Material.ITEM_FRAME, ChatColor.RED + "None", null);

		if (owner.getInventory().getLeggings() != null)
			leggings = owner.getInventory().getLeggings().clone();
		else
			createItem(inventory, leggingsSlot, Material.ITEM_FRAME, ChatColor.RED + "None", null);

		if (owner.getInventory().getBoots() != null)
			boots = owner.getInventory().getBoots().clone();
		else
			createItem(inventory, bootsSlot, Material.ITEM_FRAME, ChatColor.RED + "None", null);

		if (!getStaffItems().contains(helmet))
			getStaffItems().add(helmet);
		if (!getStaffItems().contains(chestplate))
			getStaffItems().add(chestplate);
		if (!getStaffItems().contains(leggings))
			getStaffItems().add(leggings);
		if (!getStaffItems().contains(boots))
			getStaffItems().add(boots);

		inventory.setItem(helmetSlot, helmet);
		inventory.setItem(chestplateSlot, chestplate);
		inventory.setItem(leggingsSlot, leggings);
		inventory.setItem(bootsSlot, boots);
	}

	public static void clear() {
		getIn().clear();
		getInWithSkull().clear();
	}
}