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
import me.efekos.simpler.commands.node.ArgumentNode;
import me.efekos.simpler.commands.node.CommandExecutive;
import me.efekos.simpler.commands.node.CommandNode;
import me.efekos.simpler.translation.TranslateManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for the actual command that handles a {@link TreeCommand}. Only can be created using {@link CommandManager#registerCommandTree(JavaPlugin, CommandTree)}.
 */
class TreeCommand extends Command {
    /**
     * Main {@link CommandTree} that was provided by the user.
     */
    private final CommandTree base;

    /**
     * Creates a new tree command.
     *
     * @param base {@link CommandTree} that this {@link TreeCommand} will handle.
     */
    TreeCommand(@NotNull CommandTree base) {
        super(base.getBaseName());
        this.base = base;
    }

    /**
     * execute command.
     *
     * @param commandSender Source object which is executing this command
     * @param s             The alias of the command used
     * @param args          All arguments passed to the command, split via ' '
     * @return {@code true}.
     */
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] args) {

        if (!commandSender.hasPermission(base.getBasePermission())) {
            commandSender.sendMessage(TranslateManager.translateColors(Simpler.getMessageConfiguration().NO_PERMISSION));
            return true;
        }

        List<@NotNull String> argList = Arrays.stream(args).collect(Collectors.toList());
        List<String> argumentList = new ArrayList<>();

        List<CommandNode> children = base.getChildren();
        CommandExecutive finalExecutiveFound = null;
        while (!argList.isEmpty()) {
            String arg = argList.get(0);

            Optional<CommandNode> first = children.stream().filter(commandNode -> {
                if (commandNode instanceof ArgumentNode) return ((ArgumentNode) commandNode).isCorrect(arg);
                else return commandNode.suggest(commandSender, Arrays.asList(args)).contains(arg);
            }).findFirst();
            if (first.isPresent()) {
                CommandNode node = first.get();

                if (node instanceof ArgumentNode) argumentList.add(arg);

                if (argList.size() != 1) {
                    children = node.getChildren();
                } else {
                    if (commandSender.hasPermission(node.getPermission()))
                        finalExecutiveFound = commandSender instanceof ConsoleCommandSender ? node.getConsoleExecutive() : node.getExecutive();
                    else
                        finalExecutiveFound = (context -> context.getSender().sendMessage(TranslateManager.translateColors(Simpler.getMessageConfiguration().NO_PERMISSION)));
                }
            } else finalExecutiveFound = null;
            argList.remove(0);
        }


        if (Objects.nonNull(finalExecutiveFound))
            finalExecutiveFound.onExecute(new CommandExecuteContext(commandSender, argumentList));

        return true;
    }

    /**
     * tab complete command
     *
     * @param sender Source object which is executing this command
     * @param alias  the alias being used
     * @param args   All arguments passed to the command, split via ' '
     * @return a list.
     * @throws IllegalArgumentException when something unexpected happens.
     */
    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }

    /**
     * Returns aliases.
     *
     * @return aliases.
     */
    @NotNull
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }


    /**
     * actual tab-complete method.
     *
     * @param sender   Source object which is executing this command
     * @param alias    the alias being used
     * @param args     All arguments passed to the command, split via ' '
     * @param location The position looked at by the sender, or null if none
     * @return a list of tab completions.
     * @throws IllegalArgumentException when something unexpected happens.
     */
    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        if (!sender.hasPermission(base.getBasePermission())) {
            return Collections.emptyList();
        }

        List<CommandNode> children = base.getChildren();
        List<String> finalListFound = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];

            Optional<CommandNode> first = children.stream().filter(commandNode -> {
                if (commandNode instanceof ArgumentNode) return ((ArgumentNode) commandNode).isCorrect(arg);
                else return commandNode.suggest(sender, Arrays.asList(args)).contains(arg);
            }).findFirst();

            if (first.isPresent()) children = first.get().getChildren();
            else children = Collections.emptyList();
        }

        for (CommandNode child : children) {
            finalListFound.addAll(child.suggest(sender, Arrays.asList(args)));
        }


        return finalListFound;
    }

    /**
     * Returns the description of his command.
     *
     * @return Description.
     */
    @NotNull
    @Override
    public String getDescription() {
        return base.getBaseDescription();
    }
}
