package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (StaffUtils.getOnlinePlayers().contains(e.getPlayer()))
			StaffUtils.getOnlinePlayers().remove(e.getPlayer());
	}
}
