package me.NinetyNine.staff.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import lombok.Getter;
import me.NinetyNine.staff.chatrules.StaffChatRulesInventory;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffCommands implements Listener, CommandExecutor {

	@Getter
	private static String path = "";

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
					}

					if (args[0].equalsIgnoreCase("chatrules")) {
						sender.sendMessage("You must be a player to execute this command!");
						return true;
					}
				}

				if (!(args[0].equalsIgnoreCase("chatclear") || args[0].equalsIgnoreCase("chatrules"))) {
					sender.sendMessage(ChatColor.RED + "Invalid command.");
					return true;
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

					if (args[0].equalsIgnoreCase("chatrules")) {
						addRules(StaffChatRulesInventory.getChatrule());
						player.openInventory(StaffChatRulesInventory.getChatrule());
						player.sendMessage(StaffUtils.format("&9Opening chat rule inventory.."));
						return true;
					}

					if (!(args[0].equalsIgnoreCase("quitgmsp") || args[0].equalsIgnoreCase("chatclear"))) {
						player.sendMessage(StaffUtils.format("&cInvalid command."));
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}

	public void send(CommandSender sender) {
		if (sender instanceof Player) {
			if (!(sender.hasPermission(StaffConfig.getString("permchatclear")))) {
				Bukkit.getServer()
						.broadcastMessage(ChatColor.GRAY + "           -=[+]----===================----[+]=-\n"
								+ ChatColor.GOLD + "            Chat has been cleared by " + ChatColor.RED
								+ sender.getName() + ChatColor.GOLD + "!\n" + ChatColor.GRAY
								+ "           -=[+]----===================----[+]=-\n");
				return;
			} else {
				Bukkit.getServer()
						.broadcastMessage(ChatColor.GRAY + "           -=[+]----===================----[+]=-\n"
								+ ChatColor.GOLD + "            Chat has been cleared by " + ChatColor.RED
								+ sender.getName() + ChatColor.GOLD + "!\n" + ChatColor.GRAY
								+ "           -=[+]----===================----[+]=-\n" + ChatColor.GREEN
								+ "You did not get the chat cleared because of your permissions!");
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

	private void addRules(Inventory inventory) {
		StaffItems.createItem(inventory, 0, Material.PAPER, ChatColor.RED + "Flood", null);
		StaffItems.createItem(inventory, 1, Material.PAPER, ChatColor.RED + "Spam", null);
		StaffItems.createItem(inventory, 2, Material.PAPER, ChatColor.RED + "Caps", null);
		StaffItems.createItem(inventory, 3, Material.PAPER, ChatColor.RED + "Hackusating", null);
		StaffItems.createItem(inventory, 4, Material.PAPER, ChatColor.RED + "Arguing", null);
		StaffItems.createItem(inventory, 5, Material.PAPER, ChatColor.RED + "ChatTrolling", null);
		StaffItems.createItem(inventory, 6, Material.PAPER, ChatColor.RED + "Asking Staff for Items", null);
		StaffItems.createItem(inventory, 7, Material.PAPER, ChatColor.RED + "Advertisement", null);
		StaffItems.createItem(inventory, 8, Material.PAPER, ChatColor.RED + "Swearing", null);
		StaffItems.createItem(inventory, 9, Material.PAPER, ChatColor.RED + "Server Disrespect", null);
		StaffItems.createItem(inventory, 10, Material.PAPER, ChatColor.RED + "Staff Disrespect", null);
		StaffItems.createItem(inventory, 11, Material.PAPER, ChatColor.RED + "Bypassing Chat Filter", null);
		StaffItems.createItem(inventory, 12, Material.PAPER, ChatColor.RED + "DDoS Threat", null);
		StaffItems.createItem(inventory, 13, Material.PAPER, ChatColor.RED + "Hack Threat", null);
		StaffItems.createItem(inventory, 14, Material.PAPER, ChatColor.RED + "Inappropriate Behavior", null);
	}
}