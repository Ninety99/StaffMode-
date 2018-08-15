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
		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize() + 9,
				ChatColor.BLUE + clicked.getName() + "'s inventory");

		for (int i = 0; i < clicked.getInventory().getSize(); i++) {
			if (clicked.getInventory().getItem(i) != null)
				clickedInventory.setItem(i, clicked.getInventory().getItem(i));
		}

		StaffItems.createArmor(clicked, clickedInventory, 36, 37, 38, 39);

		List<String> lore = new ArrayList<>();

		lore.add(ChatColor.GOLD + "Potion Effect(s): ");
		List<String> effectstring = new ArrayList<String>();

		if (getPotionEffects(clicked).size() > 0) {
			for (PotionEffect effects : getPotionEffects(clicked)) {
				effectstring.add(("" + effects.getType().getName().charAt(0)).toUpperCase()
						+ effects.getType().getName().substring(1).toLowerCase() + ChatColor.RED + " Duration: "
						+ ChatColor.GOLD + effects.getDuration() + ChatColor.RED + " Amplifier: " + ChatColor.GOLD
						+ effects.getAmplifier());
			}
		} else
			lore.add(ChatColor.RED + "None");

		for (String effects : effectstring)
			lore.add(ChatColor.GOLD + effects);

		StaffItems.createItem(clickedInventory, 41, Material.GLASS_BOTTLE, ChatColor.GREEN + "Potion Information",
				lore);

		StaffItems.createItemWithBMInfo(clicked, clickedInventory, 43, Material.EMERALD,
				ChatColor.LIGHT_PURPLE + "BMInfo Information");

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

	private static List<PotionEffect> getPotionEffects(Player player) {
		List<PotionEffect> pots = new ArrayList<PotionEffect>();

		for (PotionEffect effect : player.getActivePotionEffects()) {
			if (effect != null)
				pots.add(effect);
			else
				return null;
		}

		return pots;
	}
}