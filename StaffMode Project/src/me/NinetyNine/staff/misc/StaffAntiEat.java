package me.NinetyNine.staff.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffAntiEat implements Listener {

	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		else {
			e.setCancelled(true);
			e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot eat while in Staff Mode!"));
			return;
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getEntity())))
			return;
		else {
			e.setCancelled(true);
			return;
		}
	}
}