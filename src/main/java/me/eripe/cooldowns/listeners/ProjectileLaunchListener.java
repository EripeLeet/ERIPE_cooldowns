package me.eripe.cooldowns.listeners;

import me.eripe.cooldowns.CooldownPlugin;
import me.eripe.cooldowns.data.Cooldown;
import me.eripe.cooldowns.data.CooldownManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileLaunchListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLaunch(ProjectileLaunchEvent event){
        if(event.isCancelled()) return;
        if(event.getEntity().getShooter() instanceof Player){
            Player player = (Player) event.getEntity().getShooter();
            if(event.getEntity() instanceof EnderPearl) {
                if(CooldownManager.getCOOLDOWNS().containsKey(Material.ENDER_PEARL)){
                    Cooldown cooldown = CooldownManager.getCooldown(Material.ENDER_PEARL);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(Bukkit.getPlayer(player.getUniqueId()) != null)
                                player.setCooldown(Material.ENDER_PEARL, cooldown.getTime());
                        }
                    }.runTaskLater(CooldownPlugin.getCooldownPlugin(), 1l);
                }
            }
        }
    }
}
