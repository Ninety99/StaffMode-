package me.NinetyNine.staff.listeners.bminfo;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class StaffBMInfoHook {

	protected void getHistory(Player checker, OfflinePlayer target) {
		checker.performCommand("bminfo " + target.getName());
	}

	protected int getBans(Player checker, OfflinePlayer target) {
		checker.performCommand("bminfo " + target.getName() + " -b");
		return Integer.parseInt("0");
	}

	protected int getMutes(Player checker, OfflinePlayer target) {
		checker.performCommand("bminfo " + target.getName() + " -m");
		return Integer.parseInt("0");
	}

	protected int getKicks(Player checker, OfflinePlayer target) {
		checker.performCommand("bminfo " + target.getName() + " -k");
		return Integer.parseInt("0");
	}

	protected int getWarns(Player checker, OfflinePlayer target) {
		checker.performCommand("bminfo " + target.getName() + " -w");
		return Integer.parseInt("0");
	}
}