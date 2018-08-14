package me.NinetyNine.staff.inspect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffEntityInteractAbility;;

public class StaffInspect implements StaffEntityInteractAbility {
	@Override
	public void performAbility(Player player, ItemStack item, Player clicked) {
		System.out.println("performing Ability");
		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize() + 9,
				ChatColor.DARK_GRAY + clicked.getName() + "'s inventory");

		clickedInventory.setContents(clicked.getInventory().getContents());
		StaffItems.createArmor(clickedInventory, 36, 37, 38, 39, clicked);

		List<String> lore = new ArrayList<String>();
		StaffItems.createPotionEffectWithUpdate(clickedInventory, player, Material.GLASS_BOTTLE, lore);
		StaffItems.createItem(clickedInventory, 40, Material.GLASS_BOTTLE, "Active Potion Effects", lore);

		List<String> lore2 = new ArrayList<String>();
		double health = clicked.getHealth() - 10.0;
		lore2.add(ChatColor.RED + "Health: " + health);
		lore2.add(ChatColor.RED + "Food Level: " + clicked.getFoodLevel());

		StaffItems.createItem(clickedInventory, 42, Material.SPECKLED_MELON, ChatColor.GRAY + "Player Info", lore2);

		StaffItems.createItemWithBMInfo(clicked, clickedInventory, 44, Material.EMERALD, ChatColor.RED + "BMInfo");

		player.openInventory(clickedInventory);
		player.sendMessage(StaffUtils.format("&9Opening " + clicked.getName() + "'s inventory"));
		return;
	}

	@Override
	public ItemStack getAbilityItem() {
		return new ItemStack(Material.BOOK);
	}

	@Override
	public String getAbilityName() {
		return ChatColor.LIGHT_PURPLE + "Inspect";
	}
}