package me.NinetyNine.staff.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (StaffUtils.isInStaffMode(e.getPlayer())) {
			StaffUtils.toggleStaff(e.getPlayer());
		} else
			return;
	}
}
