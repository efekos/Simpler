/*
 * Copyright (c) 2023 efekos
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

import me.efekos.simpler.commands.syntax.Argument;
import me.efekos.simpler.commands.syntax.ArgumentPriority;
import me.efekos.simpler.commands.syntax.Syntax;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Used for base commands like /feed, /god etc.
 * Requires {@link me.efekos.simpler.annotations.Command} to be used in {@link me.efekos.simpler.commands.CommandManager}
 */
public abstract class BaseCommand extends Command {

    /**
     * Creates an instance of this command.
     * @param name Name of the command.
     */
    public BaseCommand(@NotNull String name) {
        super(name);
    }

    /**
     * Creates an instance of this command.
     * @param name Name of the command.
     * @param description Description of the command.
     * @param usageMessage Usage message of the command.
     * @param aliases Aliases for the command.
     */
    public BaseCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * Grabs the value of {@link me.efekos.simpler.annotations.Command#name()} and returns it.
     * @return Command name as a {@link String}.
     */
    @Override
    @NotNull
    public String getName() {
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.name();
        return super.getName();
    }

    /**
     * Grabs the value of {@link me.efekos.simpler.annotations.Command#permission()} ()} and returns it.
     * @return Permission this command needs to be executed as String, null if this command does not need any permission.
     */
    @Override
    @Nullable
    public String getPermission() {
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.permission();
        return super.getPermission();
    }

    /**
     * Grabs the value of {@link me.efekos.simpler.annotations.Command#description()} and returns it.
     * @return A brief description of this command
     */
    @Override
    @NotNull
    public String getDescription() {
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.description();
        return super.getDescription();
    }


    /**
     * Used for handling tab completion, making examples and providing valid usages for this command. You can create your own arguments with a class extends {@link Argument}. This is really helpful at making sure your arguments are good.
     * @return A Syntax class for this command.
     */
    @NotNull
    public abstract Syntax getSyntax();

    /**
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
    @NotNull
    @Override
    public String getUsage() {
        Stream<String> s = getSyntax().getArguments().stream().map(Argument::toString);
        StringBuilder builder = new StringBuilder();
        s.forEach(s1 -> {
            builder.append(" ");
            builder.append(s1);
        });

        return "/"+getName()+builder;
    }

    /**
     * Grabs the value of {@link me.efekos.simpler.annotations.Command#playerOnly()} and returns it.
     * @return Is this command can be used by something that is not player?
     */
    public boolean isPlayerOnly(){
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.playerOnly();
        else return false;
    }

    /**
     * Fires when a player executes this command.
     * @param player Player that sent the command.
     * @param args The args given by sender.
     */
    public abstract void onPlayerUse(Player player, String[] args);

    /**
     * Fires when the console executes this command
     * @param sender The console
     * @param args Args given by sender
     */
    public abstract void onConsoleUse(ConsoleCommandSender sender, String[]args);

    /**
     * Executes the command, returning its success
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     * @return true if the command was successful, otherwise false
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args){
        a:{
            if(sender instanceof Player){ //sender is a player
                me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
                Player p = (Player) sender;

                if(!command.permission().equals("")&&!p.hasPermission(command.permission())){ // @Command has a permission and player don't have the permission

                    p.sendMessage(ChatColor.RED+"You do not have permission to do that!");

                } else { // @Command don't have a permission or player has the permission

                    for (int i = 0; i < getSyntax().getArguments().size(); i++) {
                        Argument arg = getSyntax().getArguments().get(i);
                        if((args.length-1)<i && arg.getPriority()== ArgumentPriority.REQUIRED){
                            p.sendMessage(ChatColor.RED+"Invalid usage. Use " +getUsage());
                            break a;
                        }

                        if(args[i]==null && arg.getPriority()==ArgumentPriority.REQUIRED){
                            sender.sendMessage(ChatColor.RED+"Invalid usage. Use" + getUsage());
                            break a;
                        }

                        if(!arg.handleCorrection(args[i])){
                            p.sendMessage(ChatColor.RED+"Invalid usage. Use " +getUsage());
                            break a;
                        }
                    }

                    onPlayerUse((Player) sender, args);
                }

            } else if(sender instanceof ConsoleCommandSender){// sender is not a player but the console
                if(!isPlayerOnly()){ // command is not player only


                    for (int i = 0; i < getSyntax().getArguments().size(); i++) {
                        Argument arg = getSyntax().getArguments().get(i);
                        if((args.length-1)<i && arg.getPriority()== ArgumentPriority.REQUIRED){
                            sender.sendMessage(ChatColor.RED+"Invalid usage. Use " +getUsage());
                            break a;
                        }

                        if(!arg.handleCorrection(args[i])){
                            sender.sendMessage(ChatColor.RED+"Invalid usage. Use " +getUsage());
                            break a;
                        }
                    }

                    onConsoleUse((ConsoleCommandSender) sender,args);
                } else { // command is player only
                    sender.sendMessage(ChatColor.RED+"This command only can be used by a player!");
                }
            }
        }

        return true;
    }

    /**
     * Executed on tab completion for this command, returning a list of
     * options the player can tab through.
     *
     * @param sender Source object which is executing this command
     * @param alias  the alias being used
     * @param args   All arguments passed to the command, split via ' '
     * @return a list of tab-completions for the specified arguments. This
     * will never be null. List may be immutable.
     * @throws IllegalArgumentException if sender, alias, or args is null
     */
    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if(sender instanceof Player){
            Player p = (Player) sender;
            List<Argument> arguments = getSyntax().getArguments();

            if(!p.hasPermission(getPermission()))return new ArrayList<>();

            int num = args.length-1;

            if(num<arguments.size()&&arguments.get(num)!=null){
                Argument arg = arguments.get(num);
                return arg.getList(p,args[num]);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Executed on tab completion for this command, returning a list of
     * options the player can tab through.
     *
     * @param sender   Source object which is executing this command
     * @param alias    the alias being used
     * @param args     All arguments passed to the command, split via ' '
     * @param location The position looked at by the sender, or null if none
     * @return a list of tab-completions for the specified arguments. This
     * will never be null. List may be immutable.
     * @throws IllegalArgumentException if sender, alias, or args is null
     */
    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        if(sender instanceof Player){
            Player p = (Player) sender;
            List<Argument> arguments = getSyntax().getArguments();

            if(!p.hasPermission(getPermission()))return new ArrayList<>();

            int num = args.length-1;

            if(num<arguments.size()&&arguments.get(num)!=null){
                Argument arg = arguments.get(num);
                return arg.getList(p,args[num]);
            }
        }
        return new ArrayList<>();
    }
}
