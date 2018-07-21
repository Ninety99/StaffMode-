package me.NinetyNine.staff;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;
import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffFly implements Listener {

	@Getter
	private static List<Player> fly = new ArrayList<Player>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Player player = e.getPlayer();

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (e.getItem().getType() != Material.FEATHER)
			return;

		if (!(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Fly")
				|| e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Fly")))
			return;

		if (Flyer.isInFly(player)) {
			getFly().remove(player);
			remove(e.getItem());
			Flyer.removeFly(player);
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &cdisabled!"));
			return;
		} else {
			getFly().add(player);
			Flyer.setFly(player);
			add(e.getItem());
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &aenabled!"));
			return;
		}
	}

	private void add(ItemStack feather) {
		ItemMeta featherm = feather.getItemMeta();
		featherm.addEnchant(Enchantment.DURABILITY, 1, true);
		featherm.setDisplayName(ChatColor.GREEN + "Fly");
		feather.setItemMeta(featherm);
	}

	private void remove(ItemStack feather) {
		ItemMeta featherm = feather.getItemMeta();
		featherm.removeEnchant(Enchantment.DURABILITY);
		featherm.setDisplayName(ChatColor.LIGHT_PURPLE + "Fly");
		feather.setItemMeta(featherm);
	}

	public static void clear() {
		getFly().clear();
	}
}