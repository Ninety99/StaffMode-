package me.NinetyNine.staff.bminfo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBMInfo implements Listener {

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getRightClicked() instanceof Player))
			return;

		if (e.getRightClicked() == null)
			return;

		if (e.getPlayer().getItemInHand() != null)
			return;

		Player player = e.getPlayer();
		Player clicked = (Player) e.getRightClicked();
		player.performCommand("bminfo " + clicked.getName());
		return;
	}
}