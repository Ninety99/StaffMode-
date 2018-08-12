package me.NinetyNine.staff.misc;

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

		for (String worlds : StaffConfig.getConfig().getStringList("anniWorlds"))
			if (!(e.getPlayer().getWorld().getName().equals(worlds)))
				return;

		if (StaffUtils.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
			Bukkit.getServer().getLogger()
					.info(e.getPlayer().getName() + " tried to break a nexus while in Staff mode!");
		} else
			return;
	}
}