package me.NinetyNine.staff.listeners.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffDrop implements Listener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;
		else {
			e.setCancelled(true);
			e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop items whilst in Staff mode!"));
			return;
		}
	}
}
