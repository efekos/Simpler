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
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * An example of custom {@link ArgumentNode}s. Represents a number argument that can have numbers at the bounds of
 * {@link Double}. You can also put a minimum and maximum value.
 */
public class DoubleArgumentNode extends ArgumentNode {
    /**
     * Minimum value that a player can give for this argument.
     */
    private double min = Double.MIN_VALUE;
    /**
     * Maximum value that a player can give for this argument.
     */
    private double max = Double.MAX_VALUE;

    /**
     * Creates a new node for use.
     * @param min Minimum number for this argument.
     * @param max Maximum number for this argument.
     * @param children Any child to add to this node if you would like to. You can still add children using
     *                 {@link DoubleArgumentNode#addChild(CommandNode)} later.
     */
    public DoubleArgumentNode(double min, double max, CommandNode... children) {
        super(children);
        this.min = min;
        this.max = max;
    }

    /**
     * Creates a new node for use.
     * @param children Any child to add to this node if you would like to. You can still add children using
     *                 {@link DoubleArgumentNode#addChild(CommandNode)} later.
     */
    public DoubleArgumentNode(CommandNode... children) {
        super(children);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCorrect(String given) {
       try {
           double i = Double.parseDouble(given);
           return min<i && i<max;
       } catch (Exception ignored){
           return false;
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> suggest(CommandSender sender, List<String> args) {
        return Collections.emptyList();
    }
}
