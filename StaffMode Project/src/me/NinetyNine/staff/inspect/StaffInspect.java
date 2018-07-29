package me.NinetyNine.staff.inspect;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;;

public class StaffInspect implements Listener {

	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player))
			return;

		Player player = e.getPlayer();

		if (!StaffUtils.isInStaffMode(player))
			return;

		if (player.getItemInHand() == null)
			return;

		if (player.getItemInHand().getType() == Material.AIR)
			return;

		if (!(player.getItemInHand().getType() == Material.BOOK))
			return;

		if (player.getItemInHand().getItemMeta().getDisplayName() == ChatColor.GREEN + "Inspect")
			return;

		Player clicked = (Player) e.getRightClicked();

		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize() + 9,
				clicked.getName() + "'s inventory");

		for (ItemStack item : clicked.getInventory().getContents()) {
			for (int i = 0; i < clickedInventory.getSize(); i++)
				clickedInventory.setItem(i, item);
		}

		clickedInventory.setItem(39, clicked.getInventory().getHelmet());
		clickedInventory.setItem(40, clicked.getInventory().getChestplate());
		clickedInventory.setItem(41, clicked.getInventory().getLeggings());
		clickedInventory.setItem(42, clicked.getInventory().getBoots());

		StaffItems.createItem(clickedInventory, 44, new ItemStack(Material.SPECKLED_MELON),
				ChatColor.GRAY + "Player Info", Arrays.asList(
						ChatColor.RED + "Health: " + clicked.getHealth() + "Food Level: " + clicked.getFoodLevel()));
		StaffItems.createItemWithBMInfo(clicked, clickedInventory, 46, new ItemStack(Material.EMERALD),
				ChatColor.RED + "BMInfo Status");

		player.openInventory(clickedInventory);
		player.sendMessage(StaffUtils.format("&9Opening " + clicked.getName() + "'s inventory"));
		return;
	}
}