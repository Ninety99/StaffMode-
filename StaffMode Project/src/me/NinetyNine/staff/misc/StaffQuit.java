package me.NinetyNine.staff.misc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		else {
			StaffUtils.toggleStaff(e.getPlayer());
			if (e.getPlayer().hasPermission("staffmode.quitbypass"))
				return;
			else {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					if (all.hasPermission("staffmode.toggle"))
						e.setQuitMessage(StaffUtils
								.format("&98[&5Staff&8] &a" + e.getPlayer().getName() + " has left the server."));
					else
						return;
				}
			}
			return;
		}
	}
}
