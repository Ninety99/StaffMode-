package me.NinetyNine.staff.misc;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.staff.utils.StaffItems;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffDrop implements Listener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (!(StaffUtils.isInStaffMode(e.getPlayer())))
			return;

		ItemStack item = e.getItemDrop().getItemStack();

		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop items while in Staff mode!"));
		} else {
			if (StaffItems.isStaffItem(item)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(StaffUtils.format("&cYou cannot drop that item!"));
				return;
			} else {
				if (item.getType().toString().startsWith("DIAMOND") && item.getType().toString().startsWith("GOLD")
						&& item.getType().toString().startsWith("IRON"))
					System.out.println(e.getPlayer().getName()
							+ " dropped diamond/gold/iron item(s) while in creative mode (Amount: " + item.getAmount()
							+ ")");
			}
		}
	}
}