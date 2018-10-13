package me.NinetyNine.staff.misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffAntiNexus implements Listener {

	@EventHandler(ignoreCancelled = false)
	public void onBlockBreak(BlockBreakEvent e) {
		if (!(e.getBlock().getType() == Material.ENDER_STONE))
			return;

		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		boolean isWorldWhitelisted = false;
		for (String worlds : StaffConfig.getStringList("anniWorlds")) {
			if (e.getPlayer().getWorld().getName().equalsIgnoreCase(worlds)) {
				isWorldWhitelisted = true;
				continue;
			}
		}

		if (isWorldWhitelisted == true) {
			e.setCancelled(true);
			e.getPlayer()
					.sendMessage(StaffUtils.format("&cYou are in Staff mode, therefore you cannot break a nexus."));
			Bukkit.getServer().getLogger()
					.info(e.getPlayer().getName() + " tried to break a nexus while in Staff mode!");
		}
	}
}