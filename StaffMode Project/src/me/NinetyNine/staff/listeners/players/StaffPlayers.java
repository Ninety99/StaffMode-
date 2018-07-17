package me.NinetyNine.staff.listeners.players;

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
import org.bukkit.inventory.meta.SkullMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;

		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (!(e.getItem().getType() == Material.BEACON))
			return;

		if (e.getItem().getItemMeta().getDisplayName() == ChatColor.RED + "Players " + ChatColor.GRAY + "(Right Click)")
			return;

		/*
		 * Added + 7 because I tested it with my brother, and this is not finished yet
		 */
		
		Inventory inventory = Bukkit.createInventory(null, Bukkit.getServer().getOnlinePlayers().size() + 7,
				ChatColor.BLUE + "Players");
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
			skullmeta.setOwner(all.getName());
			skullmeta.setDisplayName(ChatColor.GRAY + all.getName());
			skull.setItemMeta(skullmeta);
			inventory.addItem(skull);
		}

		e.getPlayer().openInventory(inventory);
	}
}