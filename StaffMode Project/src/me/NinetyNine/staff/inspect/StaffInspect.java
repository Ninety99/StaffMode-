package me.NinetyNine.staff.inspect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffEntityInteractAbility;;

public class StaffInspect implements StaffEntityInteractAbility {

	@Override
	public void performAbility(Player player, ItemStack item, Player clicked) {

		String helmet = "";
		String chestplate = "";
		String leggings = "";
		String boots = "";

		if (clicked.getInventory().getHelmet() != null)
			helmet = clicked.getInventory().getHelmet().toString();

		if (clicked.getInventory().getChestplate() != null)
			chestplate = clicked.getInventory().getChestplate().toString();

		if (clicked.getInventory().getLeggings() != null)
			leggings = clicked.getInventory().getLeggings().toString();

		if (clicked.getInventory().getBoots() != null)
			boots = clicked.getInventory().getBoots().toString();

		player.sendMessage(StaffUtils.format("&cPlayer Information:"));
		player.sendMessage(StaffUtils.format("&aArmor: "));

		if (helmet == "")
			player.sendMessage(StaffUtils.format(" &0- &cHelmet: &bNone"));

		player.sendMessage(StaffUtils.format(
				" &0- &cHelmet: &b" + ("" + helmet.charAt(0)).toUpperCase() + helmet.substring(1).toLowerCase()));

		if (chestplate == "")
			player.sendMessage(StaffUtils.format(" &0- &cChestplate: &bNone"));

		player.sendMessage(StaffUtils.format(" &0- &cChestplate: &b" + ("" + chestplate.charAt(0)).toUpperCase()
				+ chestplate.substring(1).toLowerCase()));

		if (leggings == "")
			player.sendMessage(StaffUtils.format("&0- &cLeggings: &bNone"));

		player.sendMessage(StaffUtils.format(
				"&0- &cLeggings: &b" + ("" + leggings.charAt(0)).toUpperCase() + leggings.substring(1).toLowerCase()));

		if (boots == "")
			player.sendMessage(StaffUtils.format("&0- &cBoots: &bNone"));

		player.sendMessage(StaffUtils
				.format(" &0- &cBoots: &b" + ("" + boots.charAt(0)).toUpperCase() + boots.substring(1).toLowerCase()));

		player.sendMessage(StaffUtils.format("&aPotion Effect(s): "));

		for (PotionEffect effects : getPotionEffects(player)) {
			player.sendMessage(" &0- " + StaffUtils.format(("" + effects.getType().getName().charAt(0)).toUpperCase()
					+ effects.getType().getName().substring(1).toLowerCase() + " &aDuration: &b" + effects.getDuration()
					+ " &aAmplifier: &b" + effects.getAmplifier()));
		}

		player.sendMessage(StaffUtils.format("&cBMInfo: "));
		player.performCommand("bminfo " + clicked.getName());
		player.openInventory(clicked.getInventory());
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