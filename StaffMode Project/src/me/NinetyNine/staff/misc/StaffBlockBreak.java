package me.NinetyNine.staff.misc;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;
		else {
			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				Block block = e.getBlock();
				Material blockType = block.getType();
				if (blockType == Material.GRASS)
					e.getPlayer().getInventory().addItem(new ItemStack(Material.GRASS));
				else if (blockType == Material.LONG_GRASS)
					e.getPlayer().getInventory().addItem(new ItemStack(Material.LONG_GRASS));
				else
					e.getPlayer().getInventory().addItem(new ItemStack(blockType));
				
				block.setType(Material.AIR);
				return;
			} else
				return;
		}
	}
}
