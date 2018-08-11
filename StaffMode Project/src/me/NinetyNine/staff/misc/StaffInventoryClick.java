package me.NinetyNine.staff.misc;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (e.getView().getBottomInventory().getType() != InventoryType.PLAYER)
			return;

		if (((Player) e.getWhoClicked()).getGameMode() == GameMode.CREATIVE)
			return;

		if (e.getCurrentItem() == null)
			return;
		else {
			ItemStack current = e.getCurrentItem();
			if (StaffItems.isStaffItem(current))
				e.setCancelled(true);
			else
				return;
		}
	}
}
