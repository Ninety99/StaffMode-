package me.NinetyNine.staff.chest;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractChestAbility;

public class StaffChest implements StaffInteractChestAbility {

	@Override
	public void performAbility(Player player, Block block) {
		if (!(block.getState() instanceof Chest))
			return;
		
		Chest chest = (Chest) block.getState();
		player.openInventory(chest.getInventory());
		player.sendMessage(StaffUtils.format("&9Opening chest silently"));
	}
}