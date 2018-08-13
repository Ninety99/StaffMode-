package me.NinetyNine.staff.chest;

import org.bukkit.GameMode;
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
		
		if (!(e.getInventory().getType().equals(InventoryType.CHEST)))
			return;
		
		if (e.getCurrentItem() == null)
			return;

		if (((Player) e.getWhoClicked()).getGameMode().equals(GameMode.CREATIVE))
			return;
		else
			e.setCancelled(true);
	}
}