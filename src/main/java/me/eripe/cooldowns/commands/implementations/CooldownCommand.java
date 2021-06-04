package me.eripe.cooldowns.commands.implementations;

import me.eripe.cooldowns.bundle.BundleStorage;
import me.eripe.cooldowns.commands.Command;
import me.eripe.cooldowns.data.CooldownManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CooldownCommand extends Command {

    public CooldownCommand() {
        super("cooldown");
        makeUp("Reload plugin configuration file!", "/cooldown reload", new String[0]);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        if(args.length == 0){
            commandSender.sendMessage(ChatColor.RED + "To reload the file, enter: /cooldown reload");
            return false;
        }
        if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")){
            BundleStorage.getBunlde("config.yml").reloadData();
            CooldownManager.getCOOLDOWNS().clear();
            (new CooldownManager()).load();
            commandSender.sendMessage(ChatColor.GREEN + "The file and manager has been reloaded!");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "To reload the file, enter: /cooldown reload");
        return false;
    }
}
