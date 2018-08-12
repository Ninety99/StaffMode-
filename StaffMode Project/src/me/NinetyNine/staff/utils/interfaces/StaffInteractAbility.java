package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInteractAbility extends Listener {

	/*
	 * Added this. I need to fix bugs first before trying to implement this.
	 * (28-7-2018)
	 */

	public void performAbility(Player player, ItemStack item);

	public ItemStack getAbilityItem();

	public String getAbilityName();

	public default boolean isStaffItem(ItemStack item) {
		if (item == null)
			return false;

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();

		return item.getType().equals(getAbilityItem().getType())
				&& meta.getDisplayName().equals(ChatColor.stripColor(getAbilityName()));
	}

	@EventHandler
	public default void abilityUse(final PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;

		if (e.getItem() == null && !isStaffItem(e.getItem()))
			return;

		if (e.getItem().getType().equals(getAbilityItem().getType()))
			performAbility(e.getPlayer(), e.getItem());

	}
}