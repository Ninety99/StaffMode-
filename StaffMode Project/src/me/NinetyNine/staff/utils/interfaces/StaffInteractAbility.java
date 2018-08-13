package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInteractAbility extends Listener {
	public void performAbility(Player player, ItemStack item);

	public ItemStack getAbilityItem();

	public String getAbilityName();	

	@EventHandler
	public default void abilityUse(final PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;

		if (e.getItem() == null)
			return;

		if (!(StaffItems.isStaffItem(e.getItem())))
			return;

		if (!e.getItem().hasItemMeta())
			return;

		if (e.getItem().getType().equals(getAbilityItem().getType()))
			performAbility(e.getPlayer(), e.getItem());
	}
}