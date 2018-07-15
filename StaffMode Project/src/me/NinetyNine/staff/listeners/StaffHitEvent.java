package me.NinetyNine.staff.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffHitEvent implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player))
			return;

		if (StaffUtils.isInStaffMode((Player) e.getDamager())) {
			e.setCancelled(true);
			return;
		}
	}
}
