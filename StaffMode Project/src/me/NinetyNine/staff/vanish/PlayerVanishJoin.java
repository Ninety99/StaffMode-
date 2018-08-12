package me.NinetyNine.staff.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class PlayerVanishJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (StaffVanish.getAllPlayers().contains(e.getPlayer()))
			return;
		else {
			if (!e.getPlayer().hasPermission("staffmode.vanishbypass")) {
				for (Player vanished : StaffVanish.getVanishedPlayers())
					e.getPlayer().hidePlayer(vanished);
			}

			StaffUtils.getOnlinePlayers().add(e.getPlayer());
			return;
		}
	}
}