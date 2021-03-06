package me.NinetyNine.staff.fly;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;
import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractOnOrOffAbility;

public class StaffFly implements StaffInteractOnOrOffAbility {

	@Getter
	private static List<Player> fly = new ArrayList<Player>();

	@Override
	public void performAbility(Player player, ItemStack item) {
		if (Flyer.isInFly(player)) {
			getFly().remove(player);
			off(item);
			Flyer.removeFly(player);
			player.sendMessage(StaffUtils.format("&3Fly &7mode has been &cdisabled&7!"));
			return;
		} else {
			getFly().add(player);
			Flyer.setFly(player);
			on(item);
			player.sendMessage(StaffUtils.format("&3Fly &7mode has been &aenabled&7!"));
			return;
		}
	}

	@Override
	public ItemStack getAbilityItemWhenOn() {
		return new ItemStack(Material.FEATHER);
	}

	@Override
	public ItemStack getAbilityItemWhenOff() {
		return new ItemStack(Material.FEATHER);
	}

	@Override
	public String getAbilityNameWhenOn() {
		return ChatColor.GREEN + "Fly";
	}

	@Override
	public String getAbilityNameWhenOff() {
		return ChatColor.RED + "Fly";
	}

	@Override
	public void on(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.setDisplayName(getAbilityNameWhenOn());
		item.setItemMeta(meta);

		if (!(StaffItems.getStaffItems().contains(item)))
			StaffItems.getStaffItems().add(item);
	}

	@Override
	public void off(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.removeEnchant(Enchantment.DURABILITY);
		meta.setDisplayName(getAbilityNameWhenOff());
		item.setItemMeta(meta);

		if (!(StaffItems.getStaffItems().contains(item)))
			StaffItems.getStaffItems().add(item);
	}

	public static void clear() {
		getFly().clear();
	}
}