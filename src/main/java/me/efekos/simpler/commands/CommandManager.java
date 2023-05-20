package me.efekos.simpler.commands;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Static command manager for registering commands.
 */
public class CommandManager {

    /**
     * Registers multiple base commands at the same time.
     * @param plugin An Instance of your plugin that is using this API. If called within plugin main class, provide this keyword
     * @param commands A class reference of every base command to be registered
     * @throws NoSuchFieldException If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException If we can't access commandField
     * @throws NoSuchMethodException If there is no constructor in one of the command classes, or creating a new Instance of a command class fails
     * @throws InvocationTargetException If creating a new Instance of command fails
     * @throws InstantiationException If creating a new Instance of command fails
     */
    @SafeVarargs
    public static void registerBaseCommands(JavaPlugin plugin,Class<? extends BaseCommand>... commands) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        for (Class<? extends BaseCommand> command : commands){
            Constructor<? extends BaseCommand> cmd = command.getConstructor(String.class);
            BaseCommand newCommand = cmd.newInstance(command.getName());
            commandMap.register(plugin.getName(), newCommand);
        }
    }

    /**
     * Registers one base command
     * @param plugin An Instance of your plugin that is using this API. If called within plugin main class, provide this keyword
     * @param command a class reference of the base command class.
     * @throws NoSuchFieldException If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException If we can't access commandField
     * @throws NoSuchMethodException If there is no constructor in command parameter, or creating a new Instance of command fails
     * @throws InvocationTargetException If creating a new Instance of command fails
     * @throws InstantiationException If creating a new Instance of command fails
     */
    public static void registerBaseCommand(JavaPlugin plugin, Class<? extends BaseCommand> command) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        Constructor<? extends BaseCommand> cmd = command.getConstructor(String.class);
        BaseCommand newCommand = cmd.newInstance(command.getName());
        commandMap.register(plugin.getName(), newCommand);
    }
}
