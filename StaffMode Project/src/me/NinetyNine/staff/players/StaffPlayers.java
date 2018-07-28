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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
	private static Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory inventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory inventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

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
		
		int index = 0;
		
		if (getNumberofContents(inventory, index) <= 54) {
			addSkull(inventory);
			addMisc(1);
			setContents(inventory, realInventory);
		} else {
			if (getNumberofContents(inventory2, index) <= 54) {
				addSkull(inventory2);
				addMisc(2);
				setContents(inventory2, realInventory2);
			} else {
				if (getNumberofContents(inventory3, index) <= 54) {
					addSkull(inventory3);
					addMisc(3);
					setContents(inventory3, realInventory3);
				}
			}
		}

		e.getPlayer().openInventory(getRealInventory());
	}

	private void addMisc(int number) {
		if (getInventory(number) == inventory) {
			StaffItems.createItem(inventory, 53, new ItemStack(Material.BARRIER), "Next", null);
			return;
		} else if (getInventory(number) == inventory2) {
			StaffItems.createItem(inventory2, 45, new ItemStack(Material.BARRIER), "Previous", null);
			StaffItems.createItem(inventory2, 53, new ItemStack(Material.BARRIER), "Next", null);
			return;
		} else if (getInventory(number) == inventory3) {
			StaffItems.createItem(inventory3, 45, new ItemStack(Material.BARRIER), "Previous", null);
			return;
		}
	}

	private void setContents(Inventory from, Inventory to) {
		to.setContents(from.getContents());
	}

	private Inventory getInventory(int number) {
		if (number == 1)
			return inventory;
		else if (number == 2)
			return inventory2;
		else if (number == 3)
			return inventory3;

		return null;
	}

	private void addSkull(Inventory inventory) {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
			skullmeta.setOwner(all.getName());
			skullmeta.setDisplayName(all.getName());
			List<String> lore = new ArrayList<String>();

			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: " + bminfo.getBans(all.getPlayer()));
			lore.add(ChatColor.RED + "Mutes: " + bminfo.getMutes(all.getPlayer()));
			lore.add(ChatColor.RED + "Kicks: " + bminfo.getKicks(all.getPlayer()));
			lore.add(ChatColor.RED + "Warns: " + bminfo.getWarns(all.getPlayer()));

			skullmeta.setLore(lore);
			skull.setItemMeta(skullmeta);

			int index = 0;

			getNumberofContents(inventory, index);

			if (index >= 0 && index < 55)
				inventory.addItem(skull);
		}
	}

	private int getNumberofContents(Inventory inventory, int index) {
		for (int i = 0; i < inventory.getContents().length; i++) {
			for (ItemStack items : inventory.getContents()) {
				if (items == null)
					return 0;
				
				if (items.getType() != null)
					index++;
			}
		}
		return index;
	}
}