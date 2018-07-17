package me.NinetyNine.staff.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryPlayers implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getInventory().getTitle().equals(ChatColor.BLUE + "Players")))
			return;

		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);

		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (e.getCurrentItem() == null)
			return;
		
		if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);

			ItemStack skull = e.getCurrentItem();
			SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();

			for (Player all : Bukkit.getServer().getOnlinePlayers())
				if (skullmeta.getOwner().equalsIgnoreCase(all.getName())) {
					e.setCancelled(true);
					player.closeInventory();
					player.teleport(all);
					player.sendMessage(StaffUtils.format("&7You have been teleported to " + all.getName()));
					break;
				}
		}
	}
}
