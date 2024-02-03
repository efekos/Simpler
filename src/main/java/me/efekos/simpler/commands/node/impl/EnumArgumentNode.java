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

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * An example of custom {@link ArgumentNode}s. Allows player to choose an argument from the enumerator given.
 */
public class EnumArgumentNode extends ListArgumentNode {

    /**
     * Constructs a new node.
     * @param enumClass An {@code enum} class to generate a list of valid strings of this node.
     */
    public EnumArgumentNode(Class<? extends Enum<?>> enumClass) {
        super(Arrays.stream(enumClass.getEnumConstants()).map(anEnum -> anEnum.name().toLowerCase(Locale.ENGLISH)).toArray(String[]::new));
    }

    /**
     * Constructs a new node.
     * @param enumClass An {@code enum} class to generate a list of valid strings of this node.
     * @param children Children of this node
     */
    public EnumArgumentNode(Class<? extends Enum<?>> enumClass, CommandNode... children) {
        super(Arrays.stream(enumClass.getEnumConstants()).map(anEnum -> anEnum.name().toLowerCase(Locale.ENGLISH)).collect(Collectors.toList()), children);
    }
}
