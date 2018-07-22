package me.NinetyNine.staff.randomtp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerRandomTPJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (StaffRandomTP.getAll().isEmpty())
			return;

		if (StaffRandomTP.getAll().contains(e.getPlayer()))
			return;
		else {
			StaffRandomTP.getAll().add(e.getPlayer());
			return;
		}
	}
}
