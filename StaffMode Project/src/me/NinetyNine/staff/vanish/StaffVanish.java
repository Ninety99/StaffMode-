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
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.Vanisher;
import me.NinetyNine.staff.utils.interfaces.StaffInteractOnOrOffAbility;

public class StaffVanish implements StaffInteractOnOrOffAbility {

	@Getter
	private static List<Player> vanishedPlayers = new ArrayList<Player>();

	@Override
	public void performAbility(Player player, ItemStack item) {
		if (Vanisher.isInVanish(player)) {
			StaffActionBar.sendActionBar(player, ChatColor.RED + "You are now unvanished", 1);
			Vanisher.unvanish(player);
			off(item);
			player.sendMessage(StaffUtils.format("&3Vanish &7has been &cdisabled&7!"));
			return;
		} else {
			StaffActionBar.sendActionBar(player, ChatColor.GREEN + "You are now vanished", 1);
			Vanisher.vanish(player);
			on(item);
			player.sendMessage(StaffUtils.format("&3Vanish &7has been &aenabled&7!"));
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

	@Override
	public void on(ItemStack item) {
		item.setType(Material.REDSTONE_TORCH_ON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(getAbilityNameWhenOn());
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		item.setItemMeta(meta);

		if (!(StaffItems.getStaffItems().contains(item)))
			StaffItems.getStaffItems().add(item);
	}

	@Override
	public void off(ItemStack item) {
		item.setType(Material.TORCH);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(getAbilityNameWhenOff());
		meta.removeEnchant(Enchantment.DURABILITY);
		item.setItemMeta(meta);

		if (!(StaffItems.getStaffItems().contains(item)))
			StaffItems.getStaffItems().add(item);
	}

	public static void clear() {
		getVanishedPlayers().clear();
	}
}