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
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An example extension of a custom {@link Argument}: This argument can be an online {@link Player}..
 */
public class PlayerArgument extends Argument {
    /**
     * Priority of this argument.
     */
    private final ArgumentPriority priority;

    /**
     * Crates an instance of {@link PlayerArgument}.
     * @param priority Priority of the argument.
     */
    public PlayerArgument(ArgumentPriority priority) {
        this.priority = priority;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPlaceHolder() {
        return "player";
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getList(Player player, String current) {
        Collection<? extends Player> players = player.getServer().getOnlinePlayers();
        List<String> argumentResults = new ArrayList<>();

        players.stream().map(Player::getName).forEach(argumentResults::add);

        return argumentResults;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArgumentHandleResult handleCorrection(String given) {
        OfflinePlayer p = Bukkit.getServer().getPlayer(given);
        if(p == null) return ArgumentHandleResult.fail(Simpler.getMessageConfiguration().PLR_ARG_NAP.replace("%given%",given));

        return ArgumentHandleResult.success();
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArgumentPriority getPriority() {
        return priority;
    }
}
