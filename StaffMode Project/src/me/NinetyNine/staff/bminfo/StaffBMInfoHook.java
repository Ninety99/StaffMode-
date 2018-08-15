package me.NinetyNine.staff.bminfo;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.OfflinePlayer;
import org.guildcraft.gcbanz.GCBanz;
import org.guildcraft.gcbanz.data.Type;
import org.guildcraft.gcbanz.data.Violation;

public class StaffBMInfoHook implements StaffBMInfoInterface {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getBans(OfflinePlayer target) {
		if (target == null)
			return 0;

		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		if ((ArrayList) arg49.get(Type.BAN) == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.BAN)).size();

		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getMutes(OfflinePlayer target) {
		if (target == null)
			return 0;

		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		if ((ArrayList) arg49.get(Type.MUTE) == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.MUTE)).size();

		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getKicks(OfflinePlayer target) {
		if (target == null)
			return 0;

		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		if ((ArrayList) arg49.get(Type.KICK) == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.KICK)).size();

		return arg52;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int getWarns(OfflinePlayer target) {
		if (target == null)
			return 0;

		ArrayList arg47 = GCBanz.sql.getHistory(target.getName());

		if (arg47 == null)
			return 0;

		Map arg49 = (Map) arg47.stream().collect(Collectors.groupingBy(Violation::getType));

		if (arg49 == null)
			return 0;

		if ((ArrayList) arg49.get(Type.WARN) == null)
			return 0;

		int arg52 = ((ArrayList) arg49.get(Type.WARN)).size();

		return arg52;
	}
}