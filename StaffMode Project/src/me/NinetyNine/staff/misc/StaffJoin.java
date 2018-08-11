package me.NinetyNine.staff.misc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;
		else {
			StaffUtils.toggleStaff(e.getPlayer());
			if (e.getPlayer().hasPermission("staffmode.joinbypass"))
				return;
			else {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					if (all.hasPermission("staffmode.toggle"))
						e.setJoinMessage(StaffUtils
								.format("&98[&5Staff&8] &a" + e.getPlayer().getName() + " has joined the server."));
					else
						return;
				}
			}
		}
	}
}
