package me.NinetyNine.staff.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface StaffAbility extends Listener {

	/*
	 * Added this. I need to fix bugs first before trying to implement this. (28-7-2018) 
	 */
	
	public void performAbility(Player player, ItemStack item);

	public ItemStack getAbilityItem(Player player);

	public String getAbilityName();

	public default boolean isStaffItem(Player player, ItemStack item, boolean hasOnOrOff) {
		if (!(hasOnOrOff))
			return item.getType() == getAbilityItem(player).getType();
		else
			return item.hasItemMeta();
	}

	@EventHandler
	public default void abilityUse(final PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;

		boolean hasOnOrOff = false;

		if (e.getItem() == null || !isStaffItem(e.getPlayer(), e.getItem(), hasOnOrOff))
			return;

		if (e.getItem().getItemMeta().getDisplayName().contains(getAbilityName())) {
			hasOnOrOff = true;
			performAbility(e.getPlayer(), e.getItem());
		} else
			return;
		hasOnOrOff = false;
	}
}