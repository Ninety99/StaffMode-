package me.NinetyNine.staff.misc;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffDrop implements Listener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;
		else {
			ItemStack item = e.getItemDrop().getItemStack();

			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop items whilst in Staff mode!"));
			} else {
				if (StaffItems.isStaffItem(item)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop that item!"));
				}
			}
		}
	}
}