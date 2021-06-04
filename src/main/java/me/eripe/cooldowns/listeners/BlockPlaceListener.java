package me.eripe.cooldowns.listeners;

import me.eripe.cooldowns.data.CooldownManager;
import me.eripe.cooldowns.data.CooldownType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlace(BlockPlaceEvent event){
        if(event.isCancelled()) return;
        Material material = event.getBlockPlaced().getType();
        CooldownManager.apply(event.getPlayer(), material, CooldownType.BLOCK_PLACE);
    }
}
