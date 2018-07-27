package me.NinetyNine.staff.bminfo;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.guildcraft.gcbanz.GCBanz;
import org.guildcraft.gcbanz.data.Type;
import org.guildcraft.gcbanz.data.Violation;

import me.NinetyNine.staff.utils.StaffUtils;

public class StaffBMInfoHook implements StaffBMInfoInterface {

	@Override
	public void getHistory(Player checker, OfflinePlayer target) {
		checker.sendMessage(StaffUtils.format("&9BMINFO: "));
		checker.sendMessage(ChatColor.RED + "Results for " + ChatColor.GOLD + target.getName());
		checker.sendMessage(ChatColor.RED + "Bans: " + ChatColor.GOLD + getBans(checker, target));
		checker.sendMessage(ChatColor.RED + "Mutes: " + ChatColor.GOLD + getMutes(checker, target));
		checker.sendMessage(ChatColor.RED + "Kicks: " + ChatColor.GOLD + getKicks(checker, target));
		checker.sendMessage(ChatColor.RED + "Warns: " + ChatColor.GOLD + getWarns(checker, target));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getBans(Player checker, OfflinePlayer target) {
		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.BAN)).size();
		
		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getMutes(Player checker, OfflinePlayer target) {
		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.MUTE)).size();
		
		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getKicks(Player checker, OfflinePlayer target) {
		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.KICK)).size();
		
		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getWarns(Player checker, OfflinePlayer target) {
		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.WARN)).size();
		
		return arg52;
	}
}