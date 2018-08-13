package me.NinetyNine.staff.misc.joinquit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!(StaffUtils.getOnlinePlayers().contains(e.getPlayer())))
			StaffUtils.getOnlinePlayers().add(e.getPlayer());
	}
}