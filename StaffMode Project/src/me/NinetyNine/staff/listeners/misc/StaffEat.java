package me.NinetyNine.staff.listeners.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}
