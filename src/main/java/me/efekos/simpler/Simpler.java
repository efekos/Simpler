package me.efekos.simpler;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.efekos.simpler.config.MessageConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of Simpler.
 */
public final class Simpler extends JavaPlugin {
    /**
     * Main configuration for messages that Simpler uses.
     */
    private static MessageConfiguration configuration = new MessageConfiguration.Builder().build();

    /**
     * Returns the message configuration Simpler is using right now.
     * @return Message config of Simpler.
     */
    public static MessageConfiguration getMessageConfiguration() {
        return configuration;
    }

    /**
     * Changes the configuration with the configuration given.
     * @param configuration New configuration that you want Simpler to use.
     */
    public static void changeMessageConfiguration(MessageConfiguration configuration) {
        Simpler.configuration = configuration;
    }

    /**
     * Checks for PlaceholderAPI existence in server.
     * @return whether the server has PlaceholderAPI
     */
    public static boolean hasPlaceholderApi(){
        try {
            return Bukkit.getServer().getPluginManager().isPluginEnabled(PlaceholderAPIPlugin.getInstance());
        } catch (Exception e){
            return Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        }
    }

    @Override
    public void onEnable() {

    }
}
