package me.eripe.cooldowns.commands;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class Command extends org.bukkit.command.Command {

    protected Command(String name) {
        super(name);
        setPermission("eripe.command." + name);
    }

    public Command makeUp(String description, String usage, String[] aliases){
        setDescription(description);
        setUsage(usage);
        setAliases(Arrays.asList(aliases));
        return this;
    }

    @Getter private CommandSender commandSender;

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(!isNullOrEmpty(getPermission())){
            if(!commandSender.hasPermission(getPermission())){
                commandSender.sendMessage(ChatColor.RED + "Access denied! ("+getPermission()+")");
                return false;
            }
        }
        this.commandSender = commandSender;
        return onCommand(commandSender, strings);
    }

    public abstract boolean onCommand(CommandSender commandSender, String[] args);

    public static boolean isNullOrEmpty(String text){
        if(text == null){
            return true;
        }
        if(text.isEmpty()){
            return true;
        }
        return false;
    }
}

