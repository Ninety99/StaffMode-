package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInteractOnOrOffAbility extends Listener {

	public void performAbility(Player player, ItemStack item);

	public ItemStack getAbilityItemWhenOn();

	public ItemStack getAbilityItemWhenOff();

	public String getAbilityNameWhenOn();

	public String getAbilityNameWhenOff();

	public default boolean isStaffItem(ItemStack item) {
		if (item == null)
			return false;

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();

		return item.getType().equals(getAbilityItemWhenOn().getType())
				|| item.getType().equals(getAbilityItemWhenOff().getType())
						&& meta.getDisplayName().equals(getAbilityNameWhenOn())
				|| meta.getDisplayName().equals(getAbilityNameWhenOff());
	}
	
	public void on(ItemStack item);
	public void off(ItemStack item);

	@EventHandler
	public default void abilityUse(final PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;

		if (e.getItem() == null && !isStaffItem(e.getItem()))
			return;

		if (!e.getItem().hasItemMeta())
			return;

		if (e.getItem().getType().equals(getAbilityItemWhenOn().getType())
				|| e.getItem().getType().equals(getAbilityItemWhenOff().getType())) {
			if (e.getItem().getItemMeta().getEnchants().containsKey(Enchantment.DURABILITY))
				performAbility(e.getPlayer(), e.getItem());
			else if (e.getItem().getItemMeta().getDisplayName().equals(getAbilityNameWhenOff()))
				performAbility(e.getPlayer(), e.getItem());
		}
	}
}
