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
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractOnOrOffAbility;

public class StaffFly implements StaffInteractOnOrOffAbility {

	@Getter
	private static List<Player> fly = new ArrayList<Player>();

	@Override
	public void performAbility(Player player, ItemStack item) {
		if (Flyer.isInFly(player)) {
			getFly().remove(player);
			remove(item);
			Flyer.removeFly(player);
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &cdisabled!"));
			return;
		} else {
			getFly().add(player);
			Flyer.setFly(player);
			add(item);
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &aenabled!"));
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

	private void add(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.setDisplayName(ChatColor.GREEN + "Fly");
		item.setItemMeta(meta);
	}

	private void remove(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.removeEnchant(Enchantment.DURABILITY);
		meta.setDisplayName(ChatColor.RED + "Fly");
		item.setItemMeta(meta);
	}

	public static void clear() {
		getFly().clear();
	}
}