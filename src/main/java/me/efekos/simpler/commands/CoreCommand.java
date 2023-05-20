package me.efekos.simpler.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class CoreCommand extends Command {
    protected CoreCommand(@NotNull String name) {
        super(name);
    }

    protected CoreCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
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
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
    @NotNull
    @Override
    public String getUsage() {
        return "/"+getName()+" <sub> <args>";
    }

    @NotNull
    public abstract ArrayList<Class<? extends SubCommand>> getSubs();

    @Nullable
    public Class<? extends SubCommand> getSub(String name){
        Stream<Class<? extends SubCommand>> sameSubs = getSubs().stream().filter(aClass -> aClass.getAnnotation(me.efekos.simpler.annotations.Command.class).name().equals(name));
        if(sameSubs.count()==1){
            return (Class<? extends SubCommand>) sameSubs.toArray()[0];
        }
        return null;
    }

    /**
     * @return Is this command or subs of this command can be used by something that is not player?
     */
    public boolean isPlayerOnly(){
        me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
        if(command!=null)return command.playerOnly();
        else return false;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(sender instanceof Player){ //sender is a player
            me.efekos.simpler.annotations.Command command = this.getClass().getAnnotation(me.efekos.simpler.annotations.Command.class);
            Player p = (Player) sender;

            if(command.permission()!=null&&!p.hasPermission(command.permission())){ // @Command has a permission and player don't have the permission

                p.sendMessage(ChatColor.RED+"You do not have permission to do that!");

            } else { // @Command don't have a permission or player has the permission
                    if(getSub(args[0])!=null){
                        Class<? extends SubCommand> cmd = getSub(args[0]);
                        me.efekos.simpler.annotations.Command cmdA = cmd.getAnnotation(me.efekos.simpler.annotations.Command.class);


                    }
            }

        } else if(sender instanceof ConsoleCommandSender){// sender is not a player but the console
            if(!isPlayerOnly()){ // command is not player only
             //onconsoleuse
            } else { // command is player only
                sender.sendMessage(ChatColor.RED+"This command only can be used by a player!");
            }
        }

        return true;
    }
}
