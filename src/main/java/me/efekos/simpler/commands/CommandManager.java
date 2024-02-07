/*
 * Copyright (c) 2024 efekos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.efekos.simpler.commands;

import me.efekos.simpler.exception.InvalidAnnotationException;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Static command manager for registering commands.
 */
public class CommandManager {

    /**
     * Registers multiple {@link BaseCommand}s at the same time.
     * @param plugin An Instance of your plugin that is using this API. If called within plugin main class, provide this keyword
     * @param commands A class reference of every base command to be registered
     * @throws NoSuchFieldException If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException If we can't access commandField
     * @throws NoSuchMethodException If there is no constructor in one of the command classes, or creating a new Instance of a command class fails
     * @throws InvocationTargetException If creating a new Instance of command fails
     * @throws InstantiationException If creating a new Instance of command fails
     * @throws InvalidAnnotationException If one of the commands does not have a @{@link Command} annotation
     */
    @SafeVarargs
    public static void registerBaseCommands(JavaPlugin plugin,Class<? extends BaseCommand>... commands) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException,InvalidAnnotationException {
        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        for (Class<? extends BaseCommand> command : commands){
            if(command.getAnnotation(Command.class)==null) throw new InvalidAnnotationException(command.getName() + " Requires a me.efekos.simpler.commands.Command to be registered as command.");


            Constructor<? extends BaseCommand> cmd = command.getConstructor(String.class);
            BaseCommand newCommand = cmd.newInstance(command.getName());

            commandMap.register(plugin.getName(), newCommand);
        }
    }

    /**
     * Registers one base command
     *
     * @param plugin  An Instance of your plugin that is using this API. If called within plugin main class, provide this keyword
     * @param command a class reference of the base command class.
     * @throws NoSuchFieldException       If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException     If we can't access commandField
     * @throws NoSuchMethodException      If there is no constructor in command parameter, or creating a new Instance of command fails
     * @throws InvocationTargetException  If creating a new Instance of command fails
     * @throws InstantiationException     If creating a new Instance of command fails
     * @throws InvalidAnnotationException If given command class does not have a @{@link Command} annotation
     */
    public static void registerBaseCommand(JavaPlugin plugin, Class<? extends BaseCommand> command) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, InvalidAnnotationException {
        if(command.getAnnotation(Command.class)==null) throw new InvalidAnnotationException(command.getName() + " Requires a me.efekos.simpler.commands.Command to be registered as command.");

        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());

        Constructor<? extends BaseCommand> cmd = command.getConstructor(String.class);
        cmd.setAccessible(true);
        BaseCommand newCommand = cmd.newInstance(command.getName());
        commandMap.register(plugin.getName(), newCommand);
    }

    /**
     * Registers a {@link CoreCommand}.
     * @param plugin an instance of your {@link JavaPlugin}.
     * @param command the {@link CoreCommand} that you want to register
     * @throws InvalidAnnotationException if the given {@link CoreCommand} does not have a @{@link Command} annotation
     * @throws NoSuchFieldException If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException If we can't access commandField
     * @throws NoSuchMethodException If there is no constructor in the {@link CoreCommand} given, or creating a new instance of command fails.
     * @deprecated If you want to use this method, you have to include sub commands yourself, using {@link CommandManager#registerCoreCommand(JavaPlugin, Class, Class[])}
     */
    @Deprecated
    public static void registerCoreCommand(JavaPlugin plugin,Class<? extends CoreCommand> command) throws InvalidAnnotationException,NoSuchFieldException,IllegalAccessException,NoSuchMethodException{
        if(command.getAnnotation(Command.class)==null) throw new InvalidAnnotationException(command.getName() + " Requires a me.efekos.simpler.commands.Command to be registered as command.");

        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap map = (CommandMap) commandField.get(plugin.getServer());

        Constructor<? extends CoreCommand> cmd = command.getConstructor(String.class);
        cmd.setAccessible(true);
        try{
            CoreCommand newCommand = cmd.newInstance(command.getName());

            map.register(plugin.getName(),newCommand);
        } catch (Exception e){e.printStackTrace();}
    }

    public static void registerCoreCommand(JavaPlugin plugin,Class<? extends CoreCommand> command,Class<? extends SubCommand>... subCommands) throws InvalidAnnotationException,NoSuchFieldException,IllegalAccessException,NoSuchMethodException{
        if(command.getAnnotation(Command.class)==null) throw new InvalidAnnotationException(command.getName() + " Requires a me.efekos.simpler.commands.Command to be registered as command.");

        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap map = (CommandMap) commandField.get(plugin.getServer());

        Constructor<? extends CoreCommand> cmd = command.getConstructor(String.class);
        cmd.setAccessible(true);
        try{
            CoreCommand newCommand = cmd.newInstance(command.getName());
            newCommand.setSubList(Arrays.asList(subCommands));

            map.register(plugin.getName(),newCommand);
        } catch (Exception e){e.printStackTrace();}
    }

    /**
     * Creates a command out of the tree and executes it.
     * @param plugin an instance of your {@link JavaPlugin}.
     * @param tree Any {@link CommandTree}.
     * @throws NoSuchFieldException If there is no commandMap, which is not usual in a healthy server.
     * @throws IllegalAccessException If we can't access commandField
     */
    public static void registerCommandTree(JavaPlugin plugin,CommandTree tree) throws NoSuchFieldException, IllegalAccessException {
        TreeCommand treeCommand = new TreeCommand(tree);

        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap map = (CommandMap) commandField.get(plugin.getServer());

        map.register(plugin.getName(),treeCommand);
    }
}
