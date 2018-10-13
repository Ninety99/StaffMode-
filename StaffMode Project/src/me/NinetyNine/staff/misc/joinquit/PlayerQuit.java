package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.vanish.StaffVanish;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		StaffUtils.unStaff(e.getPlayer());

		if (!e.getPlayer().isOp()) {
			if (e.getPlayer().hasPermission("staffmode.toggle")) {
				for (Player all : StaffUtils.getOnlinePlayers()) {
					if (all.hasPermission("staffmode.toggle"))
						all.sendMessage(StaffUtils
								.format("&8[&5Staff&8] &3" + e.getPlayer().getName() + " &ahas left the server."));
				}
			}
		}

		if (StaffItems.getIn().containsKey(e.getPlayer())) {
			if (StaffItems.getInWithSkull().containsKey(e.getPlayer())) {
				Inventory inv = StaffItems.getIn().get(e.getPlayer());

				if (inv.contains(StaffItems.getInWithSkull().get(e.getPlayer())))
					inv.removeItem(StaffItems.getInWithSkull().get(e.getPlayer()));
			}
		}

		for (Player vanished : StaffVanish.getVanishedPlayers())
			e.getPlayer().showPlayer(vanished);
	}
}
