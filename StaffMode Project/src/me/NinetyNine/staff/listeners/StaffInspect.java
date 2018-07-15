package me.NinetyNine.staff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.staff.utils.StaffUtils;;

public class StaffInspect implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();

		if (!StaffUtils.isInStaffMode(player))
			return;

		if (player.getItemInHand() == null)
			return;

		if (player.getItemInHand().getType() == Material.AIR)
			return;

		if (!(player.getItemInHand().getType() == Material.BOOK))
			return;

		if (player.getItemInHand().getItemMeta().getDisplayName() == ChatColor.GREEN + "Inspect")
			return;

		Player clicked = (Player) e.getRightClicked();

		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize(),
				"&8" + clicked.getName() + "'s inventory");
		clickedInventory.setContents(clicked.getInventory().getContents());

		player.openInventory(clickedInventory);
		player.sendMessage(StaffUtils.format("&9Opening " + clicked.getName() + "'s inventory"));
		return;
	}
}