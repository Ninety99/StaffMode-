package me.NinetyNine.staff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.NinetyNine.staff.actionbar.StaffActionBar;
import me.NinetyNine.staff.bminfo.StaffBMInfo;
import me.NinetyNine.staff.chest.StaffChest;
import me.NinetyNine.staff.chest.StaffInventoryChest;
import me.NinetyNine.staff.chest.StaffPLChest;
import me.NinetyNine.staff.commands.StaffCommands;
import me.NinetyNine.staff.gmchanger.StaffGMChanger;
import me.NinetyNine.staff.gmchanger.StaffInventoryGM;
import me.NinetyNine.staff.inspect.StaffInspect;
import me.NinetyNine.staff.inspect.StaffInspectInventory;
import me.NinetyNine.staff.misc.StaffAntiEat;
import me.NinetyNine.staff.misc.StaffAntiNexus;
import me.NinetyNine.staff.misc.StaffBlockBreak;
import me.NinetyNine.staff.misc.StaffBlockPlace;
import me.NinetyNine.staff.misc.StaffDamage;
import me.NinetyNine.staff.misc.StaffDrop;
import me.NinetyNine.staff.misc.StaffHitEvent;
import me.NinetyNine.staff.misc.StaffInventoryClick;
import me.NinetyNine.staff.misc.StaffJoin;
import me.NinetyNine.staff.misc.StaffPickupItem;
import me.NinetyNine.staff.misc.StaffQuit;
import me.NinetyNine.staff.players.StaffInventoryPlayers;
import me.NinetyNine.staff.players.StaffPlayers;
import me.NinetyNine.staff.randomtp.PlayerRandomTPJoin;
import me.NinetyNine.staff.randomtp.PlayerRandomTPQuit;
import me.NinetyNine.staff.randomtp.StaffRandomTP;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.vanish.PlayerVanishJoin;
import me.NinetyNine.staff.vanish.PlayerVanishQuit;
import me.NinetyNine.staff.vanish.StaffVanish;

public class Staff extends JavaPlugin {

	@Getter
	private static Staff instance;

	@Override
	public void onEnable() {
		instance = this;

		registerListeners();
		registerActionBar();
		registerCommands();
		StaffPLChest.setupAnimationListener();
		StaffPLChest.setupSoundListener();
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
		pm.registerEvents(new StaffQuit(), this);
		pm.registerEvents(new StaffDrop(), this);
		pm.registerEvents(new StaffAntiEat(), this);
		pm.registerEvents(new StaffPickupItem(), this);
		pm.registerEvents(new StaffHitEvent(), this);
		pm.registerEvents(new StaffBlockPlace(), this);
		pm.registerEvents(new StaffChest(), this);
		pm.registerEvents(new StaffInventoryClick(), this);
		pm.registerEvents(new StaffBlockBreak(), this);
		pm.registerEvents(new StaffDamage(), this);

		pm.registerEvents(new StaffRandomTP(), this);
		pm.registerEvents(new PlayerRandomTPJoin(), this);
		pm.registerEvents(new PlayerRandomTPQuit(), this);

		pm.registerEvents(new StaffVanish(), this);
		pm.registerEvents(new PlayerVanishJoin(), this);
		pm.registerEvents(new PlayerVanishQuit(), this);

		pm.registerEvents(new StaffAntiNexus(), this);

		pm.registerEvents(new StaffBMInfo(), this);

		pm.registerEvents(new StaffInventoryGM(), this);
		pm.registerEvents(new StaffGMChanger(), this);

		pm.registerEvents(new StaffFly(), this);

		pm.registerEvents(new StaffInspect(), this);
		pm.registerEvents(new StaffInspectInventory(), this);

		pm.registerEvents(new StaffPlayers(), this);
		pm.registerEvents(new StaffInventoryPlayers(), this);

		pm.registerEvents(new StaffPLChest(), this);
		pm.registerEvents(new StaffInventoryChest(), this);

		pm.registerEvents(new StaffConfig(), this);
		
		pm.registerEvents(new StaffActionBar(), this);
	}

	private void clearAll() {
		StaffFly.clear();
		StaffVanish.clear();
		StaffRandomTP.clear();
		StaffChest.clear();
	}

	private void registerActionBar() {
		StaffActionBar.plugin = this;
		StaffActionBar.nmsver = Bukkit.getServer().getClass().getPackage().getName();
		StaffActionBar.nmsver = StaffActionBar.nmsver.substring(StaffActionBar.nmsver.lastIndexOf(".") + 1);

		if (StaffActionBar.nmsver.equalsIgnoreCase("v1_8_") && StaffActionBar.nmsver.equalsIgnoreCase("v1_9_")
				&& StaffActionBar.nmsver.equalsIgnoreCase("v1_10_") && StaffActionBar.nmsver.equalsIgnoreCase("v1_11_")
				&& StaffActionBar.nmsver.equalsIgnoreCase("v1_12_") || StaffActionBar.nmsver.startsWith("v1_7_")) {
			StaffActionBar.useOldMethods = true;
		}
	}

	private void registerCommands() {
		StaffCommands cmd = new StaffCommands();
		getCommand("staff").setExecutor(cmd);
	}
}