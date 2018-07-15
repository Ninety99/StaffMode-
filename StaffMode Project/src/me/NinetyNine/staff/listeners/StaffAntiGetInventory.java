package me.NinetyNine.staff.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffAntiGetInventory implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!StaffUtils.isInStaffMode((Player) e.getWhoClicked()))
			return;

		e.setCancelled(true);
		return;
	}
}