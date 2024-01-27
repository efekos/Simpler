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

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Used for your custom arguments. Extend this class for any type of argument you want.
 */
public abstract class Argument {

    /**
     * Used for your custom arguments. Extend this class for any type of argument you want.
     */
    public Argument() {
    }

    /**
     * Returns a short placeholder to represent this argument. However, {@link #toString()} is more recommended than {@link #getPlaceHolder()} if you want to represent this argument as a {@link String}.
     * @return The placeholder of this argument. Placeholder is usually a one-word string that represents what this argument should be. For example: {@code <player>} argument should be the name of an {@link org.bukkit.OfflinePlayer}. We are able to understand this, because that argument's placeholder is "player", meaning we should enter someone's name there.
     */
    abstract public String getPlaceHolder();

    /**
     * Generates a list of suggestions about this argument.
     * @param player Player who needs suggestions for this argument.
     * @param current Current string player entered so far.
     * @return List of the strings player will see.
     */
    abstract public List<String> getList(Player player, String current);

    /**
     * Returns a priority about this argument.
     * @return Priority of this argument. You can make your argument an optional or required argument using this.
     */
    abstract public ArgumentPriority getPriority();

    /**
     * Makes sure that the argument player wrote is valid.
     * @param given The string that someone wrote as a value for this argument
     * @return Is the given argument valid?
     */
    abstract public ArgumentHandleResult handleCorrection(String given);

    /**
     * Returns a string representation of this {@link Argument}.
     * @return A string that represents this.
     */
    @Override
    public String toString() {
        String res = "";

        switch (getPriority()){
            case OPTIONAL:
                res = "["+getPlaceHolder()+"]";
                break;
            case REQUIRED:
                res = "<"+getPlaceHolder()+">";
                break;
        }
        return res;
    }
}
