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

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Creates a new context to use for running {@link me.efekos.simpler.commands.node.CommandExecutive}s of
 * {@link me.efekos.simpler.commands.node.CommandNode}s.
 * @param sender Sender that ran this command.
 * @param args List of the arguments sender gave.
 */
public record CommandExecuteContext(CommandSender sender, List<String> args) {

    /**
     * Checks what sender is, and returns whether the sender is a player or not.
     * @return {@code true} if sender is a player.
     */
    public boolean isSenderPlayer() {
        return sender instanceof Player;
    }


    /**
     * Checks what sender is, and returns whether the sender is the console or not.
     * @return {@code true} if sender is the console.
     */
    public boolean isSenderConsole() {
        return sender instanceof ConsoleCommandSender;
    }

    @Override
    public CommandSender sender() {
        return sender;
    }

    @Override
    public List<String> args() {
        return args;
    }
}
