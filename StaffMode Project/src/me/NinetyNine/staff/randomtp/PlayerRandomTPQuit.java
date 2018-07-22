package me.NinetyNine.staff.randomtp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerRandomTPQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (StaffRandomTP.getAll().isEmpty())
			return;

		if (StaffRandomTP.getAll().contains(e.getPlayer()))
			StaffRandomTP.getAll().remove(e.getPlayer());
		else
			return;
	}
}
