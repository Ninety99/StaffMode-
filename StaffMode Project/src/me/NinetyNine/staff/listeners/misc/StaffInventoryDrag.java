package me.NinetyNine.staff.listeners.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryDrag implements Listener {

	@EventHandler
	public void onInventoryDrag(InventoryDragEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;
		
		if (e.getInventory().getType() != InventoryType.PLAYER)
			return;

		if (e.getView().getBottomInventory() != e.getInventory())
			return;
		
		e.setCancelled(true);
		return;
	}
}
