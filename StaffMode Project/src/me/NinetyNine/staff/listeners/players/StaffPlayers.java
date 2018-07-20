package me.NinetyNine.staff.listeners.players;

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
import me.NinetyNine.staff.listeners.bminfo.StaffBMInfoHook;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers extends StaffBMInfoHook implements Listener {

	@Getter
	private static Inventory realInventory = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;

		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (e.getItem().getType() != Material.BEACON)
			return;

		if (e.getItem().getItemMeta().getDisplayName() != ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)")
			return;

		Inventory inventory = null;
		Inventory inventory2 = null;
		Inventory inventory3 = null;

		int size = Bukkit.getServer().getOnlinePlayers().size();

		if (size > 9) {
			inventory = Bukkit.createInventory(null, size, ChatColor.BLUE + "Players");
		} else if (size == 8) {
			inventory = Bukkit.createInventory(null, size + 1, ChatColor.BLUE + "Players");
		} else if (size == 7) {
			inventory = Bukkit.createInventory(null, size + 2, ChatColor.BLUE + "Players");
		} else if (size == 6) {
			inventory = Bukkit.createInventory(null, size + 3, ChatColor.BLUE + "Players");
		} else if (size == 5) {
			inventory = Bukkit.createInventory(null, size + 4, ChatColor.BLUE + "Players");
		} else if (size == 4) {
			inventory = Bukkit.createInventory(null, size + 5, ChatColor.BLUE + "Players");
		} else if (size == 3) {
			inventory = Bukkit.createInventory(null, size + 6, ChatColor.BLUE + "Players");
		} else if (size == 2) {
			inventory = Bukkit.createInventory(null, size + 7, ChatColor.BLUE + "Players");
		} else if (size == 1) {
			inventory = Bukkit.createInventory(null, size + 8, ChatColor.BLUE + "Players");
		}

		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
			skullmeta.setOwner(all.getName());
			skullmeta.setDisplayName(ChatColor.GRAY + all.getName());
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(ChatColor.RED + "Bans: " + getBans(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Mutes " + getMutes(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Kicks " + getKicks(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Warns " + getWarns(e.getPlayer(), all.getPlayer()));
			skullmeta.setLore(lore);
			skull.setItemMeta(skullmeta);

			// 54

			for (int i = 0; i < inventory.getSize(); i++) {
				if (inventory.getContents().length != 54)
					inventory.setItem(i, skull);
				else if (inventory.getContents().length == 54) {
					inventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
					inventory2.setItem(i, skull);
				} else if (inventory2.getContents().length == 54) {
					inventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
					inventory3.setItem(i, skull);
				}
			}
		}

		if (realInventory.getContents().length != 54) {
			realInventory.setContents(inventory.getContents());
			StaffItems.createItem(realInventory, 38, new ItemStack(Material.BARRIER), ChatColor.GREEN + "Next", null);
			StaffItems.createItem(realInventory, 30, new ItemStack(Material.BARRIER), ChatColor.RED + "Previous", null);
		} else if (realInventory.getContents().length == 54) {
			realInventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
			realInventory2.setContents(inventory2.getContents());
			StaffItems.createItem(realInventory2, 38, new ItemStack(Material.BARRIER), ChatColor.GREEN + "Next", null);
			StaffItems.createItem(realInventory2, 30, new ItemStack(Material.BARRIER), ChatColor.RED + "Previous",
					null);
		}

		if (realInventory2.getContents().length != 54) {
			realInventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
			StaffItems.createItem(realInventory3, 30, new ItemStack(Material.BARRIER), ChatColor.RED + "Previous",
					null);
		}

		e.getPlayer().openInventory(realInventory);
	}
}