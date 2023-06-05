package io.github.meeples10.simplewarps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("simplewarps.setwarp")) {
                if(args.length == 0) return false;
                Player p = (Player) sender;
                String name = args[0].replaceAll("[^A-Za-z0-9_\\-]", "_");
                if(Main.getWarp(name) != null) {
                    sender.sendMessage(String.format(Main.warpExistsMessage, name));
                } else {
                    Main.WARPS.add(new Warp(name, p.getLocation()));
                    sender.sendMessage(String.format(Main.warpSetMessage, name));
                }
            } else {
                sender.sendMessage(Main.noPermissionMessage);
            }
        } else {
            sender.sendMessage(Main.playersOnlyMessage);
        }
        return true;
    }
}
