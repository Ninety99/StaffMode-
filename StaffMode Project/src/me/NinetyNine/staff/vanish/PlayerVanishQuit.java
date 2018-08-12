package me.NinetyNine.staff.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class PlayerVanishQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (!(StaffUtils.getOnlinePlayers().contains(e.getPlayer())))
			return;
		else {
			if (!e.getPlayer().hasPermission("staffmode.vanishbypass")) {
				for (Player vanished : StaffVanish.getVanishedPlayers())
					e.getPlayer().showPlayer(vanished);
			}
			
			StaffUtils.getOnlinePlayers().remove(e.getPlayer());
			return;
		}
	}
}
