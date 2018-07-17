package me.NinetyNine.staff.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
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
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if (!StaffUtils.isInStaffMode(e.getPlayer()))
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (e.getItem().getType() != Material.INK_SACK)
			return;

		if (!e.getItem().hasItemMeta())
			return;

		if (e.getItem().getItemMeta().getDisplayName() != ChatColor.RED + "Vanish"
				|| e.getItem().getItemMeta().getDisplayName() != ChatColor.GREEN + "Vanished")
			return;

		Player player = e.getPlayer();

		if (!getVanishedPlayers().contains(player)) {
			getVanishedPlayers().add(player);
			Vanisher.vanish(player);
			e.getItem().setType(lime().getType());
			player.sendMessage(StaffUtils.format("&9You are now &avanished&9!"));
			return;
		} else {
			getVanishedPlayers().remove(player);
			Vanisher.unvanish(player);
			e.getItem().setType(gray().getType());
			player.sendMessage(StaffUtils.format("&9You are now &cunvanished&9!"));
			return;
		}
	}

	private ItemStack lime() {
		@SuppressWarnings("deprecation")
		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) DyeColor.LIME.getData());
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(ChatColor.GREEN + "Vanished");
		a.setItemMeta(am);

		return a;
	}

	private ItemStack gray() {
		@SuppressWarnings("deprecation")
		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) DyeColor.GRAY.getData());
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(ChatColor.RED + "Vanish");
		a.setItemMeta(am);

		return a;
	}

	public static void clear() {
		getVanishedPlayers().clear();
	}
}