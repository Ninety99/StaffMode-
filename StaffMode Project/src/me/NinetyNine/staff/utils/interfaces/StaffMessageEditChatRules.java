package me.NinetyNine.staff.utils.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.NinetyNine.staff.chatrules.StaffChatRulesInventory;

public interface StaffMessageEditChatRules extends Listener {

	public void setChatRule(Player player, String message);

	@EventHandler
	public default void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().isOp())
			return;
		
		if (!(StaffChatRulesInventory.getInEditMode().contains(e.getPlayer())))
			return;

		e.setCancelled(true);
		setChatRule(e.getPlayer(), e.getMessage());
	}
}