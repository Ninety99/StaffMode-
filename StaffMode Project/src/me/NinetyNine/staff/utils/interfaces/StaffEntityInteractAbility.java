package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffEntityInteractAbility extends Listener {

	public void performAbility(Player player, ItemStack item, Player clicked);

	public ItemStack getAbilityItem();

	public String getAbilityName();

	public default boolean isStaffItem(ItemStack item) {
		if (item == null)
			return false;

		item = new ItemStack(item.getType());
		ItemMeta meta = item.getItemMeta();

		if (meta == null)
			return false;

		return item.getType().equals(getAbilityItem().getType())
				&& meta.getDisplayName().equals(ChatColor.stripColor(getAbilityName()));
	}

	@EventHandler
	public default void abilityUse(final PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player))
			return;

		System.out.println("right clicked is player (normal)");
		
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		
		System.out.println("is in staff mode (normal)");

		if (e.getPlayer().getItemInHand() == null)
			return;
		
		System.out.println("is not null (normal)");
		
		if (e.getPlayer().getItemInHand().getType().equals(getAbilityItem().getType()))
			performAbility(e.getPlayer(), e.getPlayer().getItemInHand(), (Player) e.getRightClicked());

		System.out.println("done [normal]");

	}
}