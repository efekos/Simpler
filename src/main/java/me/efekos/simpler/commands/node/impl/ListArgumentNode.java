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

package me.efekos.simpler.commands.node.impl;

import me.efekos.simpler.commands.node.ArgumentNode;
import me.efekos.simpler.commands.node.CommandNode;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

/**
 * An example of custom {@link ArgumentNode}s. Allows player to choose an argument from the list given.
 */
public class ListArgumentNode extends ArgumentNode {

    /**
     * Main list this node uses.
     */
    private List<String> list;

    /**
     * Creates a new node.
     * @param list Values that this argument can have.
     */
    public ListArgumentNode(String... list) {
        super(new CommandNode[]{});
        this.list = Arrays.asList(list);
    }

    /**
     * Creates a new node.
     * @param list List of the values that this argument can have.
     * @param children Any child node to add to this node if you would like to. You can use {@link ListArgumentNode#addChild(CommandNode)}
     *                 later.
     */
    public ListArgumentNode(List<String> list, CommandNode... children) {
        super(children);
        this.list = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCorrect(String given) {
     return list.contains(given);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> suggest(CommandSender sender, List<String> args) {
        return list;
    }
}
