package me.NinetyNine.staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffRandomTP implements Listener {
	@Getter
	private static List<Player> all = new ArrayList<Player>();

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

		if (!(e.getItem().getType() == Material.BLAZE_ROD))
			return;

		if (e.getItem().getItemMeta().getDisplayName() == ChatColor.AQUA + "Random TP")
			return;

		for (Player p : Bukkit.getServer().getOnlinePlayers())
			getAll().add(p);

		if (Bukkit.getServer().getOnlinePlayers().size() < 2) {
			player.sendMessage(StaffUtils
					.format("&cYou cannot random teleport right now because there is only less than 2 people online!"));
			return;
		} else {
			Random random = new Random();
			int r = random.nextInt(all.size());

			Player target = all.get(r);
			if (target != null) {
				player.teleport(target);
				player.sendMessage(StaffUtils.format("&7Randomly teleported to " + target.getName()));
				return;
			}
			return;
		}
	}

	public static void clear() {
		getAll().clear();
	}
}