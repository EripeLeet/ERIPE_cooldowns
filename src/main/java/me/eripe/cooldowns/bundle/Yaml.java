package me.eripe.cooldowns.bundle;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public abstract class Yaml implements Bundle {

    @Getter private Plugin plugin;
    @Getter private String name;
    @Getter private File file;
    @Getter private FileConfiguration config;

    public Yaml(Plugin plugin, String name){
        super();
        this.plugin = plugin;
        this.name = name;
        init();
    }

    @Override
    public void init() {
        checkFiles();
        reloadData();
    }

    @Override
    public void checkFiles() {
        file = new File(getPlugin().getDataFolder(), getName());
        if(!file.exists()){
            if(file.getParentFile().mkdirs()){
                getPlugin().saveResource(getName(), true);
            }
        }
    }

    @Override
    public void reloadData() {
        config = YamlConfiguration.loadConfiguration(getFile());
    }

    @Override
    public void saveData() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
