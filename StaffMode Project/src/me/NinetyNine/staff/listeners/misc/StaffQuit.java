package me.NinetyNine.staff.listeners.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		else {
			StaffUtils.toggleStaff(e.getPlayer());
			return;
		}
	}
}
