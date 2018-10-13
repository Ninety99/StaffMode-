package me.NinetyNine.staff.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBlockPlace implements Listener {

	@EventHandler(ignoreCancelled = false)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (StaffItems.isStaffItem(e.getItemInHand()))
			e.setCancelled(true);
		else
			return;
	}
}