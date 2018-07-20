package me.NinetyNine.staff.listeners.bminfo;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class StaffBMInfo2 implements StaffBMInfoHook {

	@Override
	public void getHistory(Player checker, OfflinePlayer target) {
		// TODO: Send message to checker

		getBans(checker, target);
		getMutes(checker, target);
		getKicks(checker, target);
		getWarns(checker, target);
	}

	@Override
	public int getBans(Player checker, OfflinePlayer target) {
		// TODO: Implement
		return 0;
	}

	@Override
	public int getMutes(Player checker, OfflinePlayer target) {
		// TODO: Implement
		return 0;
	}

	@Override
	public int getKicks(Player checker, OfflinePlayer target) {
		// TODO: Implement
		return 0;
	}

	@Override
	public int getWarns(Player checker, OfflinePlayer target) {
		// TODO: Implement
		return 0;
	}
}