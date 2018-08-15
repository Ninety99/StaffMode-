package me.NinetyNine.staff.inspect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
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

		for (ItemStack items : clicked.getInventory().getContents()) {
			for (int i = 0; i < clicked.getInventory().getSize(); i++) {
				clickedInventory.setItem(i, items);
				System.out.println("clickedInventory.setItem(" + i + ", " + items.toString() + ")");
			}
		}

		StaffItems.createArmor(clicked, clickedInventory, 36, 37, 38, 39);

		List<String> lore = new ArrayList<>();

		lore.add(ChatColor.GOLD + "Potion Effect(s): ");
		List<String> effectstring = null;

		for (PotionEffect effects : getPotionEffects(player)) {
			effectstring = new ArrayList<>();
			effectstring.add(("" + effects.getType().getName().charAt(0)).toUpperCase()
					+ effects.getType().getName().substring(1) + ChatColor.RED + " Duration: " + ChatColor.GOLD
					+ effects.getDuration() + ChatColor.RED + " Amplifier: " + ChatColor.GOLD + effects.getAmplifier());
			System.out.println("adding potion effects..");
		}
		Strings.join(effectstring, ",");
		System.out.println("Strings.join");

		for (String effects : effectstring)
			lore.add(ChatColor.GOLD + effects);
		System.out.println("added potions");

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