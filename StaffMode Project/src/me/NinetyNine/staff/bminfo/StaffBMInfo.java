package me.NinetyNine.staff.bminfo;

import org.bukkit.entity.Player;

import me.NinetyNine.staff.utils.interfaces.StaffEntityInteractBMInfoAbility;

public class StaffBMInfo implements StaffEntityInteractBMInfoAbility {

	@Override
	public void performAbility(Player player, Player clicked) {
		player.performCommand("bminfo " + clicked.getName());
	}
}