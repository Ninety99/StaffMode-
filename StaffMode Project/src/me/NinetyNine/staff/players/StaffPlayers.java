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

import me.NinetyNine.staff.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.bminfo.StaffBMInfoInterface;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers implements Listener {

	/*
	 * Not completed yet
	 */

	public static Inventory i = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	public static Inventory i2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	public static Inventory i3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	public static Inventory i4 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	public static Inventory i5 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

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

		e.getPlayer().openInventory(i);
	}

	public void addStuff(Player player) {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			List<String> lore = new ArrayList<String>();

			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: " + bminfo.getBans(all));
			lore.add(ChatColor.RED + "Mutes: " + bminfo.getMutes(all));
			lore.add(ChatColor.RED + "Kicks: " + bminfo.getKicks(all));
			lore.add(ChatColor.RED + "Warns: " + bminfo.getWarns(all));
			System.out.println("got all the bminfo details");

			if (StaffUtils.getNumberOfContents(i) <= 54) {
				StaffItems.createSkullWithBMInfo(i, all.getName(), lore, all.getName());
				addMisc(1);
				return;
			} else {
				if (StaffUtils.getNumberOfContents(i2) <= 54) {
					StaffItems.createSkullWithBMInfo(i2, all.getName(), lore, all.getName());
					addMisc(2);
					return;
				} else {
					if (StaffUtils.getNumberOfContents(i3) <= 54) {
						StaffItems.createSkullWithBMInfo(i3, all.getName(), lore, all.getName());
						addMisc(3);
						return;
					} else {
						if (StaffUtils.getNumberOfContents(i4) <= 54) {
							StaffItems.createSkullWithBMInfo(i4, all.getName(), lore, all.getName());
							addMisc(4);
							return;
						} else {
							if (StaffUtils.getNumberOfContents(i5) <= 54) {
								StaffItems.createSkullWithBMInfo(i5, all.getName(), lore, all.getName());
								addMisc(5);
								return;
							}
						}
					}
				}
			}
		}
	}

	private void addMisc(int invNumber) {
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.GOLD + "Left Click:");
		lore.add("Left click to check ban information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Right Click:");
		lore.add("Right click to check mute information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Middle Click:");
		lore.add("Middle click to teleport to the specified player!");
		System.out.println("got click info lore");

		if (invNumber == 1) {
			addGlass(i);
			addEmerald(i, lore);
			addNext(i);
			System.out.println("got item(misc) for inv 1");
		}

		if (invNumber == 2) {
			addPrevious(i2);
			addGlass(i2);
			addEmerald(i2, lore);
			addNext(i2);
			System.out.println("got item(misc) for inv 2");
		}

		if (invNumber == 3) {
			addPrevious(i3);
			addGlass(i3);
			addEmerald(i3, lore);
			addNext(i3);
			System.out.println("got item(misc) for inv 3");
		}

		if (invNumber == 4) {
			addPrevious(i4);
			addGlass(i4);
			addEmerald(i4, lore);
			addNext(i4);
			System.out.println("got item(misc) for inv 4");
		}

		if (invNumber == 5) {
			addPrevious(i5);
			addGlass(i5);
			addEmerald(i5, lore);
			System.out.println("got item(misc) for inv 5");
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
		StaffItems.createItem(i5, 49, Material.EMERALD, ChatColor.GOLD + "Info", lore);
	}

	private void addNext(Inventory inventory) {
		StaffItems.createItem(inventory, 53, Material.BARRIER, ChatColor.GREEN + "Next", null);
	}

	private void addPrevious(Inventory inventory) {
		StaffItems.createItem(inventory, 45, Material.BARRIER, ChatColor.RED + "Previous", null);
	}
}