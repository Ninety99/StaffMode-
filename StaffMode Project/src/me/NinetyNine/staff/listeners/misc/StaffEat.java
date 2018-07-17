package me.NinetyNine.staff.listeners.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffEat implements Listener {

	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		e.setCancelled(true);
		return;
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getEntity())))
			return;
		
		e.setCancelled(true);
		return;
	}
}