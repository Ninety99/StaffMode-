package me.NinetyNine.staff.actionbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StaffActionBar implements Listener {

	/*
	 * From a guy at bukkit.org :D
	 */

	public static String nmsver;
	public static boolean works = true;
	public static boolean useOldMethods = false;
	public static Plugin plugin;

	public static void sendActionBar(Player player, String message) {
		if (!player.isOnline())
			return;

		ActionBarMessageEvent actionBarMessageEvent = new ActionBarMessageEvent(player, message);
		Bukkit.getPluginManager().callEvent(actionBarMessageEvent);
		if (actionBarMessageEvent.isCancelled())
			return;

		if (nmsver.startsWith("v1_12_"))
			sendActionBarPost112(player, message);
		else
			sendActionBarPre112(player, message);
	}

	private static void sendActionBarPost112(Player player, String message) {
		if (!player.isOnline()) {
			return;
		}

		try {
			Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
			Object craftPlayer = craftPlayerClass.cast(player);
			Object ppoc;
			Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
			Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
			Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
			Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
			Class<?> chatMessageTypeClass = Class.forName("net.minecraft.server." + nmsver + ".ChatMessageType");
			Object[] chatMessageTypes = chatMessageTypeClass.getEnumConstants();
			Object chatMessageType = null;

			for (Object obj : chatMessageTypes) {
				if (obj.toString().equals("GAME_INFO"))
					chatMessageType = obj;
			}

			Object o = c2.getConstructor(new Class<?>[] { String.class }).newInstance(message);
			ppoc = c4.getConstructor(new Class<?>[] { c3, chatMessageTypeClass }).newInstance(o, chatMessageType);
			Method m1 = craftPlayerClass.getDeclaredMethod("getHandle");
			Object h = m1.invoke(craftPlayer);
			Field f1 = h.getClass().getDeclaredField("playerConnection");
			Object pc = f1.get(h);
			Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
			m5.invoke(pc, ppoc);
		} catch (Exception ex) {
			ex.printStackTrace();
			works = false;
		}
	}

	private static void sendActionBarPre112(Player player, String message) {
		if (!player.isOnline())
			return;

		try {
			Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
			Object craftPlayer = craftPlayerClass.cast(player);
			Object ppoc;
			Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
			Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
			if (useOldMethods) {
				Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
				Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
				Method m3 = c2.getDeclaredMethod("a", String.class);
				Object cbc = c3.cast(m3.invoke(c2, "{\"text\": \"" + message + "\"}"));
				ppoc = c4.getConstructor(new Class<?>[] { c3, byte.class }).newInstance(cbc, (byte) 2);
			} else {
				Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
				Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
				Object o = c2.getConstructor(new Class<?>[] { String.class }).newInstance(message);
				ppoc = c4.getConstructor(new Class<?>[] { c3, byte.class }).newInstance(o, (byte) 2);
			}
			Method m1 = craftPlayerClass.getDeclaredMethod("getHandle");
			Object h = m1.invoke(craftPlayer);
			Field f1 = h.getClass().getDeclaredField("playerConnection");
			Object pc = f1.get(h);
			Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
			m5.invoke(pc, ppoc);
		} catch (Exception ex) {
			ex.printStackTrace();
			works = false;
		}
	}

	public static void sendActionBar(Player player, String message, int duration) {
		new BukkitRunnable() {
			int dura = duration;

			public void run() {
				if (dura != 0) {
					sendActionBar(player, message);
					dura--;
				} else
					cancel();
			}
		}.runTaskTimer(plugin, 20L, (long) duration);
		
		new BukkitRunnable() {
			public void run() {
				
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

	public static void sendActionBarToAllPlayers(String message) {
		sendActionBarToAllPlayers(message, -1);
	}

	public static void sendActionBarToAllPlayers(String message, int duration) {
		for (Player p : Bukkit.getOnlinePlayers())
			sendActionBar(p, message, duration);
	}
}