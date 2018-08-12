package me.NinetyNine.staff.players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers implements Listener {

	/*
	 * Not completed yet
	 */

	public static Inventory i = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	public static Inventory i2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players (Page 2)");
	public static Inventory i3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players (Page 3)");
	public static Inventory i4 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players (Page 4)");
	public static Inventory i5 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players (Page 5)");

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() != Material.BEACON)
			return;

		if (!(e.getItem().getItemMeta().getDisplayName()
				.equals(ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)")))
			return;

		addStuff(e.getPlayer());
		addMisc(1);
		addMisc(2);
		addMisc(3);
		addMisc(4);
		addMisc(5);

		e.getPlayer().openInventory(i);
	}

	public void addStuff(Player player) {
		if (StaffUtils.getNumberOfContents(i) <= 54)
			StaffItems.addSkullsWithBMInfo(i);
		else {
			if (StaffUtils.getNumberOfContents(i2) <= 54)
				StaffItems.addSkullsWithBMInfo(i2);
			else {
				if (StaffUtils.getNumberOfContents(i3) <= 54)
					StaffItems.addSkullsWithBMInfo(i3);
				else {
					if (StaffUtils.getNumberOfContents(i4) <= 54)
						StaffItems.addSkullsWithBMInfo(i4);
					else {
						if (StaffUtils.getNumberOfContents(i5) <= 54)
							StaffItems.addSkullsWithBMInfo(i5);
					}
				}
			}
		}
	}

	public void addMisc(int invNumber) {
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.GOLD + "Left Click:");
		lore.add("Left click to check ban information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Right Click:");
		lore.add("Right click to check mute information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Middle Click:");
		lore.add("Middle click to teleport to the specified player!");

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
		StaffItems.createItem(inventory, 49, Material.EMERALD, ChatColor.GOLD + "Info", lore);
	}

	private void addPrevious(Inventory inventory) {
		StaffItems.createItem(inventory, 45, Material.BARRIER, ChatColor.RED + "Previous", null);
	}

	private void addNext(Inventory inventory) {
		StaffItems.createItem(inventory, 53, Material.BARRIER, ChatColor.GREEN + "Next", null);
	}
}