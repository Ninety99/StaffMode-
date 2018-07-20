package me.NinetyNine.staff.listeners.misc;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBlockPlace implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (e.getBlock().getType().equals(Material.BEACON) || e.getBlock().getType().equals(Material.TORCH)
				|| e.getBlock().getType().equals(Material.REDSTONE_TORCH_ON)) {
			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				e.setCancelled(true);
				return;
			} else
				return;
		} else
			return;
	}
}
