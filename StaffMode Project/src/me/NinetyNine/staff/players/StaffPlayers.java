package me.NinetyNine.staff.players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractAbility;

public class StaffPlayers implements StaffInteractAbility {

	@Getter
	private static int playersOnline = StaffUtils.getOnlinePlayers().size();

	@Getter
	private static Inventory i = Bukkit.createInventory(null, 54,
			ChatColor.BLUE + "Players " + ChatColor.DARK_GRAY + "(Players: " + playersOnline + ")");
	@Getter
	private static Inventory i2 = Bukkit.createInventory(null, 54,
			ChatColor.BLUE + "Players (2) " + ChatColor.DARK_GRAY + "(Players: " + playersOnline + ")");
	@Getter
	private static Inventory i3 = Bukkit.createInventory(null, 54,
			ChatColor.BLUE + "Players (3) " + ChatColor.DARK_GRAY + "(Players: " + playersOnline + ")");
	@Getter
	private static Inventory i4 = Bukkit.createInventory(null, 54,
			ChatColor.BLUE + "Players (4) " + ChatColor.DARK_GRAY + "(Players: " + playersOnline + ")");
	@Getter
	private static Inventory i5 = Bukkit.createInventory(null, 54,
			ChatColor.BLUE + "Players (5) " + ChatColor.DARK_GRAY + "(Players: " + playersOnline + ")");

	@Override
	public void performAbility(Player player, ItemStack item) {
		addSkulls();
		addMisc(1);
		addMisc(2);
		addMisc(3);
		addMisc(4);
		addMisc(5);

		player.openInventory(i);
	}

	@Override
	public ItemStack getAbilityItem() {
		return new ItemStack(Material.BEACON);
	}

	@Override
	public String getAbilityName() {
		return ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)";
	}

	public void addSkulls() {
		if (StaffUtils.getNumberOfContents(i) <= 53)
			StaffItems.addSkullsWithBMInfo(i);
		else {
			if (StaffUtils.getNumberOfContents(i2) <= 53)
				StaffItems.addSkullsWithBMInfo(i2);
			else {
				if (StaffUtils.getNumberOfContents(i3) <= 53)
					StaffItems.addSkullsWithBMInfo(i3);
				else {
					if (StaffUtils.getNumberOfContents(i4) <= 53)
						StaffItems.addSkullsWithBMInfo(i4);
					else {
						if (StaffUtils.getNumberOfContents(i5) <= 53)
							StaffItems.addSkullsWithBMInfo(i5);
					}
				}
			}
		}
	}

	public void addMisc(int invNumber) {
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.RED + "Left Click:");
		lore.add(ChatColor.GRAY + "Left click to check the");
		lore.add(ChatColor.GRAY + "ban history of");
		lore.add(ChatColor.GRAY + "the specified player.");
		lore.add(ChatColor.RED + "Right Click:");
		lore.add(ChatColor.GRAY + "Right click to check the");
		lore.add(ChatColor.GRAY + "mute history of");
		lore.add(ChatColor.GRAY + "the specified player.");
		lore.add(ChatColor.RED + "Middle Click:");
		lore.add(ChatColor.GRAY + "Middle click to teleport");
		lore.add(ChatColor.GRAY + "to the specified player!");

		if (invNumber == 1) {
			addEmerald(i, lore);
			addGlass(i);
			addNext(i);
		}

		if (invNumber == 2) {
			addEmerald(i2, lore);
			addGlass(i2);
			addNext(i2);
			addPrevious(i2);
		}

		if (invNumber == 3) {
			addEmerald(i3, lore);
			addGlass(i3);
			addNext(i3);
			addPrevious(i3);
		}

		if (invNumber == 4) {
			addEmerald(i4, lore);
			addGlass(i4);
			addNext(i4);
			addPrevious(i4);
		}

		if (invNumber == 5) {
			addEmerald(i5, lore);
			addGlass(i5);
			addPrevious(i5);
		}
	}

	private void addGlass(Inventory inventory) {
		if (inventory.contains(Material.STAINED_GLASS_PANE))
			return;

		StaffItems.createGlassWithColor(inventory, 45, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 46, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 47, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 48, " ", (short) 7);

		// Emerald info item here

		StaffItems.createGlassWithColor(inventory, 50, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 51, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 52, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 53, " ", (short) 7);
	}

	private void addEmerald(Inventory inventory, List<String> lore) {
		StaffItems.createItem(inventory, 49, Material.EMERALD, ChatColor.AQUA + "Info", lore);
	}

	private void addPrevious(Inventory inventory) {
		StaffItems.createItem(inventory, 45, Material.BARRIER, ChatColor.RED + "Previous", null);
	}

	private void addNext(Inventory inventory) {
		StaffItems.createItem(inventory, 53, Material.BARRIER, ChatColor.GREEN + "Next", null);
	}
}