package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (StaffUtils.getOnlinePlayers().contains(e.getPlayer()))
			StaffUtils.getOnlinePlayers().remove(e.getPlayer());

		if (StaffItems.getIn().containsKey(e.getPlayer())) {
			if (StaffItems.getInWithSkull().containsKey(e.getPlayer())) {
				if (StaffItems.getIn().get(e.getPlayer()).contains(StaffItems.getInWithSkull().get(e.getPlayer())))
					StaffItems.getIn().get(e.getPlayer()).removeItem(StaffItems.getInWithSkull().get(e.getPlayer()));
			}
			
			StaffItems.getIn().remove(e.getPlayer());
			StaffItems.getInWithSkull().remove(e.getPlayer());
		}
	}
}
