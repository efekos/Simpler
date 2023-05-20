package me.efekos.simpler;

import me.efekos.simpler.commands.CommandManager;
import me.efekos.simpler.egs.feed;
import me.efekos.simpler.egs.items.testcompass;
import me.efekos.simpler.events.MenuEvents;
import me.efekos.simpler.events.PlayerEvents;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simpler extends JavaPlugin {
    private static Simpler plugin;

    public static Simpler getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        // Plugin startup logic
        try {
            CommandManager.registerBaseCommand(this, feed.class);

            ItemManager.registerItem("testcompass", testcompass.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerEvents(),this);
        getServer().getPluginManager().registerEvents(new MenuEvents(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
