package me.NinetyNine.staff.bminfo;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface StaffBMInfoInterface {

	void getHistory(Player checker, OfflinePlayer target);

	int getBans(OfflinePlayer target);

	int getMutes(OfflinePlayer target);

	int getKicks(OfflinePlayer target);

	int getWarns(OfflinePlayer target);
}