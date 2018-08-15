package me.NinetyNine.staff.bminfo;

import org.bukkit.OfflinePlayer;

public interface StaffBMInfoInterface {

	int getBans(OfflinePlayer target);

	int getMutes(OfflinePlayer target);

	int getKicks(OfflinePlayer target);

	int getWarns(OfflinePlayer target);
}