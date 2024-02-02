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

import me.efekos.simpler.annotations.Command;
import me.efekos.simpler.commands.syntax.Argument;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

/**
 * Used to make sub commands for {@link CoreCommand}s. Extend this class to make your own sub commands. Must be annotated with {@link Command} to work properly.
 */
public abstract class SubCommand extends BaseCommand{
    /**
     * Creates an instance of this command.
     * @param name Name of the command.
     */
    public SubCommand(@NotNull String name) {
        super(name);
    }

    /**
     * Creates an instance of this command.
     * @param name Name of the command.
     * @param description Description of the command.
     * @param usageMessage Usage message of the command.
     * @param aliases Aliases for the command.
     */
    public SubCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * Returns the parent of this {@link SubCommand}.
     * @return Class of the {@link CoreCommand} that this {@link SubCommand} should belong to.
     */
    public abstract Class<? extends CoreCommand> getParent();

    /**
     * Generates a usage using the {@link #getSyntax()}.
     * @return Usage syntax of this command in {@link String}.
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

            return "/"+getParent().getAnnotation(Command.class).name()+" "+getName()+builder;
        } catch (Exception e){
           return "";
        }
    }
}
