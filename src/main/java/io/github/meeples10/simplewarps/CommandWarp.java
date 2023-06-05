package io.github.meeples10.simplewarps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.kyori.adventure.sound.Sound;

public class CommandWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("simplewarps.warp")) {
                if(args.length == 0) return false;
                Player p = (Player) sender;
                String name = args[0].replaceAll("[^A-Za-z0-9_\\-]", "_");
                Warp warp = Main.getWarp(name);
                if(warp != null) {
                    if(Main.resetVelocity) {
                        p.setVelocity(new Vector(0, 0, 0));
                        p.setFallDistance(0.0f);
                    }
                    p.teleport(warp.location);
                    if(Main.playSound) {
                        p.playSound(Main.teleportSound, Sound.Emitter.self());
                    }
                    sender.sendMessage(String.format(Main.teleportMessage, name));
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
