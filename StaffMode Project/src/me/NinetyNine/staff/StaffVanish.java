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
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.Vanisher;

public class StaffVanish implements Listener {

	@Getter
	private static List<Player> vanishedPlayers = new ArrayList<Player>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (e.getItem().getType() != Material.REDSTONE_TORCH_ON && e.getItem().getType() != Material.TORCH)
			return;

		if (!e.getItem().hasItemMeta())
			return;

		if (!(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Vanish")
				|| e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vanish")))
			return;

		Player player = e.getPlayer();

		if (Vanisher.isInVanish(player)) {
			Vanisher.unvanish(player);
			getVanishedPlayers().remove(player);
			off(e.getItem());
			player.sendMessage(StaffUtils.format("&9Vanish &7has been &cdisabled!"));
			return;
		} else {
			Vanisher.vanish(player);
			getVanishedPlayers().add(player);
			on(e.getItem());
			player.sendMessage(StaffUtils.format("&9Vanish &7has been &aenabled"));
			return;
		}
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
	}
}