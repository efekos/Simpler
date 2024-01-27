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

package me.efekos.simpler.commands.node;

/**
 * Main that is used for making argument nodes. It is highly recommended to extend this class instead of {@link CommandNode}
 * if your node is an argument. Because this is the only type that Simpler understands about argument nodes.
 */
public abstract class ArgumentNode extends CommandNode {
    /**
     * Created a new {@link ArgumentNode} to be used in {@link me.efekos.simpler.commands.CommandTree}s.
     * @param children Children of this node if you would like to add any.
     */
    public ArgumentNode(CommandNode... children) {
        super(children);
    }

    /**
     * Checks for a given argument and ensures that the argument is correct.
     * @param given Argument that was given for this node.
     * @return Whether the argument is correct or not.
     */
    public abstract boolean isCorrect(String given);
}
