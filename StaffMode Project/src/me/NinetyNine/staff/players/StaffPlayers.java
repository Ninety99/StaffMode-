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

import lombok.Getter;
import me.NinetyNine.staff.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.bminfo.StaffBMInfoInterface;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers implements Listener {

	/*
	 * Not completed yet
	 */

	@Getter
	private static Inventory i = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@Getter
	private static Inventory i2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@Getter
	private static Inventory i3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@Getter
	private static Inventory i4 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@Getter
	private static Inventory i5 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

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

		if (!e.getItem().hasItemMeta())
			return;

		if (!(e.getItem().getItemMeta().getDisplayName() != ChatColor.RED + "Players " + ChatColor.GRAY
				+ "(Right Click)"))
			return;

		openInventory(e.getPlayer());
	}

	public void openInventory(Player player) {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			List<String> lore = new ArrayList<String>();

			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: " + bminfo.getBans(all));
			lore.add(ChatColor.RED + "Mutes: " + bminfo.getMutes(all));
			lore.add(ChatColor.RED + "Kicks: " + bminfo.getKicks(all));
			lore.add(ChatColor.RED + "Warns: " + bminfo.getWarns(all));

			if (StaffUtils.getNumberOfContents(i) <= 54) {
				StaffItems.createSkullWithBMInfo(i, all.getName(), lore, all.getName());
				addMisc(i);
				return;
			} else {
				if (StaffUtils.getNumberOfContents(i2) <= 54) {
					StaffItems.createSkullWithBMInfo(i2, all.getName(), lore, all.getName());
					addMisc(i2);
					return;
				} else {
					if (StaffUtils.getNumberOfContents(i3) <= 54) {
						StaffItems.createSkullWithBMInfo(i3, all.getName(), lore, all.getName());
						addMisc(i3);
						return;
					}
				}
			}
		}
		
		player.openInventory(i);
	}

	private void addMisc(Inventory inventory) {
		StaffItems.createGlassWithColor(inventory, 45, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 46, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 47, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 48, " ", (short) 7);

		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.GOLD + "Left Click:");
		lore.add("Left click to check ban information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Right Click:");
		lore.add("Right click to check mute information");
		lore.add("about the specified player.");
		lore.add(ChatColor.GOLD + "Middle Click:");
		lore.add("Middle click to teleport to the specified player!");

		StaffItems.createItem(inventory, 49, Material.EMERALD, ChatColor.GOLD + "Info", lore);

		StaffItems.createGlassWithColor(inventory, 50, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 51, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 52, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 53, " ", (short) 7);
	}
}