package me.NinetyNine.staff.bminfo;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface StaffBMInfoInterface {

	void getHistory(Player checker, OfflinePlayer target);

	long getBans(Player checker, OfflinePlayer target);

	long getMutes(Player checker, OfflinePlayer target);

	long getKicks(Player checker, OfflinePlayer target);

	long getWarns(Player checker, OfflinePlayer target);
}