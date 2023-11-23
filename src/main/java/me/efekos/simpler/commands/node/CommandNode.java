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

package me.efekos.simpler.commands.node;

import me.efekos.simpler.commands.node.impl.LabelNode;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class for nodes of {@link me.efekos.simpler.commands.CommandTree}s. Every type of node should extend this class.
 */
public abstract class CommandNode {
    /**
     * List of the child nodes that this node contain. Used for command executing and tab completion.
     */
    private final List<CommandNode> children = new ArrayList<>();
    /**
     * Executive that this node will run when someone runs this command.
     */
    private CommandExecutive executive;
    /**
     * Permission required to execute {@link CommandNode#executive}.
     */
    private String permission = "";

    /**
     * Returns a list of the child nodes that this node contain.
     * @return Children of this node.
     */
    public List<CommandNode> getChildren() {
        return children;
    }

    /**
     * Returns a {@link CommandExecutive} to run when this node is executed by someone.
     * @return Executive of this node.
     */
    public CommandExecutive getExecutive() {
        return executive;
    }

    /**
     * Changes the executive of this node.
     * @param executive New executive to use.
     * @return CommandNode itself.
     */
    public CommandNode setExecutive(CommandExecutive executive) {
        this.executive = executive;
        return this;
    }

    /**
     * Creates a new {@link CommandNode}.
     * @param children Any amount of child nodes if you would like to add now. You can still add child nodes using
     *                 {@link CommandNode#addChild(CommandNode)} later.
     */
    public CommandNode(CommandNode... children) {
        this.children.addAll(Arrays.asList(children));
    }

    /**
     * Adds the given {@link CommandNode} to the children of this class and returns it.
     * @param node New child node to add to this node.
     * @return {@link CommandNode} itself.
     */
    public CommandNode addChild(CommandNode node){
        children.add(node);
        return this;
    }

    /**
     * Adds the given string as a {@link LabelNode} to the children of this node and returns it.
     * @param label New label node to add to this one.
     * @return {@link CommandNode} itself.
     */
    public CommandNode addChild(String label){
        return addChild(new LabelNode(label));
    }

    /**
     * Returns the permission that this {@link CommandNode} requires to run the executive of this node.
     * @return Permission of this node.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Changes the permission of this {@link CommandNode} and returns it.
     * @param permission Permission that this {@link CommandNode} will require to run its executive.
     * @return {@link CommandNode} itself.
     */
    public CommandNode setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * Provides a list that can be added to the final list of tab completion.
     * @param sender Sender that needs a tab completion. Probably a {@link org.bukkit.entity.Player} since console doesn't
     *               need a tab completion.
     * @param args List of the other arguments given by the sender.
     * @return A list for tab completion.
     */
    public abstract List<String> suggest(CommandSender sender, List<String> args);
}