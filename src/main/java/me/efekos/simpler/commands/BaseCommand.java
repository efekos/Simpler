package me.efekos.simpler.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand extends Command {

    public BaseCommand(@NotNull String name) {
        super(name);
    }

    public BaseCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * @return Command name as string.
     */
    @Override
    @NotNull
    public String getName() {
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.name();
        return super.getName();
    }

    /**
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
     * @return Syntax of this command.
     */
    @NotNull
    public abstract String getSyntax();

    /**
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
    @NotNull
    @Override
    public String getUsage() {
        return "/"+getSyntax();
    }

    /**
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
        if(sender instanceof Player){ //sender is a player
            me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
            Player p = (Player) sender;

            if(command.permission()!=null&&!p.hasPermission(command.permission())){ // @Command has a permission and player don't have the permission

                p.sendMessage(ChatColor.RED+"You do not have permission to do that!");

            } else { // @Command don't have a permission or player has the permission
                onPlayerUse((Player) sender, args);
            }

        } else if(sender instanceof ConsoleCommandSender){// sender is not a player but the console
            if(!isPlayerOnly()){ // command is not player only
                onConsoleUse((ConsoleCommandSender) sender,args);
            } else { // command is player only
                sender.sendMessage(ChatColor.RED+"This command only can be used by a player!");
            }
        }

        return true;
    }

    /**
     * Used to get arguments for this command.
     * @param player The player typing this command.
     * @param args List of the arguments player gave us so far.
     * @return A list of new arguments to suggest player.
     */
    public abstract List<String> getArgs(Player player,String[] args);

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
            return getArgs((Player) sender,args);
        } else {
            return new ArrayList<>();
        }
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
            return getArgs((Player) sender,args);
        } else {
            return new ArrayList<>();
        }
    }
}
