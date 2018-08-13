package me.NinetyNine.staff.inspect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffEntityInteractAbility;;

public class StaffInspect implements StaffEntityInteractAbility {
	@Override
	public void performAbility(Player player, ItemStack item, Player clicked) {
		System.out.println("performing Ability");
		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize() + 9,
				ChatColor.DARK_GRAY + clicked.getName() + "'s inventory");

		for (ItemStack items : clicked.getInventory().getContents()) {
			for (int i = 0; i < clicked.getInventory().getSize(); i++)
				clickedInventory.setItem(i, items);
		}

		clickedInventory.setItem(36, clicked.getInventory().getHelmet());
		clickedInventory.setItem(37, clicked.getInventory().getChestplate());
		clickedInventory.setItem(38, clicked.getInventory().getLeggings());
		clickedInventory.setItem(39, clicked.getInventory().getBoots());

		List<String> lore = new ArrayList<String>();
		for (PotionEffect effect : getPotionEffects(clicked)) {
			lore.add(ChatColor.GOLD + "Active Potion Effect(s): "
					+ ("" + effect.getType().getName().charAt(0)).toUpperCase());
			lore.add(ChatColor.AQUA + "Duration: " + effect.getDuration());
			lore.add(ChatColor.AQUA + "Amplifier(Potion Level): " + effect.getAmplifier());
		}

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

	private List<PotionEffect> getPotionEffects(Player player) {
		List<PotionEffect> pots = new ArrayList<PotionEffect>();

		for (PotionEffect effect : player.getActivePotionEffects())
			if (effect != null)
				pots.add(effect);
			else
				return null;

		return pots;
	}
}