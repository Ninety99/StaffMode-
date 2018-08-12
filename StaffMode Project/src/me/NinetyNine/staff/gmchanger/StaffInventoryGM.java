package me.NinetyNine.staff.gmchanger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.actionbar.StaffActionBar;
import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.inventory.StaffInventoryClickInterface;

public class StaffInventoryGM implements StaffInventoryClickInterface {

	@Override
	public void performAbility(Player player, Inventory inventory, ItemStack item) {
		if (item.getType().equals(Material.GRASS)) {
			if (player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))) {
				player.closeInventory();
				player.setGameMode(GameMode.SURVIVAL);
				if (Flyer.isInFly(player))
					Flyer.setFly(player);
				StaffActionBar.sendActionBar(player,
						ChatColor.GRAY + "[" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "] " + ChatColor.GREEN
								+ "You are now in " + ChatColor.GOLD + "SURVIVAL" + ChatColor.GREEN + " mode!"
								+ ChatColor.GRAY + " [" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "]",
						5);
				player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &6SURVIVAL"));
				return;
			} else {
				player.sendMessage(
						StaffUtils.format("&cYou do not have permissions to change your gamemode to &fCREATIVE"));
				player.closeInventory();
				return;
			}
		}

		if (item.getType().equals(Material.WOOL)) {
			if (player.hasPermission(StaffConfig.getString("gmchangerpermgm1"))) {
				player.closeInventory();
				player.setGameMode(GameMode.CREATIVE);
				StaffActionBar.sendActionBar(player,
						ChatColor.GRAY + "[" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "] " + ChatColor.GREEN
								+ "You are now in " + ChatColor.WHITE + "CREATIVE" + ChatColor.GREEN + " mode!"
								+ ChatColor.GRAY + " [" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "]",
						5);
				player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &fCREATIVE"));
				return;
			} else {
				player.sendMessage(
						StaffUtils.format("&cYou do not have permissions to change your gamemode to &fCREATIVE"));
				player.closeInventory();
				return;
			}
		}

		if (item.getType().equals(Material.GLASS)) {
			if (player.hasPermission(StaffConfig.getString("gmchangerpermgm2"))) {
				player.closeInventory();
				player.setGameMode(GameMode.ADVENTURE);
				if (Flyer.isInFly(player))
					Flyer.setFly(player);
				StaffActionBar.sendActionBar(player,
						ChatColor.GRAY + "[" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "] " + ChatColor.GREEN
								+ "You are now in " + ChatColor.DARK_GREEN + "ADVENTURE" + ChatColor.GREEN + " mode!"
								+ ChatColor.GRAY + " [" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "]",
						5);
				player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &aADVENTURE"));
				return;
			} else {
				player.sendMessage(
						StaffUtils.format("&cYou do not have permissions to change your gamemode to &9ADVENTURE"));
				player.closeInventory();
				return;
			}
		}

		if (item.getType().equals(Material.EYE_OF_ENDER)) {
			if (player.hasPermission(StaffConfig.getString("gmchangerpermgm3"))) {
				player.closeInventory();
				player.setGameMode(GameMode.SPECTATOR);
				StaffActionBar.sendActionBar(player,
						ChatColor.GRAY + "[" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "] " + ChatColor.GREEN
								+ "You are now in " + ChatColor.AQUA + "SPECTATOR" + ChatColor.GREEN + " mode!"
								+ ChatColor.GRAY + " [" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "]",
						5);
				player.sendMessage(StaffUtils.format("&9Your gamemode has been changed to &bSPECTATOR"));
				player.sendMessage(StaffUtils.format("&7Use /staff quitgmsp to quit &bSPECTATOR &7mode!"));
				return;
			} else {
				player.sendMessage(
						StaffUtils.format("&cYou do not have permissions to change your gamemode to &rSPECTATOR"));
				player.closeInventory();
				return;
			}
		}
	}

	@Override
	public String getInventoryTitle() {
		return ChatColor.RED + "Gamemode Changer";
	}

	@Override
	public List<ItemStack> getInventoryItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.GRASS));
		items.add(new ItemStack(Material.WOOL));
		items.add(new ItemStack(Material.GLASS));
		items.add(new ItemStack(Material.EYE_OF_ENDER));
		return items;
	}
}