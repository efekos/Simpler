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

package me.efekos.simpler.commands.syntax;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a syntax for a command. Every {@link Argument} is being used at tab completion and handling argument validness. A {@link Syntax} can be used to generate usage for the command. Required for {@link me.efekos.simpler.commands.BaseCommand} and {@link me.efekos.simpler.commands.SubCommand}s.
 */
public class Syntax {
    /**
     * A list of the arguments list syntax will have.
     */
    private final List<Argument> arguments;

    /**
     * Represents a syntax for a command. Every {@link Argument} gets used at tab completion and handling argument validness. A {@link Syntax} can be used to generate usage for the command. Required for {@link me.efekos.simpler.commands.BaseCommand} and {@link me.efekos.simpler.commands.SubCommand}s.
     */
    public Syntax() {
        arguments = new ArrayList<>();
    }

    /**
     * Returns a list of the {@link Argument}s on this syntax.
     * @return List of the {@link Argument}s.
     */
    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * Adds an argument to the list.
     * @param arg Argument to add
     * @return The {@link Syntax} itself, making it able to repeat this method for multiple arguments.
     */
    public Syntax withArgument(Argument arg){
        arguments.add(arg);
        return this;
    }
}
