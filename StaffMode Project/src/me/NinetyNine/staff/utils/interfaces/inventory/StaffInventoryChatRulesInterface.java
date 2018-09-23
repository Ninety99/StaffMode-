package me.NinetyNine.staff.utils.interfaces.inventory;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;

public interface StaffInventoryChatRulesInterface extends Listener {

	public void performAbility(Player player, Inventory inventory, ItemStack item);

	public String[] getInventoryTitles();

	public List<ItemStack> getInventoryItems();

	@EventHandler
	public default void onInventoryClick(final InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;

		if (!(StaffUtils.isInStaffMode(((Player) e.getWhoClicked()))))
			return;

		for (String titles : getInventoryTitles()) {
			if (!e.getInventory().getTitle().equals(titles))
				return;
		}

		if (e.getCurrentItem() == null)
			return;

		if (!e.getCurrentItem().hasItemMeta())
			return;

		for (ItemStack items : getInventoryItems()) {
			if (e.getCurrentItem().getType().equals(items.getType())) {
				e.setCancelled(true);
				performAbility((Player) e.getWhoClicked(), e.getInventory(), e.getCurrentItem());
			}
		}
	}
}
