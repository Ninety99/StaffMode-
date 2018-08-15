package me.NinetyNine.staff.utils;

import java.util.List;

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
		Staff.getInstance().reloadConfig();
	}

	public static List<String> getStringList(String path){
		List<String> list = getConfig().getStringList(path);
		
		return list;
	}
	
	public static void removeStringFromList(String string) {
		List<String> list = getStringList("anniWorlds");
		list.remove(string);
		getConfig().set("anniWorlds", list);
		save();
	}
	
	public static void addStringToList(String string) {
		List<String> list = getStringList("anniWorlds");
		list.add(string);
		getConfig().set("anniWorlds", list);
		save();
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