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

import me.efekos.simpler.commands.node.impl.LabelNode;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
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
     * Parent of this node.
     */
    private CommandNode parent;

    /**
     * Executive that this node will run when someone runs this command.
     */
    private CommandExecutive executive;

    /**
     * Executive that this node will run when the console runs this command.
     */
    private CommandExecutive consoleExecutive;

    /**
     * Description of this node
     */
    private String description;
    /**
     * Permission required to execute {@link CommandNode#executive}.
     */
    private String permission = "";

    /**
     * Creates a new {@link CommandNode}.
     *
     * @param children Any amount of child nodes if you would like to add now. You can still add child nodes using
     *                 {@link CommandNode#addChild(CommandNode)} later.
     */
    public CommandNode(CommandNode... children) {
        for (CommandNode child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }

    /**
     * Returns the description of this node
     *
     * @return Description of this node
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this node. You don't really have to set a description if this node won't have a
     * {@link CommandExecutive}.
     *
     * @param description Description of this node.
     * @return Itself.
     */
    public CommandNode setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns a list of the child nodes that this node contain.
     *
     * @return Children of this node.
     */
    public List<CommandNode> getChildren() {
        return children;
    }

    /**
     * Returns a {@link CommandExecutive} to run when this node is executed by someone.
     *
     * @return Executive of this node.
     */
    public CommandExecutive getExecutive() {
        return executive;
    }

    /**
     * Changes the executive of this node.
     *
     * @param executive New executive to use.
     * @return CommandNode itself.
     */
    public CommandNode setExecutive(CommandExecutive executive) {
        this.executive = executive;
        return this;
    }

    /**
     * Returns a {@link CommandExecutive} to run when the console executes this node.
     *
     * @return Console-special executive of this node.
     */
    public CommandExecutive getConsoleExecutive() {
        return consoleExecutive;
    }

    /**
     * Changes the console-special executive of this node.
     *
     * @param consoleExecutive New executive to use
     * @return CommandNode itself.
     */
    public CommandNode setConsoleExecutive(CommandExecutive consoleExecutive) {
        this.consoleExecutive = consoleExecutive;
        return this;
    }

    /**
     * Returns the parent of this node.
     *
     * @return Parent of this node.
     */
    public CommandNode getParent() {
        return parent;
    }

    /**
     * Changes the parent of this node. Please note that parents are only used for display purposes, and does not affect
     * the command tree in any way. It is not recommended to change the parent of any node.
     *
     * @param parent New parent of this node.
     * @return {@link CommandNode} itself.
     */
    public CommandNode setParent(CommandNode parent) {
        this.parent = parent;
        return this;
    }

    /**
     * Adds the given {@link CommandNode} to the children of this class and returns it.
     *
     * @param node New child node to add to this node.
     * @return {@link CommandNode} itself.
     */
    public CommandNode addChild(CommandNode node) {
        node.setParent(this);
        children.add(node);
        return this;
    }

    /**
     * Adds the given string as a {@link LabelNode} to the children of this node and returns it.
     *
     * @param label New label node to add to this one.
     * @return {@link CommandNode} itself.
     */
    public CommandNode addChild(String label) {
        return addChild(new LabelNode(label).setParent(this));
    }

    /**
     * Returns the permission that this {@link CommandNode} requires to run the executive of this node.
     *
     * @return Permission of this node.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Changes the permission of this {@link CommandNode} and returns it.
     *
     * @param permission Permission that this {@link CommandNode} will require to run its executive.
     * @return {@link CommandNode} itself.
     */
    public CommandNode setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * Provides a list that can be added to the final list of tab completion.
     *
     * @param sender Sender that needs a tab completion. Probably a {@link org.bukkit.entity.Player} since console doesn't
     *               need a tab completion.
     * @param args   List of the other arguments given by the sender.
     * @return A list for tab completion.
     */
    public abstract List<String> suggest(CommandSender sender, List<String> args);
}
