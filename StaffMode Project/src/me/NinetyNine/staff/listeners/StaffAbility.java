package me.NinetyNine.staff.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffAbility extends Listener {
	
	/*
	 * I tried to do it like anni but I can't kek.
	 */
	
	public ItemStack getAbilityItem();

	public ItemStack getAbilityItemWhenOff();

	public String getAbilityName();

	public boolean performAbility(Player player, boolean isLeftClick, Player clicked);

	@EventHandler
	public default void abilityUse(final PlayerInteractEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		Player player = e.getPlayer();

		if (player.getWorld().getName().equals("world"))
			return;

		if (e.getItem() == null || !isSpecItem(e.getItem()))
			return;
		else {
			e.setCancelled(true);
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
				tryAbility(player, true);
			else
				tryAbility(player, false);
		}
	}

	@EventHandler
	public default void abilityUse(final PlayerInteractEntityEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		Player player = e.getPlayer();
		
		if (player.getWorld().getName().equals("world"))
			return;

		if (player.getItemInHand() == null || !isSpecItem(player.getItemInHand()))
			return;
		else {
			e.setCancelled(true);
			performAbility(player, false, (Player) e.getRightClicked());
		}
	}

	public default boolean isSpecItem(ItemStack stack) {
		return getAbilityItem().isSimilar(stack);
	}

	public default void tryAbility(Player player, boolean isLeftClick) {
		performAbility(player, isLeftClick, null);
	}
}