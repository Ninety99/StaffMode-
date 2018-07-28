package me.NinetyNine.staff.gmchanger;

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
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getItem() == null)
			return;
		
		if (e.getItem().getType() != Material.WATCH)
			return;

		if (!e.getItem().hasItemMeta())
			return;

		if (!(e.getItem().getItemMeta().getDisplayName()
				.equals(ChatColor.DARK_BLUE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)")))
			return;

		Player player = e.getPlayer();

		if (!(player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm1"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm2"))
				|| player.hasPermission(StaffConfig.getString("gmchangerpermgm3")))) {
			player.sendMessage(StaffUtils.format("&cYou do not have permissions to use this feature!"));
			return;
		}

		Inventory gmInventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Gamemode Changer");

		StaffItems.createItem(gmInventory, 1, new ItemStack(Material.GRASS), ChatColor.GOLD + "Survival", null);
		StaffItems.createItem(gmInventory, 3, new ItemStack(Material.WOOL), ChatColor.WHITE + "Creative", null);
		StaffItems.createItem(gmInventory, 5, new ItemStack(Material.GLASS), ChatColor.DARK_BLUE + "Adventure", null);
		StaffItems.createItem(gmInventory, 7, new ItemStack(Material.EYE_OF_ENDER), ChatColor.DARK_RED + "Spectator",
				null);

		player.openInventory(gmInventory);
	}
}