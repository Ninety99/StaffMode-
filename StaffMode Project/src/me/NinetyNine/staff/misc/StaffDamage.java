package me.NinetyNine.staff.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffDamage implements Listener {

	@EventHandler(ignoreCancelled = false)
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;

		Player player = (Player) e.getEntity();
		
		if (!(StaffUtils.isInStaffMode(player)))
			return;
		else
			e.setCancelled(true);
		return;
	}
}
