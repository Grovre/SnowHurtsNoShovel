package me.grovre.snowhurtsnoshovel;

import me.grovre.snowhurtsnoshovel.Listeners.BreakBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SnowHurtsNoShovel extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("\n\n\nSnow never hurt no block :(\n\n\n");
    }
}
