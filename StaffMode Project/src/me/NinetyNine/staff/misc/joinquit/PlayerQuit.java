package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.vanish.StaffVanish;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (StaffUtils.getOnlinePlayers().contains(e.getPlayer()))
			StaffUtils.getOnlinePlayers().remove(e.getPlayer());

		if (!e.getPlayer().hasPermission("staffmode.quitbypass")) {
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (all.hasPermission("staffmode.toggle"))
					e.setQuitMessage(
							StaffUtils.format("&98[&5Staff&8] &a" + e.getPlayer().getName() + " has left the server."));
			}
		}

		if (StaffItems.getIn().containsKey(e.getPlayer())) {
			if (StaffItems.getInWithSkull().containsKey(e.getPlayer())) {
				if (StaffItems.getIn().get(e.getPlayer()).contains(StaffItems.getInWithSkull().get(e.getPlayer())))
					StaffItems.getIn().get(e.getPlayer()).removeItem(StaffItems.getInWithSkull().get(e.getPlayer()));
			}

			StaffItems.getIn().remove(e.getPlayer());
			StaffItems.getInWithSkull().remove(e.getPlayer());
		}

		if (!e.getPlayer().hasPermission("staffmode.vanishbypass")) {
			for (Player vanished : StaffVanish.getVanishedPlayers())
				e.getPlayer().showPlayer(vanished);
		} else
			return;
	}
}
