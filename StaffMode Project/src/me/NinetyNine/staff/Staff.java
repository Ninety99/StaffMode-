package me.NinetyNine.staff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.NinetyNine.staff.commands.StaffCommands;
import me.NinetyNine.staff.listeners.StaffFly;
import me.NinetyNine.staff.listeners.StaffInspect;
import me.NinetyNine.staff.listeners.StaffRandomTP;
import me.NinetyNine.staff.listeners.StaffVanish;
import me.NinetyNine.staff.listeners.gmchanger.StaffGMChanger;
import me.NinetyNine.staff.listeners.gmchanger.StaffInventoryGM;
import me.NinetyNine.staff.listeners.misc.StaffAntiNexus;
import me.NinetyNine.staff.listeners.misc.StaffDrop;
import me.NinetyNine.staff.listeners.misc.StaffHitEvent;
import me.NinetyNine.staff.listeners.misc.StaffJoin;
import me.NinetyNine.staff.listeners.misc.StaffPickupItem;
import me.NinetyNine.staff.listeners.misc.StaffQuit;
import me.NinetyNine.staff.listeners.players.StaffInventoryPlayers;
import me.NinetyNine.staff.listeners.players.StaffPlayers;
import me.NinetyNine.staff.utils.StaffConfig;

public class Staff extends JavaPlugin {

	@Getter
	private static Staff instance;

	@Override
	public void onEnable() {
		instance = this;

		registerListeners();
		registerCommand();
		StaffConfig.loadConfig();
		StaffConfig.save();
		Bukkit.getServer().getLogger().info("StaffMode has been enabled");
	}

	@Override
	public void onDisable() {
		StaffConfig.save();
		clearAll();
		Bukkit.getServer().getLogger().info("StaffMode has been disabled");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new StaffCommands(), this);
		pm.registerEvents(new StaffJoin(), this);
		pm.registerEvents(new StaffHitEvent(), this);
		pm.registerEvents(new StaffRandomTP(), this);
		pm.registerEvents(new StaffVanish(), this);
		pm.registerEvents(new StaffAntiNexus(), this);
		pm.registerEvents(new StaffConfig(), this);
		pm.registerEvents(new StaffDrop(), this);
		pm.registerEvents(new StaffInventoryGM(), this);
		pm.registerEvents(new StaffFly(), this);
		pm.registerEvents(new StaffInspect(), this);
		pm.registerEvents(new StaffPlayers(), this);
		pm.registerEvents(new StaffGMChanger(), this);
		pm.registerEvents(new StaffInventoryGM(), this);
		pm.registerEvents(new StaffDrop(), this);
		pm.registerEvents(new StaffInventoryPlayers(), this);
		pm.registerEvents(new StaffPickupItem(), this);
		pm.registerEvents(new StaffQuit(), this);
	}

	private void clearAll() {
		StaffFly.clear();
		StaffVanish.clear();
		StaffRandomTP.clear();
		StaffFly.clear();
	}

	private void registerCommand() {
		getCommand("staff").setExecutor(new StaffCommands());
	}
}