package me.NinetyNine.staff.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffInventoryGM implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getInventory().getName().equals(ChatColor.RED + "Gamemode Changer")))
			return;

		if (e.getCurrentItem() == null)
			return;

		Player player = (Player) e.getWhoClicked();

		if (!(StaffUtils.isInStaffMode(player)))
			return;

		if (e.getCurrentItem().getType().equals(Material.WOOL) && e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);
			switch (e.getCurrentItem().getData().getData()) {
			default:
				break;
			case 1:
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))) {
					player.closeInventory();
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &aSURVIVAL"));
					return;
				} else {
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &5SURVIVAL"));
					player.closeInventory();
					break;
				}
			case 0:
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm1"))) {
					player.closeInventory();
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &fCREATIVE"));
					return;
				} else {
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &fCREATIVE"));
					player.closeInventory();
					break;
				}
			case 3:
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm2"))) {
					player.closeInventory();
					player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &aADVENTURE"));
					return;
				} else {
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &9ADVENTURE"));
					player.closeInventory();
					break;
				}
			case 2:
				if (player.hasPermission(StaffConfig.getString("gmchangerpermgm3"))) {
					player.closeInventory();
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &bSPECTATOR"));
					return;
				} else {
					player.sendMessage(
							StaffUtils.format("&cYou do not have permissions to change your gamemode to &rSPECTATOR"));
					player.closeInventory();
					break;
				}
			}
		}
	}
}