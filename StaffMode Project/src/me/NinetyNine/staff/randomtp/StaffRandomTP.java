package me.NinetyNine.staff.randomtp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.interfaces.StaffInteractAbility;

public class StaffRandomTP implements StaffInteractAbility {
	@Getter
	private static List<Player> all = new ArrayList<Player>();

	@Override
	public void performAbility(Player player, ItemStack item) {
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
			} else
				return;
		}
	}

	@Override
	public ItemStack getAbilityItem() {
		return new ItemStack(Material.BLAZE_ROD);
	}

	@Override
	public String getAbilityName() {
		return ChatColor.AQUA + "Random TP";
	}

	public static void clear() {
		getAll().clear();
	}

}