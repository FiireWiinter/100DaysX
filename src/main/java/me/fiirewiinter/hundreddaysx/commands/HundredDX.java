package me.fiirewiinter.hundreddaysx.commands;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.gui.MainGUI;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HundredDX implements CommandExecutor {
    public HundredDX(Main plugin) {
        plugin.getCommand("100dx").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can not be run through the console!");
            return true;
        } else {
            Player p = (Player)sender;
            if (p.hasPermission("100dx.admin")) {
                p.openInventory(MainGUI.GUI(p));
            } else {
                p.sendMessage(Utils.chat("&cYou are missing the permission 100dx.admin"));
            }

            return false;
        }
    }
}
