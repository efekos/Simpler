package me.efekos.simpler;

import me.efekos.simpler.config.MessageConfiguration;
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

    @Override
    public void onEnable() {

        //this thing simply makes a /home set <pos> command with nms. I'll use it later 👀👀
        //CommandDispatcher<CommandListenerWrapper> dispatcher = MinecraftServer.getServer().vanillaCommandDispatcher.a();

        //
        //dispatcher.register(net.minecraft.commands.CommandDispatcher.a("home")
        //        .then(net.minecraft.commands.CommandDispatcher.a("set")
        //                .then(net.minecraft.commands.CommandDispatcher.a("pos", ArgumentPosition.a())
        //                        .executes(commandContext -> {
        //                            commandContext.getSource().a(IChatBaseComponent.a("sdf"));
        //                            return 1;
        //                        })
        //                )
        //        )
        //);
    }
}
