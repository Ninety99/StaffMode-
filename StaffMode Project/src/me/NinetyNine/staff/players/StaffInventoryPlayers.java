package me.NinetyNine.staff.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryPlayers implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (!(e.getInventory().getTitle().contains(ChatColor.BLUE + "Players")))
			return;

		if (e.getCurrentItem() == null)
			return;

		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);

		ItemStack item = e.getCurrentItem();

		if (item.getType().equals(Material.SKULL_ITEM)) {
			ItemMeta meta = item.getItemMeta();
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (meta.getDisplayName().equals(all.getName())) {
					if (e.getClick().equals(ClickType.LEFT)) {
						player.closeInventory();
						player.performCommand("bminfo " + all.getName() + " -bans");
						break;
					}

					if (e.getClick().equals(ClickType.RIGHT)) {
						player.closeInventory();
						player.performCommand("bminfo " + all.getName() + " -mutes");
						break;
					}

					if (e.getClick().equals(ClickType.MIDDLE)) {
						player.closeInventory();
						player.teleport(all);
						player.sendMessage(StaffUtils.format("&7You have been teleported to " + all.getName()));
						break;
					}
				} else
					break;
				return;
			}
		}

		if (item.getType().equals(Material.BARRIER)) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next")) {
				if (e.getInventory().getTitle().equals(StaffPlayers.i.getTitle())) {
					openInventory(StaffPlayers.i2, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i2.getTitle())) {
					openInventory(StaffPlayers.i3, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i3.getTitle())) {
					openInventory(StaffPlayers.i4, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i4.getTitle())) {
					openInventory(StaffPlayers.i5, player);
					return;
				}
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Previous")) {
				if (e.getInventory().getTitle().equals(StaffPlayers.i2.getTitle())) {
					openInventory(StaffPlayers.i, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i3.getTitle())) {
					openInventory(StaffPlayers.i2, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i4.getTitle())) {
					openInventory(StaffPlayers.i3, player);
					return;
				}

				if (e.getInventory().getTitle().equals(StaffPlayers.i5.getTitle())) {
					openInventory(StaffPlayers.i4, player);
					return;
				}
			}
		}
	}

	private void openInventory(Inventory inventory, Player player) {
		player.openInventory(inventory);
	}
}