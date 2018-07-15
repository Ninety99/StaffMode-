package me.NinetyNine.staff.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPickupItem implements Listener {

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		else
			e.setCancelled(true);
	}
}
