package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.vanish.StaffVanish;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!(StaffUtils.getOnlinePlayers().contains(e.getPlayer())))
			StaffUtils.getOnlinePlayers().add(e.getPlayer());

		if (!e.getPlayer().hasPermission("staffmode.vanishbypass")) {
			for (Player vanished : StaffVanish.getVanishedPlayers())
				e.getPlayer().hidePlayer(vanished);
		}

		if (!e.getPlayer().hasPermission("staffmode.joinbypass")) {
			for (Player all : StaffUtils.getOnlinePlayers()) {
				if (all.hasPermission("staffmode.toggle"))
					e.setJoinMessage(StaffUtils
							.format("&98[&5Staff&8] &a" + e.getPlayer().getName() + " has joined the server."));
			}
		}
	}
}