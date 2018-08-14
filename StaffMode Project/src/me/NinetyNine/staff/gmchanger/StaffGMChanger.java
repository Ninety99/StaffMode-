package me.NinetyNine.staff.gmchanger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractAbility;

public class StaffGMChanger implements StaffInteractAbility {

	@Getter
	private static Inventory gmInventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Gamemode Changer");

	@Override
	public void performAbility(Player player, ItemStack item) {
		if (!(player.hasPermission("staffmode.gm0") || player.hasPermission("staffmode.gm1")
				|| player.hasPermission("staffmode.gm2") || player.hasPermission("staffmode.gm3"))) {
			player.sendMessage(StaffUtils.format("&cYou do not have permissions to use this feature!"));
			return;
		}

		StaffItems.createItem(gmInventory, 1, Material.GRASS, ChatColor.GOLD + "Survival", null);
		StaffItems.createItem(gmInventory, 3, Material.WOOL, ChatColor.WHITE + "Creative", null);
		StaffItems.createItem(gmInventory, 5, Material.GLASS, ChatColor.DARK_BLUE + "Adventure", null);
		StaffItems.createItem(gmInventory, 7, Material.EYE_OF_ENDER, ChatColor.DARK_RED + "Spectator", null);

		player.openInventory(gmInventory);
	}

	@Override
	public ItemStack getAbilityItem() {
		return new ItemStack(Material.WATCH);
	}

	@Override
	public String getAbilityName() {
		return ChatColor.DARK_PURPLE + "Gamemode Changer " + ChatColor.GRAY + "(Right Click)";
	}
}