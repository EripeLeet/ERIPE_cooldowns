package me.eripe.cooldowns.listeners;

import me.eripe.cooldowns.data.CooldownManager;
import me.eripe.cooldowns.data.CooldownType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsumeListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onConsume(PlayerItemConsumeEvent event){
        if(event.isCancelled()) return;
        Material material = event.getItem().getType();
        CooldownManager.apply(event.getPlayer(), material, CooldownType.CONSUME);
    }
}
