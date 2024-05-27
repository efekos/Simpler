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
import java.util.Arrays;
import java.util.List;

/**
 * An example extension of a custom {@link Argument}s: This argument can be one of the values you give at constructor.
 */
public class ListArgument extends Argument {
    /**
     * Placeholder of this argument.
     */
    private final String holder;
    /**
     * Priority of this argument.
     */
    private final ArgumentPriority priority;
    /**
     * A list of the values this argument can have.
     */
    private final List<String> values = new ArrayList<>();

    /**
     * Creates an instance of {@link ListArgument}.
     *
     * @param holder   Placeholder of this argument.
     * @param priority Priority of this argument.
     * @param values   Values this argument can approve.
     */
    public ListArgument(String holder, ArgumentPriority priority, String... values) {
        this.holder = holder;
        this.priority = priority;
        this.values.addAll(Arrays.asList(values));
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
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArgumentHandleResult handleCorrection(String given) {
        MessageConfiguration configuration = Simpler.getMessageConfiguration();
        if (values.contains(given)) return ArgumentHandleResult.success();
        else return ArgumentHandleResult.fail(configuration.LIST_ARG_NIL.replace("%given%", given));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArgumentPriority getPriority() {
        return priority;
    }
}
