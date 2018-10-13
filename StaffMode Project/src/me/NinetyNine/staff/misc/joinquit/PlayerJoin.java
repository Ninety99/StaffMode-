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
		if (!e.getPlayer().isOp()) {
			if (e.getPlayer().hasPermission("staffmode.toggle")) {
				for (Player all : StaffUtils.getOnlinePlayers()) {
					if (all.hasPermission("staffmode.toggle"))
						all.sendMessage(StaffUtils
								.format("&9[&5Staff&9] &3" + e.getPlayer().getName() + " &ahas joined the server."));
				}
			}
		}

		for (Player vanished : StaffVanish.getVanishedPlayers()) {
			if (!e.getPlayer().isOp())
				e.getPlayer().hidePlayer(vanished);
			else
				e.getPlayer().showPlayer(vanished);
		}
	}
}