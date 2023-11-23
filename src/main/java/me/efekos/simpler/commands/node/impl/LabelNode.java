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

import me.efekos.simpler.commands.node.CommandNode;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * An example of custom {@link CommandNode}. A node that holds a simple label. {@link CommandNode#addChild(String)}
 * generates a {@link LabelNode}.
 */
public class LabelNode extends CommandNode {

    /**
     * Main label that this node holds as a sub command
     */
    private final String label;

    /**
     * Creates a new label node.
     * @param label Main label that this node will hold.
     */
    public LabelNode(String label) {
        this.label = label;
    }

    /**
     * Returns the label that this node is holding.
     * @return Label of this node.
     */
    public String getLabel() {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> suggest(CommandSender sender, List<String> args) {
        return Collections.singletonList(label);
    }
}
