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

import lombok.Getter;
import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffFly implements Listener {

	@Getter
	private static List<Player> fly = new ArrayList<Player>();

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
		
		if (!(e.getItem().getType() == Material.FEATHER))
			return;

		if (e.getItem().getItemMeta().getDisplayName() == ChatColor.DARK_BLUE + "Fly")
			return;

		if (!getFly().contains(player)) {
			getFly().add(player);
			Flyer.setFly(player);
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &aenabled!"));
			return;
		} else {
			getFly().remove(player);
			Flyer.removeFly(player);
			player.sendMessage(StaffUtils.format("&9Fly &7mode has been &cdisabled!"));
			return;
		}
	}

	public static void clear() {
		getFly().clear();
	}
}