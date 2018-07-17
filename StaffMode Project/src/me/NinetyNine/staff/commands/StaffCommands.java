package me.NinetyNine.staff.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(StaffUtils.format("&cYou cannot execute this command!"));
			return true;
		} else {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("staff")) {
				if (args.length == 0) {
					if (player.hasPermission("staffmode.toggle")) {
						StaffUtils.toggleStaff(player);
						return true;
					} else {
						player.sendMessage(StaffUtils.format("&cYou do not have permissions to execute this command!"));
						return true;
					}
				} else {
					player.sendMessage(StaffUtils.format("&cInvalid command."));
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("quitgmsp")) {
				if (args.length == 0) {
					if (player.hasPermission(StaffConfig.getString("gmchangerpermgm0"))) {
						if (player.getGameMode() != GameMode.SURVIVAL) {
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(StaffUtils.format("&9Changed gamemode to SURVIVAL"));
							return true;
						} else {
							player.sendMessage(StaffUtils.format("&cYou are already in SURVIVAL!"));
							return true;
						}
					} else {
						player.sendMessage(StaffUtils.format("&cYou do not have permissions to execute this command!"));
						return true;
					}
				} else {
					player.sendMessage(StaffUtils.format("&cInvalid command."));
					return true;
				}
			}
		}
		return false;
	}
}