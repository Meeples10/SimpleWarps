package io.github.meeples10.simplewarps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("simplewarps.delwarp")) {
                if(args.length == 0) return false;
                String name = args[0].replaceAll("[^A-Za-z0-9_\\-]", "_");
                Warp warp = Main.getWarp(name);
                if(warp != null) {
                    Main.WARPS.remove(warp);
                    sender.sendMessage(String.format(Main.warpDeletedMessage, name));
                } else {
                    sender.sendMessage(Main.warpNotFoundMessage);
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
