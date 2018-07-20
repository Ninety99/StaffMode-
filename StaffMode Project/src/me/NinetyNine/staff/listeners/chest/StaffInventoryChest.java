package me.NinetyNine.staff.listeners.chest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryChest implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (e.getCurrentItem() == null && e.getCurrentItem().getType() == Material.AIR)
			return;

		if (e.getInventory().getType() != InventoryType.CHEST)
			return;

		e.setCancelled(true);
		return;
	}
}