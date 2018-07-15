package me.NinetyNine.staff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffGMChanger implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;

		Player player = e.getPlayer();

		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == (Material.AIR))
			return;

		if (!(e.getItem().getType().equals(Material.MELON)))
			return;

		if (!(e.getItem().getItemMeta().getDisplayName()
				.equals(ChatColor.DARK_BLUE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)")))
			return;

		if (!(player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm1"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm2"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm3"))))
			return;

		Inventory gmInventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Gamemode Changer");

		/*
		 * TODO: Fix colors.
		 */
		
		StaffItems.createWool(gmInventory, 1, new ItemStack(Material.WOOL), ChatColor.GOLD + "Survival", 1);
		StaffItems.createWool(gmInventory, 3, new ItemStack(Material.WOOL, 1, (byte) 0), ChatColor.WHITE + "Creative",
				0);
		StaffItems.createWool(gmInventory, 5, new ItemStack(Material.WOOL, 1, (byte) 3),
				ChatColor.DARK_BLUE + "Adventure", 3);
		StaffItems.createWool(gmInventory, 7, new ItemStack(Material.WOOL, 1, (byte) 2),
				ChatColor.DARK_RED + "Spectator", 2);

		player.openInventory(gmInventory);
	}
}