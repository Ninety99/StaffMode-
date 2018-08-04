package me.NinetyNine.staff.chatrules;

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
import me.NinetyNine.staff.chatrules.utils.ExampleType;
import me.NinetyNine.staff.chatrules.utils.ExampleUtils;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffChatRulesInventory implements Listener {

	@Getter
	private static Inventory chatrule = Bukkit.createInventory(null, 27, ChatColor.DARK_BLUE + "Chat Rules");

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
	private static Inventory swearing2 = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing");

	@Getter
	private static Inventory swearing3 = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing");

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

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(StaffUtils.isInStaffMode((Player) e.getWhoClicked())))
			return;

		if (e.getCurrentItem() == null)
			return;

		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);

		ItemStack item = e.getCurrentItem();

		if (!item.hasItemMeta())
			return;

		if (e.getInventory().equals(chatrule)) {
			String displayName = item.getItemMeta().getDisplayName();

			if (displayName.equals(ChatColor.RED + "Flood")) {
				StaffItems.createItem(flood, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(flood, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, flood);
			}
			if (displayName.equals(ChatColor.RED + "Spam")) {
				StaffItems.createItem(spam, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(spam, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, spam);
			}
			if (displayName.equals(ChatColor.RED + "Caps")) {
				StaffItems.createItem(caps, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(caps, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, caps);
			}
			if (displayName.equals(ChatColor.RED + "Hackusating")) {
				StaffItems.createItem(hackusating, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(hackusating, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, hackusating);
			}
			if (displayName.equals(ChatColor.RED + "Arguing")) {
				StaffItems.createItem(arguing, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(arguing, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, arguing);
			}
			if (displayName.equals(ChatColor.RED + "ChatTrolling")) {
				StaffItems.createItem(chatTrolling, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(chatTrolling, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, chatTrolling);
			}
			if (displayName.equals(ChatColor.RED + "Asking Staff for Items")) {
				StaffItems.createItem(askStaff, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(askStaff, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, askStaff);
			}
			if (displayName.equals(ChatColor.RED + "Advertisement")) {
				StaffItems.createItem(adv, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(adv, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, adv);
			}
			if (displayName.equals(ChatColor.RED + "Swearing")) {
				StaffItems.createItemWithColor(swearing, Material.WOOL, 2, ChatColor.RED + "Swearing (Sev. 1)", 14);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 4, ChatColor.RED + "Swearing (Sev. 2)", 12);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 6, ChatColor.RED + "Swearing (Sev. 3)", 10);
				openInventory(player, swearing);
			}
			if (displayName.equals(ChatColor.RED + "Server Disrespect")) {
				StaffItems.createItem(serverdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(serverdis, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, serverdis);
			}
			if (displayName.equals(ChatColor.RED + "Staff Disrespect")) {
				StaffItems.createItem(staffdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(staffdis, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, staffdis);
			}
			if (displayName.equals(ChatColor.RED + "Bypassing Chat Filter")) {
				StaffItems.createItem(bypassChat, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(bypassChat, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, bypassChat);
			}
			if (displayName.equals(ChatColor.RED + "DDoS Threat")) {
				StaffItems.createItem(ddos, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(ddos, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, ddos);
			}
			if (displayName.equals(ChatColor.RED + "Hack Threat")) {
				StaffItems.createItem(hack, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(hack, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, hack);
			}
			if (displayName.equals(ChatColor.RED + "Inappropriate Behavior")) {
				StaffItems.createItem(inappb, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(inappb, 5, Material.BOOK, ChatColor.RED + "Example", null);
				openInventory(player, inappb);
			}

		} else
			return;

		if (item.getType() == Material.ARROW) {
			if (e.getInventory().equals(flood)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.FLOOD);
				return;
			} else if (e.getInventory().equals(spam)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SPAM);
				return;
			} else if (e.getInventory().equals(adv)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ADV);
				return;
			} else if (e.getInventory().equals(arguing)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ARGUE);
				return;
			} else if (e.getInventory().equals(askStaff)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ASKSTAFF);
				return;
			} else if (e.getInventory().equals(serverdis)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SERVER);
				return;
			} else if (e.getInventory().equals(inappb)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.INAPP);
				return;
			} else if (e.getInventory().equals(ddos)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.DDOS);
				return;
			} else if (e.getInventory().equals(bypassChat)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.BYPASS);
				return;
			} else if (e.getInventory().equals(chatTrolling)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.CHATT);
				return;
			} else if (e.getInventory().equals(hack)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.HACK);
				return;
			} else if (e.getInventory().equals(staffdis)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.STAFF);
				return;
			} else if (e.getInventory().equals(hackusating)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ACCUSE);
				return;
			} else if (e.getInventory().equals(caps)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.CAPS);
				return;
			} else if (e.getInventory().equals(swearing)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR);
				return;
			} else if (e.getInventory().equals(swearing2)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR2);
				return;
			} else if (e.getInventory().equals(swearing3)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR3);
				return;
			}
		}

		if (item.getType() == Material.BOOK) {
			if (e.getInventory().equals(flood)) {
				player.closeInventory();
				ExampleUtils.sendExample(player, ExampleType.FLOOD);
				return;
			} else if (e.getInventory().equals(spam)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SPAM);
				return;
			} else if (e.getInventory().equals(adv)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ADV);
				return;
			} else if (e.getInventory().equals(arguing)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ARGUE);
				return;
			} else if (e.getInventory().equals(askStaff)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ASKSTAFF);
				return;
			} else if (e.getInventory().equals(serverdis)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SERVER);
				return;
			} else if (e.getInventory().equals(inappb)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.INAPP);
				return;
			} else if (e.getInventory().equals(ddos)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.DDOS);
				return;
			} else if (e.getInventory().equals(bypassChat)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.BYPASS);
				return;
			} else if (e.getInventory().equals(chatTrolling)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.CHATT);
				return;
			} else if (e.getInventory().equals(hack)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.HACK);
				return;
			} else if (e.getInventory().equals(staffdis)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.STAFF);
				return;
			} else if (e.getInventory().equals(hackusating)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.ACCUSE);
				return;
			} else if (e.getInventory().equals(caps)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.CAPS);
				return;
			} else if (e.getInventory().equals(swearing)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR);
				return;
			} else if (e.getInventory().equals(swearing2)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR2);
				return;
			} else if (e.getInventory().equals(swearing3)) {
				player.closeInventory();
				ExampleUtils.sendDetails(player, ExampleType.SWEAR3);
				return;
			}
		}

	}

	private void openInventory(Player player, Inventory inventory) {
		player.closeInventory();
		player.openInventory(inventory);
	}
}