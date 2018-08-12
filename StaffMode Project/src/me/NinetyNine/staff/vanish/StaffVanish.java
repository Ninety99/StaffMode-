package me.NinetyNine.staff.vanish;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;
import me.NinetyNine.staff.actionbar.StaffActionBar;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.Vanisher;
import me.NinetyNine.staff.utils.interfaces.StaffInteractOnOrOffAbility;

public class StaffVanish implements StaffInteractOnOrOffAbility {

	@Getter
	private static List<Player> vanishedPlayers = new ArrayList<Player>();

	@Getter
	private static List<Player> allPlayers = new ArrayList<Player>();
	@Override
	public void performAbility(Player player, ItemStack item) {
		if (Vanisher.isInVanish(player)) {
			Vanisher.unvanish(player);
			StaffActionBar.sendActionBar(player, ChatColor.RED + "You are now unvanished", 1);
			off(item);
			player.sendMessage(StaffUtils.format("&9Vanish &7has been &cdisabled!"));
			return;
		} else {
			Vanisher.vanish(player);
			StaffActionBar.sendActionBar(player, ChatColor.GREEN + "You are currently vanished", 1);
			on(item);
			player.sendMessage(StaffUtils.format("&9Vanish &7has been &aenabled"));
			return;
		}
	}

	@Override
	public String getAbilityNameWhenOn() {
		return ChatColor.GREEN + "Vanish";
	}

	@Override
	public String getAbilityNameWhenOff() {
		return ChatColor.RED + "Vanish";
	}

	@Override
	public ItemStack getAbilityItemWhenOn() {
		return new ItemStack(Material.REDSTONE_TORCH_ON);
	}

	@Override
	public ItemStack getAbilityItemWhenOff() {
		return new ItemStack(Material.TORCH);
	}

	private void on(ItemStack a) {
		a.setType(Material.REDSTONE_TORCH_ON);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(ChatColor.GREEN + "Vanish");
		am.addEnchant(Enchantment.DURABILITY, 1, true);
		a.setItemMeta(am);
	}

	private void off(ItemStack a) {
		a.setType(Material.TORCH);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(ChatColor.RED + "Vanish");
		am.removeEnchant(Enchantment.DURABILITY);
		a.setItemMeta(am);
	}

	public static void clear() {
		getVanishedPlayers().clear();
		getAllPlayers().clear();
	}

}