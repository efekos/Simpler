package me.efekos.simpler;

import org.bukkit.plugin.java.JavaPlugin;

public final class Simpler extends JavaPlugin {
    private final String customItemDataPath = getDataFolder().getAbsolutePath()+"\\CustomItemData.json";

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
