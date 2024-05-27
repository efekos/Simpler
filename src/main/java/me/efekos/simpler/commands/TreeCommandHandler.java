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

import org.bukkit.plugin.java.JavaPlugin;

/**
 * A class that hols a {@link CommandTree} for {@link CommandManager} to use. When using {@link me.efekos.simpler.Simpler#registerCommands(JavaPlugin)},
 * you need to make a class the extends this class and is annotated with {@link CommandTreeHandler} to make Simpler notice
 * your {@link TreeCommandHandler} class.
 */
public abstract class TreeCommandHandler {

    /**
     * Creates a new handler.
     */
    public TreeCommandHandler() {
    }

    /**
     * Returns a command tree that will be registered.
     *
     * @return A {@link CommandTree} instance.
     */
    public abstract CommandTree getTree();
}
