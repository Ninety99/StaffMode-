package me.NinetyNine.staff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffAntiNexus implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!(e.getBlock().getType() == Material.ENDER_STONE))
			return;

		if (!(e.getPlayer().getWorld().getName().equals(StaffConfig.getString("anniWorld"))))
			return;
		
		if (StaffUtils.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
			Bukkit.getServer().getLogger()
					.info(StaffUtils.format(e.getPlayer().getName() + " tried to break a nexus while in Staff mode!"));
		} else
			return;
	}
}