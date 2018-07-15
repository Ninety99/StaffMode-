package me.NinetyNine.staff.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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

		Player player = e.getPlayer();

		if (!StaffUtils.isInStaffMode(player))
			return;


		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;
		
		if (!(e.getItem().getType() == Material.INK_SACK))
			return;

		if (e.getItem().getItemMeta().getDisplayName() == ChatColor.RED + "Vanish")
			return;

		if (!getVanishedPlayers().contains(player)) {
			getVanishedPlayers().add(player);
			Vanisher.vanish(player);
			ItemStack a = new ItemStack(Material.INK_SACK, 1, (byte) 8);
			e.getItem().setType(a.getType());
			player.sendMessage(StaffUtils.format("&9You are now &avanished&9!"));
			return;
		} else {
			getVanishedPlayers().remove(player);
			Vanisher.unvanish(player);
			ItemStack a = new ItemStack(Material.INK_SACK, 1, (byte) 10);
			e.getItem().setType(a.getType());
			player.sendMessage(StaffUtils.format("&9You are now &cunvanished&9!"));
			return;
		}
	}

	public static void clear() {
		getVanishedPlayers().clear();
	}
}