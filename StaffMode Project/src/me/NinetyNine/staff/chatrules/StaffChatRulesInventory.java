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
import me.NinetyNine.staff.chatrules.utils.ExampleUtils;
import me.NinetyNine.staff.chatrules.utils.RuleType;
import me.NinetyNine.staff.utils.StaffConfig;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffChatRulesInventory implements Listener {

	@Getter
	private static Inventory chatrule = Bukkit.createInventory(null, 45, ChatColor.DARK_BLUE + "Chat Rules");

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
	private static Inventory swearing1 = Bukkit.createInventory(null, 9, ChatColor.RED + "Swearing (Sev. 1)");

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

	@Getter
	private static String path = "";

	@Getter
	private static List<Player> inEditMode = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;

		if (!(((Player) e.getWhoClicked()).hasPermission("staffmode.chatrules")))
			return;

		List<Inventory> invs = new ArrayList<Inventory>(
				Arrays.asList(getChatrule(), getAdv(), getFlood(), getArguing(), getAskStaff(), getBypassChat(),
						getCaps(), getChatTrolling(), getDdos(), getHack(), getHackusating(), getInappb()));
		for (Inventory inv : invs) {
			if (!(e.getInventory().getType().equals(inv.getType())))
				continue;
		}

		Player player = (Player) e.getWhoClicked();

		ItemStack item = e.getCurrentItem();

		if (e.getInventory().getTitle().equals(ChatColor.DARK_BLUE + "Chat Rules")) {
			e.setCancelled(true);
			String displayName = "";

			if (item.getItemMeta() == null)
				return;
			
			if (item.getItemMeta().getDisplayName() != null)
				displayName = item.getItemMeta().getDisplayName();
			else 
				displayName = " ";

			if (displayName.equals(ChatColor.RED + "Flood")) {
				addBack(flood);
				StaffItems.createItem(flood, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, flood);
				StaffItems.createItem(flood, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, flood);
				player.openInventory(flood);
			}

			if (displayName.equals(ChatColor.RED + "Spam")) {
				addBack(spam);
				StaffItems.createItem(spam, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, spam);
				StaffItems.createItem(spam, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, spam);
				player.openInventory(spam);
			}
			if (displayName.equals(ChatColor.RED + "Caps")) {
				addBack(caps);
				StaffItems.createItem(caps, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, caps);
				StaffItems.createItem(caps, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, caps);
				player.openInventory(caps);
			}
			if (displayName.equals(ChatColor.RED + "Hackusating")) {
				addBack(hackusating);
				StaffItems.createItem(hackusating, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, hackusating);
				StaffItems.createItem(hackusating, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, hackusating);
				player.openInventory(hackusating);
			}
			if (displayName.equals(ChatColor.RED + "Arguing")) {
				addBack(arguing);
				StaffItems.createItem(arguing, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, arguing);
				StaffItems.createItem(arguing, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, arguing);
				player.openInventory(arguing);
			}
			if (displayName.equals(ChatColor.RED + "ChatTrolling")) {
				addBack(chatTrolling);
				StaffItems.createItem(chatTrolling, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, chatTrolling);
				StaffItems.createItem(chatTrolling, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, chatTrolling);
				player.openInventory(chatTrolling);
			}
			if (displayName.equals(ChatColor.RED + "Asking Staff for Items")) {
				addBack(askStaff);
				StaffItems.createItem(askStaff, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, askStaff);
				StaffItems.createItem(askStaff, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, askStaff);
				player.openInventory(askStaff);
			}
			if (displayName.equals(ChatColor.RED + "Advertisement")) {
				addBack(adv);
				StaffItems.createItem(adv, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, adv);
				StaffItems.createItem(adv, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, adv);
				player.openInventory(adv);
			}
			if (displayName.equals(ChatColor.RED + "Swearing")) {
				addBack(swearing);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 1, ChatColor.RED + "Swearing (Sev. 1)", 14);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 4, ChatColor.RED + "Swearing (Sev. 2)", 12);
				StaffItems.createItemWithColor(swearing, Material.WOOL, 7, ChatColor.RED + "Swearing (Sev. 3)", 10);

				addBack(swearing1);
				StaffItems.createItem(swearing1, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, swearing1);
				StaffItems.createItem(swearing1, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, swearing1);

				addBack(swearing2);
				StaffItems.createItem(swearing2, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(swearing2, 6, Material.BOOK, ChatColor.RED + "Example", null);

				addBack(swearing3);
				StaffItems.createItem(swearing3, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				StaffItems.createItem(swearing3, 6, Material.BOOK, ChatColor.RED + "Example", null);

				player.openInventory(swearing);
			}
			if (displayName.equals(ChatColor.RED + "Server Disrespect")) {
				addBack(serverdis);
				StaffItems.createItem(serverdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, serverdis);
				StaffItems.createItem(serverdis, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, serverdis);
				player.openInventory(serverdis);
			}
			if (displayName.equals(ChatColor.RED + "Staff Disrespect")) {
				addBack(staffdis);
				StaffItems.createItem(staffdis, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, staffdis);
				StaffItems.createItem(staffdis, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, staffdis);
				player.openInventory(staffdis);
			}
			if (displayName.equals(ChatColor.RED + "Bypassing Chat Filter")) {
				addBack(bypassChat);
				StaffItems.createItem(bypassChat, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, bypassChat);
				StaffItems.createItem(bypassChat, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, bypassChat);
				player.openInventory(bypassChat);
			}
			if (displayName.equals(ChatColor.RED + "DDoS Threat")) {
				addBack(ddos);
				StaffItems.createItem(ddos, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, ddos);
				StaffItems.createItem(ddos, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, ddos);
				player.openInventory(ddos);
			}
			if (displayName.equals(ChatColor.RED + "Hack Threat")) {
				addBack(hack);
				StaffItems.createItem(hack, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, hack);
				StaffItems.createItem(hack, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, hack);
				player.openInventory(hack);
			}
			if (displayName.equals(ChatColor.RED + "Inappropriate Behavior")) {
				addBack(inappb);
				StaffItems.createItem(inappb, 2, Material.ARROW, ChatColor.GREEN + "Information", null);
				addEdit(player, inappb);
				StaffItems.createItem(inappb, 6, Material.BOOK, ChatColor.RED + "Example", null);
				addReset(player, inappb);
				player.openInventory(inappb);
			}
		}

		if (item.getType().equals(Material.ARROW)
				&& item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Information")) {
			e.setCancelled(true);
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.FLOOD);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.SPAM);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.ADVERTISEMENT);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.ARGUING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.ASKING_STAFF_FOR_ITEMS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.SERVERDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.INAPPB);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypassing Chat Filter")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.BYPASSING_FILTER);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.CHAT_TROLLING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.THREATHACK);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.DDOS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.STAFFDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.CAPS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 1)")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.SWEARING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 2)")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.SWEARING_2);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 3)")) {
				player.closeInventory();
				DetailUtils.sendDetails(player, RuleType.SWEARING_3);
				return;
			}
		}

		if (item.getType().equals(Material.BOOK)
				&& item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Example")) {
			e.setCancelled(true);
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.FLOOD);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.SPAM);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.ADVERTISEMENT);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.ARGUING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.ASKING_STAFF_FOR_ITEMS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.SERVERDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.INAPPB);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypass Chat Filter")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.BYPASSING_FILTER);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.CHAT_TROLLING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.DDOS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.STAFFDIS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.HACKUSATING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.CAPS);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 1)")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.SWEARING);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 2)")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.SWEARING_2);
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 3)")) {
				player.closeInventory();
				ExampleUtils.sendExample(player, RuleType.SWEARING_3);
				return;
			}
		}

		if (item.getType().equals(Material.PAPER)
				&& item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Edit Details")) {
			e.setCancelled(true);

			if (!(getInEditMode().contains(player)))
				getInEditMode().add(player);

			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				path = "flood";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				path = "spam";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				path = "advertisement";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				path = "arguing";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				path = "askingstaffforthings";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				path = "serverdis";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				path = "inappb";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypassing Chat Filter")) {
				path = "bypassingfilter";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				path = "chattrolling";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				path = "threathack";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				path = "ddos";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				path = "staffdis";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				path = "hackusating";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				path = "caps";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 1)")) {
				path = "swearing";
				player.closeInventory();
				player.sendMessage(
						StaffUtils.format("&7You are now in &bediting &7mode! &8(Editing: &b" + path + "&8)"));
				return;
			}
		}

		if (item.getType().equals(Material.BARRIER)
				&& item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Reset Details")) {
			e.setCancelled(true);
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Flood")) {
				player.closeInventory();
				StaffConfig.set("chatrules.flood",
						"&7Saying &n1&r&7 or more letters constantly and it can also be saying different characters that intended to flood the chat. This can result in a &l&42-4 hour mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bFlood"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Spam")) {
				player.closeInventory();
				StaffConfig.set("chatrules.spam",
						"&7Spamming &n3 sentences or phrases&r&7 / &nCountdown&4&7 spamming (3,2,1...) in the chat can result in a &l&42-4 hour mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bSpam"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Advertisement")) {
				player.closeInventory();
				StaffConfig.set("chatrules.advertisement",
						"&7Asking &nplayers to join a server &r&7via mentioning the servers name in &npublic &r&7conversation or by &nprivate &r&7messaging an &lIP&r&7. This can result in a &n&l&4PERMANENT &7ban!");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bSpam"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Arguing")) {
				player.closeInventory();
				StaffConfig.set("chatrules.arguing",
						"&7Someone &lconstantly contradicting what someone says &r&7/Someone &l&nprotesting a punishment that has been given to them&r&7 (Make an unban/unmute request at our forums!). This will result in a &l&412 hour mute&r&7!");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bArguing"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Asking Staff for Items")) {
				player.closeInventory();
				StaffConfig.set("chatrules.askingstaffforitems",
						"&7Asking a Staff member who is able to &nspawn in items and/or money for a player&r&7. This can result in a l42-4 hour mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bAsking Staff for Items"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Server Disrespect")) {
				player.closeInventory();
				StaffConfig.set("chatrules.serverdis",
						"&7Giving the &lserver a &nbad name&r&7. &r&7If you see someone doing/did this, &lplease report at our forums or on discord &nimmediately!&r&7 This can result in a &l&4MUTE &7or a &l&4BAN.");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bServer Disrespect"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Inappropriate Behavior")) {
				player.closeInventory();
				StaffConfig.set("chatrules.inappb",
						"&7Saying something in chat that has &lsexual or generally inappropriate content&r&7. This can result in a &l&48-12 hour mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bInappropriate Behavior"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Bypassing Chat Filter")) {
				player.closeInventory();
				StaffConfig.set("chatrules.bypassingfilter",
						"&7Finding a way to use words that are &lfiltered/Using other &nvulgar words&r&7 that are not filtered but &lSHOULD &r&7be. This includes swearing in /msg! This can result in a &l&4mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bBypassing Chat Filter"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Chat Trolling")) {
				player.closeInventory();
				StaffConfig.set("chatrules.chattrolling",
						"&n&7Intentionally&r &7saying things that are meant to &nconfuse &r&7or &nmislead players. &r&7| &nImpersonating a Staff member&r&7. This can result in a &c(Currently not known!)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bChat Trolling"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hack Threat")) {
				player.closeInventory();
				StaffConfig.set("chatrules.threathack",
						"&7Threatening a player/the server to be &lhacked. This &n&lWILL&4&7 result in a &l&4PERMANENT &lban.");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bHack Threat"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "DDoS Threat")) {
				player.closeInventory();
				StaffConfig.set("chatrules.ddos",
						"&7Threatening a player/the server to be shut down/to put down someones internet/the server. This &n&lWILL&7 result in a &l&4PERMANENT&lban.");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bDDoS Threat"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Staff Disrespect")) {
				player.closeInventory();
				StaffConfig.set("chatrules.staffdis",
						"&7Giving a &lStaff member/the Staff team a &nbad name&r&7. &r&7If you see someone doing/did this, &lplease report at our forums or on discord &nimmediately!&r&7 This can result in a &l&4MUTE &7or a &l&4BAN.");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bStaff Disrespect"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Hackusating")) {
				player.closeInventory();
				StaffConfig.set("chatrules.hackusating",
						"&7Accusing someone of &nhacking&r&7. Thus, will lower the chances of Staff to ban them. Players who accuse must use /helpop! This can result in a &l&46 hour mute!");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bHackusating"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Caps")) {
				player.closeInventory();
				StaffConfig.set("chatrules.caps",
						"&7Capitalizing &n&l4 or more words&r&7 / Capitalizing more than &n15 characters&r&7. This can result in a &l&41-2 hour mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bCaps"));
				return;
			} else if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing (Sev. 1)")) {
				player.closeInventory();
				StaffConfig.set("chatrules.swearing",
						"&7Cussing/Cursing/Swearing/being rude to a player with &nintent to hurt their &n&lfeelings&r&7. This can result in a &l&424 hours/36 hours/3 day mute! &c(Depends on how severe)");
				player.sendMessage(StaffUtils.format("&aSuccesfully reset: &bSwearing"));
				return;
			}
		}

		if (item.getType().equals(Material.ITEM_FRAME)
				&& item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Back")) {
			e.setCancelled(true);
			if (e.getInventory().getTitle().contains("Swearing (")) {
				player.openInventory(swearing);
			} else
				player.openInventory(chatrule);
		}

		if (item.getType().equals(Material.WOOL)) {
			if (e.getInventory().getTitle().equals(ChatColor.RED + "Swearing")) {
				e.setCancelled(true);
				switch (item.getData().getData()) {
				default:
					break;
				case 14:
					player.openInventory(swearing1);
					break;
				case 12:
					player.openInventory(swearing2);
					break;
				case 10:
					player.openInventory(swearing3);
					break;
				}
			}
		}
	}

	private void addEdit(Player player, Inventory inventory) {
		if (player.isOp())
			StaffItems.createItem(inventory, 4, Material.PAPER, ChatColor.GOLD + "Edit Details", null);
		else
			return;
	}

	private void addReset(Player player, Inventory inventory) {
		if (player.isOp())
			StaffItems.createItem(inventory, 8, Material.BARRIER, ChatColor.RED + "Reset Details", null);
		else
			return;
	}

	private void addBack(Inventory inventory) {
		StaffItems.createItem(inventory, 0, Material.ITEM_FRAME, ChatColor.GREEN + "Back", null);
	}
}