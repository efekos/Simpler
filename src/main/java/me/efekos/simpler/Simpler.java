package me.efekos.simpler;

import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.Command;
import me.efekos.simpler.commands.CommandManager;
import me.efekos.simpler.commands.SubOf;
import me.efekos.simpler.config.MessageConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Set;

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

    public static void registerCommands(JavaPlugin plugin){
        Reflections reflections = new Reflections(plugin.getClass().getPackageName());

        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(Command.class);
        Set<Class<?>> subCommandClasses = reflections.getTypesAnnotatedWith(SubOf.class);


        for (Class<?> commandClass : commandClasses) {
            try {

                if(commandClass.getSuperclass()!=null&&commandClass.getSuperclass()== BaseCommand.class) CommandManager.registerBaseCommand(plugin, ((Class<? extends BaseCommand>) commandClass));
                //TODO core commands
                //TODO tree commands

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //TODO: NMS commands
    //TODO: finish guides
    //TODO: command registration with just annotations
}
