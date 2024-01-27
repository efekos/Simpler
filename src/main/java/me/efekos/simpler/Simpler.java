package me.efekos.simpler;

import me.efekos.simpler.config.MessageConfiguration;

/**
 * Main class of Simpler.
 */
public final class Simpler {
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
}
