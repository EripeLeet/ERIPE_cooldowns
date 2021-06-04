package me.eripe.cooldowns.data;

import java.util.Arrays;

public enum CooldownType {

    BLOCK_PLACE, CONSUME, DAMAGE_GIVEN, DAMAGE_RECEIVED;

    public static CooldownType fromString(String name){
        return Arrays.stream(CooldownType.values()).filter(cooldownType -> cooldownType.toString().equalsIgnoreCase(name)).findAny().orElse(null);
    }
}
