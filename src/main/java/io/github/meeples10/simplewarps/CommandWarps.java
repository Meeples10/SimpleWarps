package io.github.meeples10.simplewarps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandWarps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("simplewarps.warps")) {
                if(Main.WARPS.size() == 0) {
                    sender.sendMessage(Main.noWarpsMessage);
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for(Warp warp : Main.WARPS) {
                    sb.append(ChatColor.DARK_AQUA);
                    sb.append(warp.name);
                    if(i < Main.WARPS.size() - 1) {
                        sb.append(ChatColor.GRAY);
                        sb.append(", ");
                    }
                    i++;
                }
                sender.sendMessage(sb.toString());
            } else {
                sender.sendMessage(Main.noPermissionMessage);
            }
        } else {
            sender.sendMessage(Main.playersOnlyMessage);
        }
        return true;
    }
}
