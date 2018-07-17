package me.NinetyNine.staff.listeners.chest;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffChest implements Listener {

	@Getter
	private static Map<Player, Chest> chest = new HashMap<Player, Chest>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;
		
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		
		if (!(e.getClickedBlock().getState() instanceof Chest))
			return;
		
		Player player = e.getPlayer();
		getChest().put(player, (Chest) e.getClickedBlock().getState());
		Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST);
		inventory.setContents(getChest().get(player).getBlockInventory().getContents());
		player.openInventory(inventory);
		player.sendMessage(StaffUtils.format("&9Opening chest silently"));
	}
	
	public static void clear() { 
		getChest().clear();
	}
}