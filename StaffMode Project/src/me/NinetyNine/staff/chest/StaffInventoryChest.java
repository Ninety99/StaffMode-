package me.NinetyNine.staff.chest;

import org.bukkit.block.Chest;
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

		if (e.getCurrentItem() == null)
			return;
		
		if (e.getInventory().getType() != InventoryType.CHEST && e.getInventory() instanceof Chest)
			return;

		e.setCancelled(true);
		return;
	}
}