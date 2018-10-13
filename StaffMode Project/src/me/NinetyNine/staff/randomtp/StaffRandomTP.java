package me.NinetyNine.staff.randomtp;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.Vanisher;
import me.NinetyNine.staff.utils.interfaces.StaffInteractAbility;

public class StaffRandomTP implements StaffInteractAbility {
	@Override
	public void performAbility(Player player, ItemStack item) {
		if (Bukkit.getServer().getOnlinePlayers().size() < 2) {
			player.sendMessage(StaffUtils
					.format("&cYou cannot random teleport right now because there is only less than 2 people online!"));
			return;
		} else {
			Random random = new Random();
			int r = random.nextInt(StaffUtils.getOnlinePlayers().size());
			Player target = StaffUtils.getOnlinePlayers().get(r);
			if (!StaffUtils.isInStaffMode(target) && !Vanisher.isInVanish(target)) {
				if (target != null) {
					player.teleport(target);
					player.sendMessage(StaffUtils.format("&7Randomly teleported to " + target.getName()));
				}
			}
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
}