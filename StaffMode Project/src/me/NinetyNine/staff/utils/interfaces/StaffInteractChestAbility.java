package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInteractChestAbility extends Listener {

	public void performAbility(Player player, Block block);
	
	@EventHandler
	public default void onPlayerInteract(final PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		e.setCancelled(true);
		performAbility(e.getPlayer(), e.getClickedBlock());
	}
}
