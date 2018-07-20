package me.NinetyNine.staff.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			if (cmd.getName().equalsIgnoreCase("staff")) {
				if (args.length == 0) {
					sender.sendMessage("The only command you can execute is staff chatclear!");
					return true;
				}

				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("chatclear")) {
						for (int i = 0; i < 100; i++) {
							for (Player all : Bukkit.getServer().getOnlinePlayers()) {
								if (!all.hasPermission(StaffConfig.getString("permchatclear")))
									all.sendMessage("\n");
							}
						}

						send(sender);
						
						for (Player all : Bukkit.getServer().getOnlinePlayers()) {
							if (all.hasPermission(StaffConfig.getString("permchatclear")))
								all.sendMessage(ChatColor.GREEN
										+ "You did not get the chat cleared because of your permissions!");
						}
						return true;
					} else {
						sender.sendMessage("Invalid command.");
						return true;
					}
				}
			}
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
				}

				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("quitgmsp")) {
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
							player.sendMessage(
									StaffUtils.format("&cYou do not have permissions to execute this command!"));
							return true;
						}
					}

					if (args[0].equalsIgnoreCase("chatclear")) {
						if (player.hasPermission(StaffConfig.getString("permchatclear"))) {
							for (int i = 0; i < 100; i++) {
								for (Player all : Bukkit.getServer().getOnlinePlayers()) {
									if (!all.hasPermission(StaffConfig.getString("permchatclear")))
										all.sendMessage("\n");
								}
							}

							send(player);
							return true;
						} else {
							player.sendMessage(
									StaffUtils.format("&cYou do not have permissions to execute this command!"));
							return true;
						}
					}

					if (!(args[0].equalsIgnoreCase("quitgmsp") || args[0].equalsIgnoreCase("chatclear"))) {
						player.sendMessage(StaffUtils.format("&cInvalid command."));
						return true;
					}
				}

				if (args.length > 1) {
					player.sendMessage(StaffUtils.format("&cInvalid command."));
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public void send(CommandSender sender) {
		if (sender instanceof Player) {
			if (!(sender.hasPermission(StaffConfig.getString("permchatclear")))) {
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "           [+]----===================----[+]\n"
						+ ChatColor.GOLD + "            Chat has been cleared by " + ChatColor.RED + sender.getName()
						+ ChatColor.GOLD + "!\n" + ChatColor.GRAY + "           [+]----===================----[+]\n");
				return;
			} else {
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "           [+]----===================----[+]\n"
						+ ChatColor.GOLD + "            Chat has been cleared by " + ChatColor.RED + sender.getName()
						+ ChatColor.GOLD + "!\n" + ChatColor.GRAY + "           [+]----===================----[+]\n"
						+ ChatColor.GREEN + "You did not get the chat cleared because of your permissions!");
				return;

			}
		} else {
			Bukkit.getServer()
					.broadcastMessage(ChatColor.GRAY + "           [+]----===================----[+]\n" + ChatColor.GOLD
							+ "            Chat has been cleared by " + ChatColor.RED + "CONSOLE" + ChatColor.GOLD
							+ "!\n" + ChatColor.GRAY + "           [+]----===================----[+]\n");
			return;
		}
	}
}