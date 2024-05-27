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
 * An example extension of custom {@link Argument}s: This argument can be any integer, or an integer in a range.
 */
public class IntegerArgument extends Argument {
    /**
     * Placeholder of this argument.
     */
    private final String holder;
    /**
     * Priority of this argument.
     */
    private final ArgumentPriority priority;
    /**
     * Minimum value for this argument.
     */
    private final int min;
    /**
     * Maximum value for this argument.
     */
    private final int max;

    /**
     * Crates an instance of {@link IntegerArgument}.
     *
     * @param holder   Placeholder of the argument.
     * @param priority Priority of the argument.
     * @param min      Minimum value.
     * @param max      Maximum value.
     */
    public IntegerArgument(String holder, ArgumentPriority priority, int min, int max) {
        this.holder = holder;
        this.min = min;
        this.max = max;
        this.priority = priority;
    }

    /**
     * Crates an instance of {@link IntegerArgument}.
     *
     * @param holder   Placeholder of the argument.
     * @param priority Priority of the argument.
     */
    public IntegerArgument(String holder, ArgumentPriority priority) {
        this.holder = holder;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
        this.priority = priority;
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
        MessageConfiguration configuration = Simpler.getMessageConfiguration();

        try {
            long i = Long.parseLong(given);
            if (i < Integer.MIN_VALUE)
                return ArgumentHandleResult.fail(configuration.NUM_ARG_IMIN.replace("%given%", given));
            if (i > Integer.MAX_VALUE)
                return ArgumentHandleResult.fail(configuration.NUM_ARG_IMAX.replace("%given%", given));
            if (i < min)
                return ArgumentHandleResult.fail(configuration.NUM_ARG_MIN.replace("%given%", given).replace("%minValue%", min + ""));
            if (i > max)
                return ArgumentHandleResult.fail(configuration.NUM_ARG_MAX.replace("%given%", given).replace("%maxValue%", max + ""));
        } catch (Exception ignored) {
            return ArgumentHandleResult.fail(configuration.NUM_ARG_NAN.replace("%given%", given));
        }
        return ArgumentHandleResult.success();
    }
}
