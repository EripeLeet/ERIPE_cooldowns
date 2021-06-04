package me.eripe.cooldowns.data;

import lombok.Getter;
import me.eripe.cooldowns.CooldownPlugin;
import me.eripe.cooldowns.bundle.BundleStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CooldownManager {

    @Getter private static Map<Material, Cooldown> COOLDOWNS = new HashMap<>();

    public static void createCooldown(Cooldown cooldown){
        getCOOLDOWNS().put(cooldown.getMaterial(), cooldown);
    }

    public static Cooldown getCooldown(Material material){
        if(getCOOLDOWNS().containsKey(material)){
            return getCOOLDOWNS().get(material);
        }
        return null;
    }

    public void load(){
        FileConfiguration config = BundleStorage.getBunlde("config.yml").getConfig();
        for(String id : config.getConfigurationSection("cooldowns").getKeys(false)) {
            Material material = Material.getMaterial(Objects.requireNonNull(config.getString("cooldowns." + id + ".material")));
            int time = config.getInt("cooldowns." + id + ".time");
            Set<CooldownType> set = new HashSet<>();
            for (String type : config.getStringList("cooldowns." + id + ".set")) {
                CooldownType cooldownType = CooldownType.fromString(type);
                if (cooldownType != null) {
                    set.add(cooldownType);
                }
            }
            createCooldown(new Cooldown(id, material, time, set));
        }
    }

    public static void apply(Player player, Material material, CooldownType... cooldownTypes){
        if(material == null || material.equals(Material.AIR)) return;
        Cooldown cooldown = CooldownManager.getCooldown(material);
        if(cooldown == null) return;
        if(cooldown.getCooldownTypes().isEmpty()) return;
        if(Arrays.asList(cooldownTypes).containsAll(cooldown.getCooldownTypes())){
            cooldown.apply(player);
            if(material.equals(Material.SHIELD)){
                ItemStack main = null;
                ItemStack off = null;
                if(player.getInventory().getItemInMainHand().getType().equals(Material.SHIELD)){
                    main = player.getInventory().getItemInMainHand().clone();
                    player.getInventory().setItemInMainHand(null);
                }
                if(player.getInventory().getItemInOffHand().getType().equals(Material.SHIELD)){
                    off = player.getInventory().getItemInOffHand().clone();
                    player.getInventory().setItemInOffHand(null);
                }
                ItemStack finalMain = main;
                ItemStack finalOff = off;
                new BukkitRunnable() {
                    UUID uuid = player.getUniqueId();
                    @Override
                    public void run() {
                        Player p = Bukkit.getPlayer(uuid);
                        if(p == null){
                            cancel();
                            return;
                        }
                        if(finalMain != null){
                            p.getInventory().setItemInMainHand(finalMain);
                        }
                        if(finalOff != null){
                            p.getInventory().setItemInOffHand(finalOff);
                        }
                        cancel();
                    }
                }.runTaskLater(CooldownPlugin.getCooldownPlugin(), 1L);
            }
        }
    }
}
