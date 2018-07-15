package me.NinetyNine.staff.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.Staff;

public class StaffConfig implements Listener {

	public static void loadConfig() {
		if (Staff.getInstance().getDataFolder().exists())
			return;

		getConfig().options().copyDefaults(true);
		
	}

	public static void set(String path, Object object) {
		getConfig().set(path, object);
		save();
	}

	public static void save() {
		Staff.getInstance().saveConfig();
	}

	public static String getString(String path) {
		return getConfig().getString(path);
	}
	
	public static int getInt(String path) {
		return getConfig().getInt(path);
	}

	public static FileConfiguration getConfig() {
		return Staff.getInstance().getConfig();
	}
}