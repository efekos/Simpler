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

package me.efekos.simpler.commands.syntax.impl;

import me.efekos.simpler.commands.syntax.Argument;
import me.efekos.simpler.commands.syntax.ArgumentPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An example extension of a custom {@link Argument}s: This argument is an enum you give at constructor.
 */
public class EnumArgument extends ListArgument {
    /**
     * Creates an instance of {@link ListArgument}.
     *
     * @param holder    Placeholder of this argument.
     * @param priority  Priority of this argument.
     * @param enumValue An enumerator.
     */
    public EnumArgument(String holder, ArgumentPriority priority, Class<? extends Enum<?>> enumValue) {
        super(holder, priority, makeList(enumValue));
    }

    /**
     * Generated an argument list using the enum class given.
     *
     * @param enumValue An enum to generate an array.
     * @return An array of {@link String}s, every {@link String} being lower-cased.
     */
    private static String[] makeList(Class<? extends Enum<?>> enumValue) {
        Enum<?>[] constants = enumValue.getEnumConstants();
        List<String> things = new ArrayList<>();

        for (Enum<?> constant : constants) {
            things.add(constant.name().toLowerCase(Locale.ENGLISH).replaceAll(" ", "_"));
        }

        return things.toArray(String[]::new);
    }
}
