package com.samwolfson.panicbutton;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Collection;


public class EventListener implements Listener {

    public static final int BOUNDING_BOX_SIZE = 32;

    @EventHandler
    public void onPlayerButtonPress(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();

        // only deal with buttons
        if (!clickedBlock.getType().equals(Material.OAK_BUTTON)) {
            return;
        }

        Location clickedBlockLocation = clickedBlock.getLocation();
        Block oneUp = clickedBlock.getWorld().getBlockAt(clickedBlockLocation.add(0, 1, 0));


        if (!oneUp.getType().equals(Material.BIRCH_WALL_SIGN)) {
            return;
        }

        Sign sign = (Sign) oneUp.getState();

        // if sign reads the wrong thing, then early return
        if (!sign.getLine(1).equals("DO NOT PRESS")) {
            return;
        }

        Collection<Entity> nearby = clickedBlock.getWorld()
                .getNearbyEntities(clickedBlockLocation, BOUNDING_BOX_SIZE, BOUNDING_BOX_SIZE, BOUNDING_BOX_SIZE);

        int undesirables = 0;

        for (Entity e : nearby) {
            if (e.getType().equals(EntityType.IRON_GOLEM) || e.getType().equals(EntityType.ZOMBIFIED_PIGLIN)) {
                e.remove();
                undesirables++;
            }
        }

        System.out.println("Killed " + undesirables + " Undesirable Mobs.");
        event.getPlayer().getServer().broadcastMessage("PANIC!!!1!!");
    }
}
