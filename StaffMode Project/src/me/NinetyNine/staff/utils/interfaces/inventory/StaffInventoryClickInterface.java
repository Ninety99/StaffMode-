package me.NinetyNine.staff.utils.interfaces.inventory;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInventoryClickInterface extends Listener {

	public void performAbility(Player player, Inventory inventory, ItemStack item);

	public String getInventoryTitle();

	public List<ItemStack> getInventoryItems();

	@EventHandler
	public default void onInventoryClick(final InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;

		if (!(StaffUtils.isInStaffMode(((Player) e.getWhoClicked()))))
			return;

		if (!e.getInventory().getTitle().equals(getInventoryTitle()))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (!e.getCurrentItem().hasItemMeta())
			return;

		e.setCancelled(true);

		for (ItemStack items : getInventoryItems()) {
			if (e.getCurrentItem().getType().equals(items.getType()))
				performAbility((Player) e.getWhoClicked(), e.getInventory(), e.getCurrentItem());
		}
	}
}
