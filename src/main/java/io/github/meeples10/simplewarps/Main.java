package io.github.meeples10.simplewarps;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

    public static final String NAME = "SimpleWarps";
    public static final Set<Warp> WARPS = new HashSet<>();
    private static File df, cfg, warpsFile;
    static String playersOnlyMessage;
    static String noPermissionMessage;
    static String warpSetMessage;
    static String warpExistsMessage;
    static String warpDeletedMessage;
    static String warpNotFoundMessage;
    static String noWarpsMessage;
    static String teleportMessage;
    public static boolean playSound;
    public static Sound teleportSound;
    public static boolean resetVelocity;

    @Override
    public void onEnable() {
        df = Bukkit.getServer().getPluginManager().getPlugin(NAME).getDataFolder();
        cfg = new File(df, "config.yml");
        warpsFile = new File(df, "warps.yml");
        this.getCommand("setwarp").setExecutor(new CommandSetWarp());
        this.getCommand("warp").setExecutor(new CommandWarp());
        this.getCommand("warps").setExecutor(new CommandWarps());
        this.getCommand("delwarp").setExecutor(new CommandDelWarp());
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin(NAME));
        loadConfig();
        loadWarps();
    }

    @Override
    public void onDisable() {
        try {
            saveWarps();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadConfig() {
        try {
            if(!df.exists()) {
                df.mkdirs();
            }
            if(!cfg.exists()) {
                Bukkit.getPluginManager().getPlugin(NAME).saveDefaultConfig();
            }
            FileConfiguration c = YamlConfiguration.loadConfiguration(cfg);
            playSound = c.getBoolean("play-sound", true);
            teleportSound = Sound.sound(Key.key(c.getString("teleport-sound")), Sound.Source.PLAYER, 1f, 1f);
            resetVelocity = c.getBoolean("reset-velocity", false);
            ConfigurationSection messages = c.getConfigurationSection("messages");
            playersOnlyMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("players-only", "This command can only be used by players."));
            noPermissionMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("no-permission", "You do not have permission to use this command."));
            warpSetMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("warp-set", "Warp set: %s"));
            warpExistsMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("warp-exists", "There is already a warp named %s"));
            warpDeletedMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("warp-deleted", "Warp deleted: %s"));
            warpNotFoundMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("warp-not-found", "Warp not found."));
            noWarpsMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("no-warps", "There are no warps."));
            teleportMessage = ChatColor.translateAlternateColorCodes('&',
                    messages.getString("teleport", "Teleported to %s"));
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void loadWarps() {
        FileConfiguration c = YamlConfiguration.loadConfiguration(warpsFile);
        for(String key : c.getKeys(false)) {
            WARPS.add(Warp.load(c.getConfigurationSection(key)));
        }
    }

    private static void saveWarps() throws IOException {
        FileConfiguration c = warpsFile.exists() ? YamlConfiguration.loadConfiguration(warpsFile)
                : new YamlConfiguration();
        for(Warp warp : WARPS) {
            warp.save(c);
        }
        c.save(warpsFile);
    }

    public static Warp getWarp(String name) {
        for(Warp warp : WARPS) {
            if(warp.name.equalsIgnoreCase(name)) return warp;
        }
        return null;
    }
}
