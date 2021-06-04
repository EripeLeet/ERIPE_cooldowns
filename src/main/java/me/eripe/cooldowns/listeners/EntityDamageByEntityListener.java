package me.eripe.cooldowns.listeners;

import me.eripe.cooldowns.data.CooldownManager;
import me.eripe.cooldowns.data.CooldownType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.isCancelled()) return;
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            CooldownManager.apply(player, player.getInventory().getItemInMainHand().getType(), CooldownType.DAMAGE_RECEIVED);
            CooldownManager.apply(player, player.getInventory().getItemInOffHand().getType(), CooldownType.DAMAGE_RECEIVED);
        }
        if(event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            CooldownManager.apply(player, player.getInventory().getItemInMainHand().getType(), CooldownType.DAMAGE_GIVEN);
            CooldownManager.apply(player, player.getInventory().getItemInOffHand().getType(), CooldownType.DAMAGE_GIVEN);
        }
    }
}
