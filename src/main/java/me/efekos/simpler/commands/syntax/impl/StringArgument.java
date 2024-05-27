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

import me.efekos.simpler.Simpler;
import me.efekos.simpler.commands.syntax.Argument;
import me.efekos.simpler.commands.syntax.ArgumentHandleResult;
import me.efekos.simpler.commands.syntax.ArgumentPriority;
import me.efekos.simpler.config.MessageConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * An example extension of a custom {@link Argument}: This argument can be used for any kind of string arguments.
 */
public class StringArgument extends Argument {
    /**
     * Placeholder of this argument.
     */
    private final String holder;
    /**
     * Priority of this argument.
     */
    private final ArgumentPriority priority;
    /**
     * Minimum length of a value for this argument can have.
     */
    private final int minLength;
    /**
     * Maximum length of a value for this argument can have.
     */
    private final int maxLength;

    /**
     * Creates a new instance of {@link StringArgument}.
     *
     * @param holder    Placeholder of the argument.
     * @param priority  Priority of the argument.
     * @param minLength Minimum length for the value {@link String}.
     * @param maxLength Maximum length for the value {@link String}.
     */
    public StringArgument(String holder, ArgumentPriority priority, int minLength, int maxLength) {
        this.holder = holder;
        this.priority = priority;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlaceHolder() {
        return holder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getList(Player player, String current) {
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArgumentPriority getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArgumentHandleResult handleCorrection(String given) {
        int length = given.length();
        MessageConfiguration configuration = Simpler.getMessageConfiguration();
        if (length < minLength)
            return ArgumentHandleResult.fail(configuration.STR_ARG_SHT.replace("%given%", given).replace("%min%", minLength + ""));
        if (length > maxLength)
            return ArgumentHandleResult.fail(configuration.STR_ARG_LNG.replace("%given%", given).replace("%max%", maxLength + ""));
        return ArgumentHandleResult.success();
    }
}
