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

package me.efekos.simpler.commands.node.impl;

import me.efekos.simpler.commands.node.ArgumentNode;
import me.efekos.simpler.commands.node.CommandNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An example of custom {@link ArgumentNode}s. Can have the name of any player online as a value.
 */
public class PlayerArgumentNode extends ArgumentNode {
    /**
     * Creates a new node.
     * @param children Any child nodes if you would like to add to this node. You can use {@link PlayerArgumentNode#addChild(CommandNode)}
     *                 for later.
     */
    public PlayerArgumentNode(CommandNode... children) {
        super(children);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCorrect(String given) {
        return Bukkit.getPlayer(given)!=null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> suggest(CommandSender sender, List<String> args) {
        return Bukkit.getServer().getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
