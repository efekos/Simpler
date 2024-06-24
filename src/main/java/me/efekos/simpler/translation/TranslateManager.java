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

package me.efekos.simpler.translation;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static manager to translate colors and generate translation keys.
 */
public class TranslateManager {

    /**
     * The pattern used for detecting hex colors.
     */
    public static final Pattern hexColorsPattern = Pattern.compile("(&#[0-9a-fA-F]{6})");

    /**
     * Creates a new manager instance if you somehow need one.
     */
    public TranslateManager() {
    }

    /**
     * Generates a translation key for the {@link Material} given.
     *
     * @param material A material that {@link Material#isBlock()} or {@link Material#isItem()}.
     * @return A {@link TranslatableComponent} that will show name of the material. You can use it with {@link Spigot#sendMessage(BaseComponent...)}.
     */
    @NotNull
    public static TranslatableComponent translateMaterial(@NotNull Material material) {
        TranslatableComponent component = new TranslatableComponent();

        if (material.isBlock()) {
            component.setTranslate("block." + material.getKey().getNamespace() + "." + material.getKey().getKey());
        } else if (material.isItem()) {
            component.setTranslate("item." + material.getKey().getNamespace() + "." + material.getKey().getKey());
        }
        return component;
    }

    /**
     * Generates a translation key for the {@link EntityType} given.
     *
     * @param type Any entity type.
     * @return A {@link TranslatableComponent} that will show name of the entity. You can use it with {@link Spigot#sendMessage(BaseComponent...)}.
     */
    @NotNull
    public static TranslatableComponent translateEntity(@NotNull EntityType type) {
        return new TranslatableComponent("entity." + type.getKey().getNamespace() + "." + type.getKey().getKey());
    }

    /**
     * Generates a translation key for the {@link Enchantment} given.
     *
     * @param enchantment Any enchantment.
     * @return A {@link TranslatableComponent} that will show name of the enchantment. You can use it with {@link Spigot#sendMessage(BaseComponent...)}.
     */
    @NotNull
    public static TranslatableComponent translateEnchantment(@NotNull Enchantment enchantment) {
        return new TranslatableComponent("enchantment." + enchantment.getKey().getNamespace() + "." + enchantment.getKey().getKey());
    }

    /**
     * Generates a translation key for the {@link PotionEffectType} given.
     *
     * @param type One of the {@link PotionEffectType}s in game.
     * @return A {@link TranslatableComponent} that will show name of the potion effect. You can use it with {@link Spigot#sendMessage(BaseComponent...)}.
     */
    @NotNull
    public static TranslatableComponent translateEffect(@NotNull PotionEffectType type) {
        return new TranslatableComponent("effect." + type.getKey().getNamespace() + "." + type.getKey().getKey());
    }

    /**
     * Translates the colors in the text given. Supports default minecraft color codes that uses the character {@code &} and hex colors.
     *
     * @param message Message to translate colors.
     * @return Translated message.
     */
    @NotNull
    public static String translateColors(@NotNull String message) {
        String newMessage = message;

        Matcher matcher = hexColorsPattern.matcher(newMessage);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String code = matcher.group(1).substring(1);
            matcher.appendReplacement(buffer, ChatColor.of(code) + "");
        }
        matcher.appendTail(buffer);

        newMessage = ChatColor.translateAlternateColorCodes('&', buffer.toString());
        return newMessage;
    }
}