package me.fiirewiinter.hundreddaysx.commands;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    public StartCommand(Main plugin) {
        plugin.getCommand("100dxstart").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(Utils.chat("&l&o&n&cTHIS IS NOT IMPLEMENTED YET!"));
        return false;
    }
}
