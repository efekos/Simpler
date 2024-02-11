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

import me.efekos.simpler.Simpler;
import me.efekos.simpler.commands.syntax.Argument;
import me.efekos.simpler.commands.syntax.ArgumentHandleResult;
import me.efekos.simpler.commands.syntax.ArgumentPriority;
import me.efekos.simpler.config.MessageConfiguration;
import me.efekos.simpler.exception.InvalidAnnotationException;
import me.efekos.simpler.translation.TranslateManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Used for core commands like /friends invite,/friends list,/friends remove etc. {@link #getSubs()} will return a list of the {@link SubCommand}s that belong to this command. Must be annotated with {@link me.efekos.simpler.commands.Command} to be registered properly.
 */
public abstract class CoreCommand extends Command {
    /**
     * Creates a new core command instance. Not really needed for usage, but you have to override this constructor in
     * your class.
     * @param name Name of the command, not important in this case though.
     */
    protected CoreCommand(@NotNull String name) {
        super(name);
    }

    /**
     * Creates a new core command instance. Not really needed for usage, but you have to override this constructor in
     * your class.
     * @param name Name of the command, not important in this case though.
     * @param description Description.
     * @param usageMessage Usage message.
     * @param aliases Alias list.
     */
    protected CoreCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * @return Command name as string.
     */
    @Override
    @NotNull
    public String getName() {
        me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);
        if (command != null) return command.name();
        return super.getName();
    }

    /**
     * @return Permission this command needs to be executed as String, null if this command does not need any permission.
     */
    @Override
    @Nullable
    public String getPermission() {
        me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);
        if (command != null) return command.permission();
        return super.getPermission();
    }

    /**
     * @return A brief description of this command
     */
    @Override
    @NotNull
    public String getDescription() {
        me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);
        if (command != null) return command.description();
        return super.getDescription();
    }

    /**
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
    @NotNull
    @Override
    public String getUsage() {
        return "/" + getName() + " <sub> <args>";
    }

    private List<Class<? extends SubCommand>> subList = new ArrayList<>();

    public void setSubList(List<Class<? extends SubCommand>> subList) {
        this.subList = subList;
    }

    /**
     * Returns a list of the {@link SubCommand}s that belong to this {@link CoreCommand}.
     *
     * @return List of the subs.
     */
    @NotNull
    public List<Class<? extends SubCommand>> getSubs() {
        return subList;
    }

    /**
     * Finds a {@link SubCommand} by its name using {@link SubCommand#getName()}.
     *
     * @param name Name of the {@link SubCommand} you want to get.
     * @return {@link SubCommand} if found, null otherwise.
     */
    @Nullable
    public Class<? extends SubCommand> getSub(String name) {
        for (Class<? extends SubCommand> sub : getSubs()) {
            me.efekos.simpler.commands.Command command = sub.getAnnotation(me.efekos.simpler.commands.Command.class);
            if (command.name().equals(name)) {
                return sub;
            }
        }
        return null;
    }

    /**
     * Grabs the value of {@link me.efekos.simpler.commands.Command#playerOnly()} and returns it.
     *
     * @return Is this command or subs of this command can be used by something that is not player?
     */
    public boolean isPlayerOnly() {
        me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);
        if (command != null) return command.playerOnly();
        else return false;
    }

    /**
     * Provides a text that will help the {@link CommandSender} trying to execute one of the {@link SubCommand}s at this command.
     *
     * @param sender       The sender who is struggling with using any sub of this command.
     * @param subInstances Instances of all the {@link SubCommand}s under this {@link CoreCommand}.
     */
    public abstract void renderHelpList(CommandSender sender, List<SubCommand> subInstances);

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        MessageConfiguration configuration = Simpler.getMessageConfiguration();

        if (args.length == 0) {
            sender.sendMessage(TranslateManager.translateColors(configuration.USAGE.replace("%usage%", getUsage())));
            return true;
        }

        Class<? extends SubCommand> cmd = getSub(args[0]);
        if (cmd == null || args[0].equals("help")) {
            List<SubCommand> subCommands = new ArrayList<>();
            for (Class<? extends SubCommand> sub : getSubs()) {
                try {
                    Constructor<? extends SubCommand> constructor = sub.getConstructor(String.class);
                    me.efekos.simpler.commands.Command commandA = sub.getAnnotation(me.efekos.simpler.commands.Command.class);
                    SubCommand command = constructor.newInstance(commandA.name());
                    subCommands.add(command);
                } catch (Exception ignored) {}
            }

            renderHelpList(sender, subCommands);
            return true;
        }

        me.efekos.simpler.commands.Command cmdA = cmd.getAnnotation(me.efekos.simpler.commands.Command.class);
        if (cmdA == null) {
            try {
                throw new InvalidAnnotationException(cmd.getName() + " Must have a @Command annotation.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        try {
            if (sender instanceof Player) { //sender is a player
                me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);

                if (hasPermission() && !sender.hasPermission(command.permission())) { // @Command has a permission and player don't have the permission
                    sender.sendMessage(TranslateManager.translateColors(configuration.NO_PERMISSION));
                    return true;
                }

                // @Command don't have a permission or player has the permission
                if (getSub(args[0]) == null) return true;

                if (!cmdA.permission().isEmpty() && !sender.hasPermission(cmdA.permission())) { // SubCommand's @Command has a permission and player don't have the permission
                    sender.sendMessage(TranslateManager.translateColors(configuration.NO_PERMISSION));
                    return true;
                }

                // SubCommand's @Command don't have a permission or player has the permission
               doExecution(sender,cmdA,cmd,subArgs,configuration);

            } else if (sender instanceof ConsoleCommandSender) {// sender is not a player but the console
                if (isPlayerOnly()) {
                    sender.sendMessage(TranslateManager.translateColors(Simpler.getMessageConfiguration().ONLY_PLAYER));
                    return true;
                } // command is not player only

                doExecution(sender, cmdA, cmd, subArgs, configuration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private void doExecution(@NotNull CommandSender sender, me.efekos.simpler.commands.Command cmdA, Class<? extends SubCommand> cmd, String[] subArgs, MessageConfiguration configuration) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        a:{

            if (cmdA.playerOnly()&&!(sender instanceof Player)) { // SubCommand's @Command is player only
                sender.sendMessage(TranslateManager.translateColors(configuration.ONLY_PLAYER));
                return;
            }

            // SubCommand's @Command is not player-only

            Constructor<? extends SubCommand> constructor = cmd.getConstructor(String.class);
            constructor.setAccessible(true);
            SubCommand instance = constructor.newInstance(cmdA.name());

            if (subArgs.length == 0 && instance.getSyntax().getArguments().stream().anyMatch(argument -> argument.getPriority() == ArgumentPriority.REQUIRED)) {
                sender.sendMessage(TranslateManager.translateColors(configuration.USAGE.replace("%usage%", getUsage())));
                break a;
            }

            for (int i = 0; i < instance.getSyntax().getArguments().size(); i++) {
                Argument arg = instance.getSyntax().getArguments().get(i);
                if (arg.getPriority() == ArgumentPriority.REQUIRED) {
                    if ((subArgs.length - 1) < i) {
                        sender.sendMessage(TranslateManager.translateColors(configuration.USAGE.replace("%usage%", getUsage())));
                        break a;
                    }

                    if ((subArgs[i] == null)) {
                        sender.sendMessage(TranslateManager.translateColors(configuration.USAGE.replace("%usage%", getUsage())));
                        break a;
                    }
                }
                if (!(subArgs.length - 1 < i) && subArgs[i] != null) {
                    ArgumentHandleResult result = arg.handleCorrection(subArgs[i]);
                    if (!result.isPassed()) {
                        sender.sendMessage(TranslateManager.translateColors(configuration.USAGE.replace("%usage%", getUsage()).replace("%reason%", result.hasReason() ? Objects.requireNonNull(result.getReason()) : "")));
                        break a;
                    }
                }
            }

            if(sender instanceof Player) instance.onPlayerUse((Player) sender, subArgs);
            else instance.onConsoleUse((ConsoleCommandSender) sender,subArgs);

        }
    }

    /**
     * Returns whether this command has a permission.
     *
     * @return Whether this command has a permission.
     */
    public boolean hasPermission() {
        me.efekos.simpler.commands.Command command = this.getClass().getAnnotation(me.efekos.simpler.commands.Command.class);
        if (command != null) return command.permission() != null;
        else return false;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (sender instanceof Player p) {

            if (hasPermission() && !p.hasPermission(Objects.requireNonNull(getPermission()))) return new ArrayList<>();

            if (args.length == 1) {
                ArrayList<String> cmdNames = new ArrayList<>();
                getSubs().forEach(sub -> {
                    try {
                        SubCommand cmd = sub.getConstructor(String.class).newInstance(args[0]);
                        if (cmd.hasPermission() && p.hasPermission(Objects.requireNonNull(cmd.getPermission())))
                            cmdNames.add(cmd.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                cmdNames.add("help");
                cmdNames.sort(String::compareTo);
                return cmdNames.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());

            } else if (args.length > 1) {
                Class<? extends SubCommand> sub = getSub(args[0]);
                if (sub == null) return new ArrayList<>();

                try {
                    return sub.getConstructor(String.class).newInstance(args[0]).tabComplete(sender, alias, Arrays.copyOfRange(args, 1, args.length));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<>();
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        return tabComplete(sender, alias, args);
    }
}
