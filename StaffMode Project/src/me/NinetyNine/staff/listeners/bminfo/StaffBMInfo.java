package me.NinetyNine.staff.listeners.bminfo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class StaffBMInfo extends StaffBMInfoHook implements Listener {

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
		if ((e.getRightClicked() instanceof Player))
			return;

		if (e.getPlayer().getItemInHand() != null)
			return;
		else {
			Player player = e.getPlayer();
			Player clicked = (Player) e.getRightClicked();

			getHistory(player, clicked);
		}
	}
}