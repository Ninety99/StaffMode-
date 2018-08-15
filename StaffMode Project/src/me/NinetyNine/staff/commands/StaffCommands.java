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

import me.NinetyNine.staff.chatrules.StaffChatRulesInventory;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("staff")) {
				if (args.length == 0) {
					sender.sendMessage(
							"The only /staff command you can execute is /staff chatclear and /unstaff <player>!");
					return true;
				}

				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("chatclear")) {
						for (Player all : StaffUtils.getOnlinePlayers()) {
							for (int i = 0; i < 100; i++) {
								if (!all.hasPermission("staffmode.chatclear"))
									all.sendMessage("\n");
							}
						}

						send(sender);

						for (Player all : StaffUtils.getOnlinePlayers()) {
							if (all.hasPermission("staffmode.chatclear"))
								all.sendMessage(ChatColor.GREEN
										+ "You did not get your chat cleared because of your permissions!");
						}
						return true;
					}

					if (args[0].equalsIgnoreCase("chatrules")) {
						sender.sendMessage("You must be a player to execute this command!");
						return true;
					}

					if (args[0].equalsIgnoreCase("worldlist")) {
						sender.sendMessage(ChatColor.RED + "Annihilation Worlds:");
						sender.sendMessage(ChatColor.GRAY
								+ StaffConfig.getStringList("anniWorlds").toString().replace("[", "").replace("]", ""));
						return true;
					}
				}

				if (args.length > 1) {
					sender.sendMessage("Invalid command.");
					return true;
				}

				if (!(args[0].equalsIgnoreCase("chatclear") || args[0].equalsIgnoreCase("chatrules")
						|| args[0].equalsIgnoreCase("worldlist"))) {
					sender.sendMessage(ChatColor.RED + "Invalid command.");
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("unstaff")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /unstaff <player>");
					return true;
				}

				if (args.length > 1) {
					sender.sendMessage("Invalid command.");
					return true;
				}

				@SuppressWarnings("deprecation")
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null)
					sender.sendMessage(ChatColor.RED + "Player cannot be found!");
				else {
					if (StaffUtils.isInStaffMode(target)) {
						StaffUtils.toggleStaff(target);
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + target.getName() + " is not in Staff mode!");
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
						if (player.hasPermission("staffmode.gm0")) {
							if (player.getGameMode() != GameMode.SURVIVAL
									&& player.getGameMode() == GameMode.SPECTATOR) {
								player.setGameMode(GameMode.SURVIVAL);
								player.sendMessage(StaffUtils.format("&9Changed gamemode to SURVIVAL"));
								return true;
							} else {
								player.sendMessage(StaffUtils
										.format("&cYou are already in SURVIVAL || You are not on &bSPECTATOR &cmode!"));
								return true;
							}
						} else {
							player.sendMessage(
									StaffUtils.format("&cYou do not have permissions to execute this command!"));
							return true;
						}
					}

					if (args[0].equalsIgnoreCase("chatclear")) {
						if (player.hasPermission("staffmode.chatclear")) {
							for (int i = 0; i < 100; i++) {
								for (Player all : Bukkit.getServer().getOnlinePlayers()) {
									if (!all.hasPermission("staffmode.chatclear"))
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
						if (player.hasPermission("staffmode.chatrules")) {
							addRules(StaffChatRulesInventory.getChatrule());
							player.openInventory(StaffChatRulesInventory.getChatrule());
							player.sendMessage(StaffUtils.format("&9Opening chat rule inventory.."));
							return true;
						} else {
							player.sendMessage(
									StaffUtils.format("&cYou do not have enough permissions to execute this command."));
							return true;
						}
					}

					if (args[0].equalsIgnoreCase("addworld")) {
						if (player.hasPermission("staffmode.addworld")) {
							if (!StaffConfig.getStringList("anniWorlds").contains(player.getWorld().getName())) {
								StaffConfig.addStringToList(player.getWorld().getName());
								player.sendMessage(StaffUtils.format("&aSuccesfully added &c"
										+ player.getWorld().getName() + " &ato the anni world list!"));
								return true;
							} else {
								player.sendMessage(StaffUtils.format(
										"&c" + player.getWorld().getName() + " &7is already in the anni world list!"));
								return true;
							}
						} else {
							player.sendMessage(
									StaffUtils.format("&cYou do not have permissions to use this commnand."));
							return true;
						}
					}

					if (args[0].equalsIgnoreCase("removeworld")) {
						if (player.hasPermission("staffmode.removeworld")) {
							if (!StaffConfig.getStringList("anniWorlds").contains(player.getWorld().getName())) {
								player.sendMessage(StaffUtils.format(
										"&c" + player.getWorld().getName() + " &7is not on the anni world list!"));
								return true;
							} else {
								StaffConfig.removeStringFromList(player.getWorld().getName());
								player.sendMessage(StaffUtils.format("&aSuccesfully removed &c"
										+ player.getWorld().getName() + " &ato the anni world list."));
								return true;
							}
						}
					}

					if (args[0].equalsIgnoreCase("worldlist")) {
						if (player.hasPermission("staffmode.worldlist")) {
							player.sendMessage(ChatColor.RED + "Annihilation Worlds:\n");

							player.sendMessage(ChatColor.GRAY + StaffConfig.getStringList("anniWorlds").toString()
									.replace("[", "").replace("]", ""));
							return true;
						}
					}

					if (!(args[0].equalsIgnoreCase("quitgmsp") || args[0].equalsIgnoreCase("chatclear")
							|| args[0].equalsIgnoreCase("chatrules") || args[0].equalsIgnoreCase("addworld")
							|| args[0].equalsIgnoreCase("removeworld") || args[0].equalsIgnoreCase("worldlist"))) {
						player.sendMessage(StaffUtils.format("&cInvalid command."));
						return true;
					}
				}

				if (args.length > 1) {
					player.sendMessage(StaffUtils.format("&cInvalid command."));
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("unstaff")) {
				if (!player.hasPermission("staffmode.unstaff")) {
					player.sendMessage(StaffUtils.format("&cYou cannot execute this command!"));
					return true;
				}

				if (args.length == 0) {
					player.sendMessage(StaffUtils.format("&cUsage: /unstaff <player>"));
					return true;
				}

				if (args.length > 1) {
					player.sendMessage(StaffUtils.format("&cInvalid command."));
					return true;
				}

				@SuppressWarnings("deprecation")
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null)
					player.sendMessage(StaffUtils.format("&cPlayer cannot be found!"));
				else {
					if (StaffUtils.isInStaffMode(target)) {
						StaffUtils.toggleStaff(target);
						return true;
					} else {
						sender.sendMessage(StaffUtils.format("&c" + target.getName() + " is not in Staff mode!"));
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
			if (!(sender.hasPermission("staffmode.chatclear"))) {
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
		StaffItems.createGlassWithColor(inventory, 1, " ", (short) 0);
		StaffItems.createItem(inventory, 2, Material.PAPER, ChatColor.RED + "Spam", null);
		StaffItems.createGlassWithColor(inventory, 3, " ", (short) 8);
		StaffItems.createItem(inventory, 4, Material.PAPER, ChatColor.RED + "Caps", null);
		StaffItems.createGlassWithColor(inventory, 5, " ", (short) 7);
		StaffItems.createItem(inventory, 6, Material.PAPER, ChatColor.RED + "Hackusating", null);
		StaffItems.createGlassWithColor(inventory, 7, " ", (short) 15);
		StaffItems.createItem(inventory, 8, Material.PAPER, ChatColor.RED + "Arguing", null);
		StaffItems.createGlassWithColor(inventory, 9, " ", (short) 15);
		StaffItems.createItem(inventory, 10, Material.PAPER, ChatColor.RED + "ChatTrolling", null);
		StaffItems.createGlassWithColor(inventory, 11, " ", (short) 7);
		StaffItems.createItem(inventory, 12, Material.PAPER, ChatColor.RED + "Asking Staff for Items", null);
		StaffItems.createGlassWithColor(inventory, 13, " ", (short) 8);
		StaffItems.createItem(inventory, 14, Material.PAPER, ChatColor.RED + "Advertisement", null);
		StaffItems.createGlassWithColor(inventory, 15, " ", (short) 0);
		StaffItems.createItem(inventory, 16, Material.PAPER, ChatColor.RED + "Swearing", null);
		StaffItems.createGlassWithColor(inventory, 17, " ", (short) 0);
		StaffItems.createItem(inventory, 18, Material.PAPER, ChatColor.RED + "Server Disrespect", null);
		StaffItems.createGlassWithColor(inventory, 19, " ", (short) 8);
		StaffItems.createItem(inventory, 20, Material.PAPER, ChatColor.RED + "Staff Disrespect", null);
		StaffItems.createGlassWithColor(inventory, 21, " ", (short) 7);
		StaffItems.createItem(inventory, 22, Material.PAPER, ChatColor.RED + "Bypassing Chat Filter", null);
		StaffItems.createGlassWithColor(inventory, 23, " ", (short) 15);
		StaffItems.createItem(inventory, 24, Material.PAPER, ChatColor.RED + "DDoS Threat", null);
		StaffItems.createGlassWithColor(inventory, 25, " ", (short) 3);
		StaffItems.createItem(inventory, 26, Material.PAPER, ChatColor.RED + "Hack Threat", null);
		StaffItems.createGlassWithColor(inventory, 27, " ", (short) 0);
		StaffItems.createGlassWithColor(inventory, 28, " ", (short) 8);
		StaffItems.createGlassWithColor(inventory, 29, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 30, " ", (short) 15);
		StaffItems.createItem(inventory, 31, Material.PAPER, ChatColor.RED + "Inappropriate Behavior", null);
		StaffItems.createGlassWithColor(inventory, 32, " ", (short) 15);
		StaffItems.createGlassWithColor(inventory, 33, " ", (short) 7);
		StaffItems.createGlassWithColor(inventory, 34, " ", (short) 8);
		StaffItems.createGlassWithColor(inventory, 35, " ", (short) 0);
	}
}