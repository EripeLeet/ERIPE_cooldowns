package me.eripe.cooldowns.bundle;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BundleStorage {

    @Getter private static Map<String, Yaml> YAMLS = new HashMap<>();

    public BundleStorage(){
    }

    public void registerYaml(Yaml yaml){
        getYAMLS().put(yaml.getName().toLowerCase(), yaml);
    }

    public void registerYamls(Yaml... yamls){
        Arrays.asList(yamls).forEach(this::registerYaml);
    }

    public static Yaml getBunlde(String name){
        return getYAMLS().get(name.toLowerCase());
    }



}
