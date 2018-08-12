package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffEntityInteractBMInfoAbility extends Listener {
	public void performAbility(Player player, Player clicked);

	@EventHandler
	public default void abilityUse(final PlayerInteractAtEntityEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getRightClicked() instanceof Player))
			return;

		if (e.getPlayer().getItemInHand() != null)
			return;

		performAbility(e.getPlayer(), (Player) e.getRightClicked());
	}
}