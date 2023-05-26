package me.efekos.simpler.commands;

import me.efekos.simpler.annotations.Command;
import me.efekos.simpler.commands.syntax.Argument;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public abstract class SubCommand extends BaseCommand{
    public SubCommand(@NotNull String name) {
        super(name);
    }

    public SubCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * @return Class of the {@link CoreCommand} that this {@link SubCommand} should belong to.
     */
    public abstract Class<? extends CoreCommand> getParent();

    /**
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
    @Override
    public @NotNull String getUsage() {
        try {

            Stream<String> s = getSyntax().getArguments().stream().map(Argument::toString);
            StringBuilder builder = new StringBuilder();
            s.forEach(s1 -> {
                builder.append(" ");
                builder.append(s1);
            });

            return "/"+getParent().getConstructor(String.class).newInstance(getParent().getAnnotation(Command.class).name()).getName()+" "+getName()+builder;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args, location);
    }
}
