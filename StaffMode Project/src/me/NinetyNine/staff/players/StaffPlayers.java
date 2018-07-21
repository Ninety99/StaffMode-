package me.NinetyNine.staff.players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import lombok.Getter;
import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPlayers implements Listener {

	@Getter
	private static Inventory realInventory = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
	@Getter
	private static Inventory realInventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		if (e.getItem() == null)
			return;

		if (e.getItem().getType() == Material.AIR)
			return;

		if (e.getItem().getType() != Material.BEACON)
			return;

		if (!(e.getItem().getItemMeta().getDisplayName() != ChatColor.RED + "Players " + ChatColor.GRAY
				+ "(Right Click)"))
			return;

		Inventory inventory = null;
		Inventory inventory2 = null;
		Inventory inventory3 = null;

		int size = Bukkit.getServer().getOnlinePlayers().size();

		if (size > 9)
			inventory = Bukkit.createInventory(null, size, ChatColor.BLUE + "Players");
		else if (size == 8)
			inventory = Bukkit.createInventory(null, size + 1, ChatColor.BLUE + "Players");
		else if (size == 7)
			inventory = Bukkit.createInventory(null, size + 2, ChatColor.BLUE + "Players");
		else if (size == 6)
			inventory = Bukkit.createInventory(null, size + 3, ChatColor.BLUE + "Players");
		else if (size == 5)
			inventory = Bukkit.createInventory(null, size + 4, ChatColor.BLUE + "Players");
		else if (size == 4)
			inventory = Bukkit.createInventory(null, size + 5, ChatColor.BLUE + "Players");
		else if (size == 3)
			inventory = Bukkit.createInventory(null, size + 6, ChatColor.BLUE + "Players");
		else if (size == 2)
			inventory = Bukkit.createInventory(null, size + 7, ChatColor.BLUE + "Players");
		else if (size == 1)
			inventory = Bukkit.createInventory(null, size + 8, ChatColor.BLUE + "Players");

		/*
		 * Not completed yet
		 */

		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
			skullmeta.setOwner(all.getName());
			skullmeta.setDisplayName(all.getName());
			List<String> lore = new ArrayList<String>();

//			StaffBMInfoInterface bminfo = new StaffBMInfoHook();

			lore.add(ChatColor.AQUA + "BMInfo:");
			lore.add(" ");
			lore.add(ChatColor.RED + "Bans: ");
//			+ bminfo.getBans(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Mutes ");
//			+ bminfo.getMutes(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Kicks ");
//			+ bminfo.getKicks(e.getPlayer(), all.getPlayer()));
			lore.add(ChatColor.RED + "Warns ");
//			+ bminfo.getWarns(e.getPlayer(), all.getPlayer()));
			skullmeta.setLore(lore);
			skull.setItemMeta(skullmeta);

			for (int i = 0; i < inventory.getSize(); i++) {
				if (inventory.getContents().length > 52)
					inventory.setItem(i, skull);
				else if (inventory.getContents().length > 52) {
					inventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
					inventory2.setItem(i, skull);
				} else if (inventory2.getContents().length > 52) {
					inventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
					inventory3.setItem(i, skull);
				}
			}
		}

		getRealInventory().setContents(inventory.getContents());

		if (getRealInventory().getContents().length > 52) {
			StaffItems.createItem(realInventory, 53, new ItemStack(Material.BARRIER), ChatColor.GREEN + "Next",
					null);
		} else if (getRealInventory().getContents().length > 52) {
			inventory2 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
			getRealInventory2().setContents(inventory2.getContents());
			StaffItems.createItem(getRealInventory2(), 53, new ItemStack(Material.BARRIER), ChatColor.GREEN + "Next",
					null);
			StaffItems.createItem(getRealInventory2(), 45, new ItemStack(Material.BARRIER), ChatColor.RED + "Previous",
					null);
		}

		if (getRealInventory2().getContents().length > 52) {
			realInventory3 = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Players");
			getRealInventory3().setContents(inventory3.getContents());
			StaffItems.createItem(getRealInventory3(), 45, new ItemStack(Material.BARRIER), ChatColor.RED + "Previous",
					null);
		}

		if (getRealInventory2() == null && getRealInventory3() == null)
			e.getPlayer().openInventory(getRealInventory());
		else if (getRealInventory3() == null)
			e.getPlayer().openInventory(getRealInventory2());
		else if (getRealInventory3() != null)
			e.getPlayer().openInventory(getRealInventory3());
	}
}