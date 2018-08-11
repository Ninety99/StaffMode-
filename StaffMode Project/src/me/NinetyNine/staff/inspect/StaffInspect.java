package me.NinetyNine.staff.inspect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

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

		if (!(player.getItemInHand().getType() == Material.STICK))
			return;

		if (player.getItemInHand().getItemMeta().getDisplayName() == ChatColor.GREEN + "Inspect")
			return;

		Player clicked = (Player) e.getRightClicked();

		Inventory clickedInventory = Bukkit.createInventory(null, clicked.getInventory().getSize() + 9,
				ChatColor.DARK_GRAY + clicked.getName() + "'s inventory");

		for (ItemStack item : clicked.getInventory().getContents()) {
			for (int i = 0; i < clicked.getInventory().getSize(); i++)
				clickedInventory.setItem(i, item);
		}

		clickedInventory.setItem(36, clicked.getInventory().getHelmet());
		clickedInventory.setItem(37, clicked.getInventory().getChestplate());
		clickedInventory.setItem(38, clicked.getInventory().getLeggings());
		clickedInventory.setItem(39, clicked.getInventory().getBoots());

		List<String> lore = new ArrayList<String>();
		for (PotionEffect effect : getPotionEffects(clicked)) {
			lore.add(ChatColor.GOLD + "Potion Effect type(s):" + effect.getType().getName().substring(1).toLowerCase());
			lore.add(ChatColor.AQUA + "Duration: " + effect.getDuration());
			lore.add(ChatColor.AQUA + "Amplifier(Potion Level):" + effect.getAmplifier());
		}

		StaffItems.createItem(clickedInventory, 40, Material.GLASS_BOTTLE, "Active Potion Effects", lore, false);

		List<String> lore2 = new ArrayList<String>();
		double health = clicked.getHealth() - 10.0;
		lore2.add(ChatColor.RED + "Health: " + health + ChatColor.RED + "❤️");
		lore2.add(ChatColor.RED + "Food Level: " + clicked.getFoodLevel());

		StaffItems.createItem(clickedInventory, 42, Material.SPECKLED_MELON, ChatColor.GRAY + "Player Info", lore2,
				false);

		StaffItems.createItemWithBMInfo(clicked, clickedInventory, 44, Material.EMERALD, ChatColor.RED + "BMInfo");

		player.openInventory(clickedInventory);
		player.sendMessage(StaffUtils.format("&9Opening " + clicked.getName() + "'s inventory"));
		return;
	}

	private List<PotionEffect> getPotionEffects(Player player) {
		List<PotionEffect> pots = new ArrayList<PotionEffect>();

		for (PotionEffect effect : player.getActivePotionEffects())
			if (effect != null)
				pots.add(effect);
			else
				return null;

		return pots;
	}
}