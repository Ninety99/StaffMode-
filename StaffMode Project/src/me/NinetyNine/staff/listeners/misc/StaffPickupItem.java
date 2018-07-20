package me.NinetyNine.staff.listeners.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPickupItem implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		else {
			e.setCancelled(true);
			return;
		}
	}
}
