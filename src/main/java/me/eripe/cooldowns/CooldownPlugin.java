package me.eripe.cooldowns;

import lombok.Getter;
import me.eripe.cooldowns.bundle.BundleStorage;
import me.eripe.cooldowns.bundle.implementations.ConfigYaml;
import me.eripe.cooldowns.commands.CommandManager;
import me.eripe.cooldowns.commands.implementations.CooldownCommand;
import me.eripe.cooldowns.data.CooldownManager;
import me.eripe.cooldowns.listeners.BlockPlaceListener;
import me.eripe.cooldowns.listeners.EntityDamageByEntityListener;
import me.eripe.cooldowns.listeners.PlayerItemConsumeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CooldownPlugin extends JavaPlugin {

    @Getter private static CooldownPlugin cooldownPlugin;

    @Override
    public void onEnable() {
        cooldownPlugin = this;
        (new BundleStorage()).registerYamls(new ConfigYaml(this, "config.yml"));
        (new CooldownManager()).load();
        (new CommandManager()).registerCommands(new CooldownCommand());
        registerListeners(new BlockPlaceListener(), new EntityDamageByEntityListener(), new PlayerItemConsumeListener());
    }

    public void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void registerListeners(Listener... listeners){
        Arrays.asList(listeners).forEach(this::registerListener);
    }
}
