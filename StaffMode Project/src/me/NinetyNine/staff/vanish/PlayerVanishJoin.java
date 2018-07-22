package me.NinetyNine.staff.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerVanishJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (StaffVanish.getAllPlayers().contains(e.getPlayer()))
			return;
		else {
			StaffVanish.getAllPlayers().add(e.getPlayer());
			
			for (Player vanished : StaffVanish.getVanishedPlayers())
				e.getPlayer().hidePlayer(vanished);
			
			return;
		}
	}
}