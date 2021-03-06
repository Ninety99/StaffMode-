package me.NinetyNine.staff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.NinetyNine.staff.actionbar.StaffActionBar;
import me.NinetyNine.staff.bminfo.StaffBMInfo;
import me.NinetyNine.staff.chatrules.StaffChatRulesInventory;
import me.NinetyNine.staff.chatrules.StaffChatRulesSet;
import me.NinetyNine.staff.chatrules.utils.DetailUtils;
import me.NinetyNine.staff.chest.StaffChest;
import me.NinetyNine.staff.chest.StaffInventoryChest;
import me.NinetyNine.staff.chest.StaffPLChest;
import me.NinetyNine.staff.commands.StaffCommands;
import me.NinetyNine.staff.fly.StaffFly;
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
import me.NinetyNine.staff.misc.StaffPickupItem;
import me.NinetyNine.staff.misc.joinquit.PlayerJoin;
import me.NinetyNine.staff.misc.joinquit.PlayerQuit;
import me.NinetyNine.staff.players.StaffInventoryPlayers;
import me.NinetyNine.staff.players.StaffPlayers;
import me.NinetyNine.staff.randomtp.StaffRandomTP;
import me.NinetyNine.staff.utils.Flyer;
import me.NinetyNine.staff.utils.Ping;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;
import me.NinetyNine.staff.utils.Vanisher;
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
		giveItemsBack();
		clearAll();
		
		Bukkit.getServer().getLogger().info("StaffMode has been disabled");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();

		pm.registerEvents(new DetailUtils(), this);
		pm.registerEvents(new Flyer(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new Vanisher(), this);

		pm.registerEvents(new StaffActionBar(), this);
		pm.registerEvents(new StaffAntiEat(), this);
		pm.registerEvents(new StaffAntiNexus(), this);
		pm.registerEvents(new StaffBlockBreak(), this);
		pm.registerEvents(new StaffBlockPlace(), this);
		pm.registerEvents(new StaffBMInfo(), this);
		pm.registerEvents(new StaffCommands(), this);
		pm.registerEvents(new StaffConfig(), this);
		pm.registerEvents(new StaffChatRulesInventory(), this);
		pm.registerEvents(new StaffChatRulesSet(), this);
		pm.registerEvents(new StaffChest(), this);
		pm.registerEvents(new StaffDamage(), this);
		pm.registerEvents(new StaffDrop(), this);
		pm.registerEvents(new StaffFly(), this);
		pm.registerEvents(new StaffGMChanger(), this);
		pm.registerEvents(new StaffHitEvent(), this);
		pm.registerEvents(new StaffInspect(), this);
		pm.registerEvents(new StaffInspectInventory(), this);
		pm.registerEvents(new StaffInventoryChest(), this);
		pm.registerEvents(new StaffInventoryClick(), this);
		pm.registerEvents(new StaffInventoryGM(), this);
		pm.registerEvents(new StaffInventoryPlayers(), this);
		pm.registerEvents(new StaffItems(), this);
		pm.registerEvents(new StaffPlayers(), this);
		pm.registerEvents(new StaffPickupItem(), this);
		pm.registerEvents(new StaffPLChest(), this);
		pm.registerEvents(new StaffRandomTP(), this);
		pm.registerEvents(new StaffUtils(), this);
		pm.registerEvents(new StaffVanish(), this);

		pm.registerEvents(new Ping(), this);
	}

	private void clearAll() {
		StaffFly.clear();
		StaffVanish.clear();
		StaffItems.clear();
		StaffUtils.clear();
	}

	private void giveItemsBack() {
		for (Player staff : StaffUtils.getStaff().keySet()) {
			staff.getInventory().clear();
			staff.getInventory().setContents(StaffUtils.getStaff().get(staff));
			Vanisher.unvanish(staff);
			Flyer.removeFly(staff);
			staff.sendMessage(StaffUtils.format("&7(&6Vanish &7and &6Fly &cdisabled&7)"));
			return;
		}

		for (Player staff : StaffUtils.getStaffArmor().keySet())
			staff.getInventory().setArmorContents(StaffUtils.getStaffArmor().get(staff));
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
		getCommand("unstaff").setExecutor(cmd);
	}
}