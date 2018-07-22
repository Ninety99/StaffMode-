package me.NinetyNine.staff.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerVanishQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (!(StaffVanish.getAllPlayers().contains(e.getPlayer())))
			return;
		else {
			StaffVanish.getAllPlayers().remove(e.getPlayer());

			for (Player vanished : StaffVanish.getVanishedPlayers())
				e.getPlayer().showPlayer(vanished);
			
			return;
		}
	}
}
