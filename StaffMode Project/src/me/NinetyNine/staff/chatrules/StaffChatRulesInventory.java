package me.NinetyNine.staff.chatrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.NinetyNine.staff.chatrules.utils.DetailUtils;
import me.NinetyNine.staff.chatrules.utils.ExampleType;
import me.NinetyNine.staff.chatrules.utils.ExampleUtils;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffChatRulesInventory implements Listener {

	@Getter
	private static Inventory chatrule = Bukkit.createInventory(null, 36, ChatColor.DARK_BLUE + "Chat Rules");

	@Getter
	private static Inventory flood = Bukkit.createInventory(null, 9, ChatColor.RED + "Flood");

	@Getter
	private static Inventory spam = Bukkit.createInventory(null, 9, ChatColor.RED + "Spam");

	@Getter
	private static Inventory caps = Bukkit.createInventory(null, 9, ChatColor.RED + "Caps");

	@Getter
	private static Inventory hackusating = Bukkit.createInventory(null, 9, ChatColor.RED + "Hackusating");

	@Getter
	private static Inventory arguing = Bukkit.createInventory(null, 9, ChatColor.RED + "Arguing");

	@Getter
	private static Inventory chatTrolling = Bukkit.createInventory(null, 9, ChatColor.RED + "Chat Trolling");

	@Getter
	private static Inventory askStaff = Bukkit.createInventory(null, 9, ChatColor.RED + "Asking Staff for Items");

	@Getter
	private static Inventory adv = Bukkit.createInventory(null, 9, ChatColor.RED + "Advertisement");

	@Getter
	private static Inventory swearing = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing");

	@Getter
	private static Inventory swearing2 = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing (Sev. 2)");

	@Getter
	private static Inventory swearing3 = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing (Sev. 3)");

	@Getter
	private static Inventory serverdis = Bukkit.createInventory(null, 9, ChatColor.RED + "Server Disrespect");

	@Getter
	private static Inventory staffdis = Bukkit.createInventory(null, 9, ChatColor.RED + "Staff Disrespect");

	@Getter
	private static Inventory bypassChat = Bukkit.createInventory(null, 9, ChatColor.RED + "Bypassing Chat Filter");

	@Getter
	private static Inventory ddos = Bukkit.createInventory(null, 9, ChatColor.RED + "DDoS Threat");

	@Getter
	private static Inventory hack = Bukkit.createInventory(null, 9, ChatColor.RED + "Hack Threat");

	@Getter
	private static Inventory inappb = Bukkit.createInventory(null, 9, ChatColor.RED + "Inappropriate Behavior");

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		List<Inventory> invs = new ArrayList<Inventory>(
				Arrays.asList(getChatrule(), getAdv(), getFlood(), getArguing(), getAskStaff(), getBypassChat(),
						getCaps(), getChatTrolling(), getDdos(), getHack(), getHackusating(), getInappb()));
		for (Inventory inv : invs) {
			if (!(e.getInventory().getType().equals(inv.getType())))
				return;
			break;
		}
		if (e.getCurrentItem() == null)
			return;

		Player player = (Player) e.getWhoClicked();

		ItemStack item = e.getCurrentItem();

		if (e.getInventory().getTitle().equals(ChatColor.DARK_BLUE + "Chat Rules")) {
			e.setCancelled(true);
			String displayName = item.getItemMeta().getDisplayName();

			if (displayName.equals(ChatColor.RED + "Flood")) {
				StaffItems.createItem(flood, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(flood, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, flood);
			}

			if (displayName.equals(ChatColor.RED + "Spam")) {
				StaffItems.createItem(spam, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(spam, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, spam);
			}
			if (displayName.equals(ChatColor.RED + "Caps")) {
				StaffItems.createItem(caps, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(caps, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, caps);
			}
			if (displayName.equals(ChatColor.RED + "Hackusating")) {
				System.out.println("is hackusating name");
				StaffItems.createItem(hackusating, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(hackusating, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, hackusating);
			}
			if (displayName.equals(ChatColor.RED + "Arguing")) {
				StaffItems.createItem(arguing, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(arguing, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, arguing);
			}
			if (displayName.equals(ChatColor.RED + "ChatTrolling")) {
				StaffItems.createItem(chatTrolling, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(chatTrolling, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, chatTrolling);
			}
			if (displayName.equals(ChatColor.RED + "Asking Staff for Items")) {
				StaffItems.createItem(askStaff, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(askStaff, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, askStaff);
			}
			if (displayName.equals(ChatColor.RED + "Advertisement")) {
				StaffItems.createItem(adv, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(adv, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, adv);
			}
			if (displayName.equals(ChatColor.RED + "Swearing")) {
				StaffItems.createItemWithColor(swearing, Material.WOOL, 1, ChatColor.RED + "Swearing (Sev. 1)", 14);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 4, ChatColor.RED + "Swearing (Sev. 2)", 12);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 7, ChatColor.RED + "Swearing (Sev. 3)", 10);
				StaffItems.createItem(swearing2, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(swearing2, 6, Material.BOOK, ChatColor.RED + "Example", null);
				StaffItems.createItem(swearing3, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(swearing3, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, swearing);
			}
			if (displayName.equals(ChatColor.RED + "Server Disrespect")) {
				StaffItems.createItem(serverdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(serverdis, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, serverdis);
			}
			if (displayName.equals(ChatColor.RED + "Staff Disrespect")) {
				StaffItems.createItem(staffdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(staffdis, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, staffdis);
			}
			if (displayName.equals(ChatColor.RED + "Bypassing Chat Filter")) {
				StaffItems.createItem(bypassChat, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(bypassChat, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, bypassChat);
			}
			if (displayName.equals(ChatColor.RED + "DDoS Threat")) {
				StaffItems.createItem(ddos, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(ddos, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, ddos);
			}
			if (displayName.equals(ChatColor.RED + "Hack Threat")) {
				StaffItems.createItem(hack, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(hack, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, hack);
			}
			if (displayName.equals(ChatColor.RED + "Inappropriate Behavior")) {
				StaffItems.createItem(inappb, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(inappb, 6, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, inappb);
			}
		}

		if (item.getType() == Material.ARROW) {
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				e.setCancelled(true);
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.FLOOD);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.SPAM);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.ADVERTISEMENT);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.ARGUING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.ASKING_STAFF_FOR_ITEMS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.SERVERDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.INAPPB);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypassing Chat Filter")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.BYPASSING_FILTER);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.CHAT_TROLLING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.THREATHACK);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.DDOS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.STAFFDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.CAPS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.SWEARING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 2)")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.SWEARING_2);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 3)")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, ExampleType.SWEARING_3);
				return;
			}
		}

		if (item.getType() == Material.BOOK) {
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				e.setCancelled(true);
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.FLOOD);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.SPAM);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.ADVERTISEMENT);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.ARGUING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.ASKING_STAFF_FOR_ITEMS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.SERVERDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.INAPPB);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypass Chat Filter")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.BYPASSING_FILTER);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.CHAT_TROLLING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.DDOS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.STAFFDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.CAPS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.SWEARING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 2)")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.SWEARING_2);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 3)")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.SWEARING_3);
				return;
			}
		}

		if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing")) {
			e.setCancelled(true);
			if (item.getType() == Material.WOOL) {
				switch (item.getData().getData()) {
				default:
					break;
				case 14:
					openInventory(player, swearing);
					break;
				case 12:
					openInventory(player, swearing2);
					break;
				case 10:
					openInventory(player, swearing3);
					break;
				}
			}
		}
	}

	private void openInventory(Player player, Inventory inventory) {
		player.openInventory(inventory);
	}
}