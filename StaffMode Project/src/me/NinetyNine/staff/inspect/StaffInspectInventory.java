package me.NinetyNine.staff.inspect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInspectInventory implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!StaffUtils.isInStaffMode((Player) e.getWhoClicked()))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (!e.getInventory().getTitle().contains("'s inventory"))
			return;

		e.setCancelled(true);
	}
}
