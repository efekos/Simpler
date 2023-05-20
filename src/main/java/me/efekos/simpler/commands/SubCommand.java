package me.efekos.simpler.commands;

import me.efekos.simpler.commands.syntax.Argument;
import org.jetbrains.annotations.NotNull;

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
        Stream<String> s = getSyntax().getArguments().stream().map(Argument::toString);
        StringBuilder builder = new StringBuilder();
        s.forEach(s1 -> {
            builder.append(" ");
            builder.append(s1);
        });

        return "/"+getParent().getName()+getName()+builder;
    }
}
