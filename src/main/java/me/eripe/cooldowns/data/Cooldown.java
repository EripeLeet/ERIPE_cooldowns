package me.eripe.cooldowns.data;

import lombok.Data;
import me.eripe.cooldowns.CooldownPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public @Data class Cooldown {

    private String id;
    private Material material;
    private int time;
    private Set<CooldownType> cooldownTypes;

    public Cooldown(String id, Material material, int time, Set<CooldownType> cooldownTypes){
        this.id = id;
        this.material = material;
        this.time = time;
        this.cooldownTypes = cooldownTypes;
    }

    public void apply(Player player){
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setCooldown(getMaterial(), time);
                player.updateInventory();
            }
        }.runTaskLater(CooldownPlugin.getCooldownPlugin(), 1L);
    }
}
