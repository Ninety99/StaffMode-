package me.NinetyNine.staff.listeners.misc;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffDrop implements Listener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;
		else {
			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop items whilst in Staff mode!"));
				return;
			} else {
				ItemStack item = e.getItemDrop().getItemStack();

				if (item.getType() == Material.BEACON || item.getType() == Material.BOOK
						|| item.getType() == Material.BLAZE_ROD || item.getType() == Material.TORCH
						|| item.getType() == Material.REDSTONE_TORCH_ON || item.getType() == Material.FEATHER
						|| item.getType() == Material.WATCH) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop that item!"));
					return;
				}
			}
		}
	}
}