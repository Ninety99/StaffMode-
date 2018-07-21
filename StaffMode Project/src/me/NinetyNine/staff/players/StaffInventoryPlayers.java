package me.NinetyNine.staff.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryPlayers implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (!(e.getInventory().getTitle().equals(ChatColor.BLUE + "Players")))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (e.getCurrentItem().getType() != Material.SKULL_ITEM && e.getCurrentItem().getType() != Material.BARRIER)
			return;

		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);

		ItemStack item = e.getCurrentItem();

		if (item.getType() == Material.SKULL_ITEM) {
			ItemMeta meta = item.getItemMeta();

			for (Player all : Bukkit.getServer().getOnlinePlayers())
				if (meta.getDisplayName().equals(all.getName())) {
					player.closeInventory();
					player.teleport(all);
					player.sendMessage(StaffUtils.format("&7You have been teleported to " + all.getName()));
					break;
				}
		}

		if (item.getType() == Material.BARRIER) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next")) {
				if (e.getInventory() == StaffPlayers.getRealInventory()) {
					player.closeInventory();
					player.openInventory(StaffPlayers.getRealInventory2());
					return;
				}

				if (e.getInventory() == StaffPlayers.getRealInventory2()) {
					player.closeInventory();
					player.openInventory(StaffPlayers.getRealInventory3());
				}
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Previous")) {
				if (e.getInventory() == StaffPlayers.getRealInventory2()) {
					player.closeInventory();
					player.openInventory(StaffPlayers.getRealInventory());
					return;
				}

				if (e.getInventory() == StaffPlayers.getRealInventory3()) {
					player.closeInventory();
					player.openInventory(StaffPlayers.getRealInventory2());
					return;
				}
			}
		}
	}
}