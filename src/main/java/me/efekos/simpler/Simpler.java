package me.efekos.simpler;

import me.efekos.simpler.config.MessageConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of Simpler. does not have a use right now.
 */
public final class Simpler extends JavaPlugin {
    private static MessageConfiguration configuration;

    public static MessageConfiguration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(MessageConfiguration configuration) {
        Simpler.configuration = configuration;
    }

    @Override
    public void onEnable() {
        configuration = new MessageConfiguration.Builder().build();
    }
}
