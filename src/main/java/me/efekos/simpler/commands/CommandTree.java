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

import me.efekos.simpler.commands.node.CommandNode;
import me.efekos.simpler.commands.node.impl.LabelNode;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a command tree that can hold infinite amount of {@link CommandNode}s as children. You can use this to make
 * really complex commands with a bunch of sub commands. Use {@link CommandManager#registerCommandTree(JavaPlugin, CommandTree)}
 * to register a {@link CommandTree}.
 */
public class CommandTree {
    /**
     * List of the child nodes that this tree contain. Used for command executing and tab completion.
     */
    private List<CommandNode> children = new ArrayList<>();
    /**
     * Name of the actual command.
     */
    private final String baseName;
    /**
     * Description of the actual command.
     */
    private final String baseDescription;
    /**
     * Permission that the actual command needs. Please note that someone can't use any sub commands if they don't have
     * this permission.
     */
    private final String basePermission;

    /**
     * Creates a new command tree.
     * @param baseName Base name of the command.
     * @param baseDescription Base description of the command that will be used by spigot.
     * @param basePermission Base permission needed to run ANY sub command under this tree.
     * @param children {@link CommandNode}s to add if you would like to. You can use {@link #addChild(CommandNode)} too.
     */
    public CommandTree(String baseName,String baseDescription,String basePermission,CommandNode... children) {
        this.children = Arrays.asList(children);
        this.baseName = baseName;
        this.baseDescription = baseDescription;
        this.basePermission = basePermission;
    }

    /**
     * Creates a new command tree.
     * @param baseName Base name of the command.
     * @param baseDescription Base description of the command that will be used by spigot.
     * @param basePermission Base permission needed to run ANY sub command under this tree.
     */
    public CommandTree(String baseName,String baseDescription,String basePermission) {
        this.baseName = baseName;
        this.baseDescription = baseDescription;
        this.basePermission = basePermission;
    }

    /**
     * Returns the base description that will be used by bukkit.
     * @return Description of this tree.
     */
    public String getBaseDescription() {
        return baseDescription;
    }

    /**
     * Returns the base permission that is needed to run ANY command under this tree.
     * @return Permission of this tree.
     */
    public String getBasePermission() {
        return basePermission;
    }

    /**
     * Returns a list of the child nodes that this tree contain.
     * @return Children of this tree.
     */
    public List<CommandNode> getChildren() {
        return children;
    }

    /**
     * Returns the base name that will be the main command of this tree.
     * @return Base name.
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * Adds the given {@link CommandNode} to the children of this tree and returns it.
     * @param node New child node to add to this tree.
     * @return {@link CommandTree} itself.
     */
    public CommandTree addChild(CommandNode node){
        children.add(node);
        return this;
    }

    /**
     * Adds the given string as a {@link LabelNode} to the children of this tree and returns it.
     * @param label New label node to add to this one.
     * @return {@link CommandTree} itself.
     */
    public CommandTree addChild(String label){
        return addChild(new LabelNode(label));
    }
}
