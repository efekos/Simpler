package me.efekos.simpler.commands;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CommandManager {
    @SafeVarargs
    public static void registerBaseCommands(JavaPlugin plugin,BaseCommand... commands) throws  NoSuchFieldException,IllegalAccessException {
        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        for (BaseCommand command : commands){
            commandMap.register(command.getName(),command);
        }
    }

    public static void registerBaseCommand(JavaPlugin plugin,BaseCommand command) throws  NoSuchFieldException,IllegalAccessException {
        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        commandMap.register(command.getName(),command);
    }
}
