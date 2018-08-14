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
				e.setCancelled(true);
				Block block = e.getBlock();
				Material blockType = block.getType();
				if (blockType == Material.GRASS)
					e.getPlayer().getInventory().addItem(new ItemStack(Material.GRASS));
				else if (blockType == Material.LONG_GRASS) {
					ItemStack grass = new ItemStack(Material.LONG_GRASS, 1, (short) 1);
					e.getPlayer().getInventory().addItem(grass);
				} else if (blockType == Material.WOOD) {
					ItemStack wood = new ItemStack(Material.WOOD, 1, (short) 13);
					e.getPlayer().getInventory().addItem(wood);
				} else
					e.getPlayer().getInventory().addItem(new ItemStack(blockType));

				block.setType(Material.AIR);
				return;
			} else
				return;
		}
	}
}
