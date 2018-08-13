package me.NinetyNine.staff.chest;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractChestAbility;

public class StaffChest implements StaffInteractChestAbility {
	@Override
	public void performAbility(Player player, Block block) {
		if (!(block.getState() instanceof Chest))
			return;
		
		Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST);
		Chest chest = (Chest) block.getState();
		inventory.setContents(chest.getInventory().getContents());
		player.openInventory(inventory);
		player.sendMessage(StaffUtils.format("&9Opening chest silently"));
	}
}