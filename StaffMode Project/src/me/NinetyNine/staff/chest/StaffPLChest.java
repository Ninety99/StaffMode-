package me.NinetyNine.staff.chest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;

import me.NinetyNine.staff.Staff;
import me.NinetyNine.staff.utils.StaffUtils;

public class StaffPLChest implements Listener {

	/*
	 * Props to a guy on bukkit.org site, helped me a lot!
	 */

	public static void setupAnimationListener() {
		ProtocolLibrary.getProtocolManager().addPacketListener(
				new PacketAdapter(Staff.getInstance(), ListenerPriority.HIGH, PacketType.Play.Server.BLOCK_ACTION) {
					@Override
					public void onPacketSending(PacketEvent e) {
						if (e.getPacketType() == PacketType.Play.Server.BLOCK_ACTION) {
							Player listener = e.getPlayer();
							if (e.getPacket().getIntegers().read(1) != 1)
								return;
							BlockPosition position = e.getPacket().getBlockPositionModifier().read(0);
							if (position == null)
								return;
							Location loc = position.toVector().toLocation(listener.getWorld());
							Block b = listener.getWorld().getBlockAt(loc);

							if (!(b.getState() instanceof Chest))
								return;

							Chest chest = (Chest) b.getState();
							Inventory inv = chest.getBlockInventory();
							List<HumanEntity> humanViewers = inv.getViewers();
							List<Player> players = new ArrayList<>();
							for (HumanEntity entity : humanViewers) {
								if (entity instanceof Player)
									players.add((Player) entity);
							}
							for (Player p : players) {
								if (StaffUtils.isInStaffMode(p))
									e.setCancelled(true);
							}
						}
					}
				});
	}

	public static void setupSoundListener() {
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Staff.getInstance(),
				ListenerPriority.HIGH, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
			@Override
			public void onPacketSending(PacketEvent e) {
				if (e.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
					if (!(e.getPacket().getStrings().read(0).equalsIgnoreCase("random.chestopen")
							|| e.getPacket().getStrings().read(0).equalsIgnoreCase("random.chestclosed")))
						return;
					Player listener = e.getPlayer();

					if (!(StaffUtils.isInStaffMode(listener)))
						return;

					Location loc = new Location(listener.getWorld(), e.getPacket().getIntegers().read(0) / 8,
							e.getPacket().getIntegers().read(1) / 8, e.getPacket().getIntegers().read(2) / 8);
					Block b = listener.getWorld().getBlockAt(loc);
					check: if (!(b.getState() instanceof Chest)) {
						List<Location> adjacentBlockLocations = getAdjacentBlockLocations(loc);
						for (Location otherLocation : adjacentBlockLocations) {
							Block otherBlock = listener.getWorld().getBlockAt(otherLocation);
							if (otherBlock.getState() instanceof Chest) {
								b = otherBlock;
								loc = otherLocation;
								break check;
							}
						}
						return;
					}

					if (e.getPacket().getStrings().read(0).equalsIgnoreCase("random.chestopen")) {
						if (StaffUtils.isInStaffMode(listener))
							e.setCancelled(true);
						else
							return;
					}
					if (e.getPacket().getStrings().read(0).equalsIgnoreCase("random.chestclosed")) {
						if (StaffUtils.isInStaffMode(listener))
							e.setCancelled(true);
						else
							return;
					}

					Chest chest = (Chest) b.getState();
					Inventory inv = chest.getBlockInventory();
					List<HumanEntity> humanViewers = inv.getViewers();
					for (HumanEntity ent : humanViewers) {
						if (ent instanceof Player) {
							if (StaffUtils.isInStaffMode((Player) ent))
								e.setCancelled(true);
							else
								return;
						}
					}

				}
			}
		});
	}

	private static Location add(Location l, int x, int z) {
		return new Location(l.getWorld(), l.getX() + x, l.getY(), l.getZ() + z);
	}

	private static List<Location> getAdjacentBlockLocations(Location loc) {
		List<Location> adjacentBlockLocations = new ArrayList<>();
		adjacentBlockLocations.add(add(loc, 1, 0));
		adjacentBlockLocations.add(add(loc, -1, 0));
		adjacentBlockLocations.add(add(loc, 0, -1));
		adjacentBlockLocations.add(add(loc, 0, 1));
		adjacentBlockLocations.add(add(loc, 1, 1));
		adjacentBlockLocations.add(add(loc, -1, -1));
		adjacentBlockLocations.add(add(loc, 1, -1));
		adjacentBlockLocations.add(add(loc, -1, 1));
		return adjacentBlockLocations;
	}

}
