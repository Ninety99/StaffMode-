package me.NinetyNine.staff.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import lombok.Getter;

public class Ping implements Listener {

	@Getter
	private static final Ping instance = new Ping();

	private Class<?> getClass(String cp) throws ClassNotFoundException {
		return Class.forName("org.bukkit.craftbukkit." + getServerVersion() + "." + cp);
	}

	private String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().substring(23);
	}

	public int getPing(Player player) {
		try {
			Class<?> craftPlayer = getClass("entity.CraftPlayer");
			Method getHandle = craftPlayer.getMethod("getHandle");
			Object entityPlayer = getHandle.invoke(craftPlayer.cast(player));
			Field pingField = entityPlayer.getClass().getField("ping");
			return pingField.getInt(entityPlayer);
		} catch (ReflectiveOperationException e) {
			return 0;
		}
	}
}