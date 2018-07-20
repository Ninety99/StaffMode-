package me.NinetyNine.staff.listeners.bminfo;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface StaffBMInfoInterface {

	void getHistory(Player checker, OfflinePlayer target);

	int getBans(Player checker, OfflinePlayer target);

	int getMutes(Player checker, OfflinePlayer target);

	int getKicks(Player checker, OfflinePlayer target);

	int getWarns(Player checker, OfflinePlayer target);
}