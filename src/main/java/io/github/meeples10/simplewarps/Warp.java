package io.github.meeples10.simplewarps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Warp {
    public String name;
    public Location location;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public static Warp load(ConfigurationSection section) {
        return new Warp(section.getName(),
                new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"),
                        section.getDouble("y"), section.getDouble("z"), (float) section.getDouble("yaw"),
                        (float) section.getDouble("pitch")));
    }

    public void save(FileConfiguration fc) {
        if(fc.getConfigurationSection(name) == null) {
            fc.createSection(name);
        }
        ConfigurationSection c = fc.getConfigurationSection(name);
        c.set("world", location.getWorld().getName());
        c.set("x", location.getX());
        c.set("y", location.getY());
        c.set("z", location.getZ());
        c.set("yaw", location.getYaw());
        c.set("pitch", location.getPitch());
    }
}
