package me.NinetyNine.staff.listeners.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBlockPlace implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		
		e.setCancelled(true);
		return;
	}
}
