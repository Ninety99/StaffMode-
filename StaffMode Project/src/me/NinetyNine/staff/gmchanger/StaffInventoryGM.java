package me.NinetyNine.staff.gmchanger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryGM implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getInventory().getName().equals(ChatColor.RED + "Gamemode Changer")))
			return;

		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (e.getCurrentItem() == null)
			return;

		Player player = (Player) e.getWhoClicked();

		if (!(e.getCurrentItem().getType().equals(Material.AIR)) && e.getCurrentItem().hasItemMeta()) {
			if (e.getCurrentItem().getType().equals(Material.GRASS)) {
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))) {
					player.closeInventory();
					player.setGameMode(GameMode.SURVIVAL);
					if (Flyer.isInFly(player))
						Flyer.setFly(player);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &aSURVIVAL"));
					return;
				} else {
					e.setCancelled(true);
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &fCREATIVE"));
					player.closeInventory();
					return;
				}
			}

			if (e.getCurrentItem().getType().equals(Material.WOOL)) {
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm1"))) {
					player.closeInventory();
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &fCREATIVE"));
					return;
				} else {
					e.setCancelled(true);
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &fCREATIVE"));
					player.closeInventory();
					return;
				}
			}

			if (e.getCurrentItem().getType().equals(Material.GLASS)) {
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm2"))) {
					player.closeInventory();
					player.setGameMode(GameMode.ADVENTURE);
					if (Flyer.isInFly(player))
						Flyer.setFly(player);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &aADVENTURE"));
					return;
				} else {
					e.setCancelled(true);
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &9ADVENTURE"));
					player.closeInventory();
					return;
				}
			}

			if (e.getCurrentItem().getType().equals(Material.EYE_OF_ENDER)) {
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm3"))) {
					player.closeInventory();
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &bSPECTATOR"));
					player.sendMessage(StaffUtils.format("&7Use /staff quitgmsp to quit &bSPECTATOR &7mode!"));
					return;
				} else {
					e.setCancelled(true);
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &rSPECTATOR"));
					player.closeInventory();
					return;
				}
			}
		} else
			return;
	}
}