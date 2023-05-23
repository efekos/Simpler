package me.efekos.simpler.commands.translation;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateManager {

    public static final Pattern hexColorsPattern = Pattern.compile("(&#[0-9a-fA-F]{6})");

    /**
     * Translates a material name.
     * @param material Material to get It's name translated
     * @return A TranslatableComponent for the material. Use {@link TranslatableComponent#toLegacyText()} with {@link Player#spigot()}>{@link Spigot#sendMessage(BaseComponent...)}
     */
    @NotNull
    public static TranslatableComponent translateMaterial(Material material){
        TranslatableComponent component = new TranslatableComponent();

        if(material.isBlock()){
            component.setTranslate("block.minecraft."+material.getKey().getKey());
        } else if(material.isItem()){
            component.setTranslate("block.minecraft."+material.getKey().getKey());
        }
        return component;
    }

    /**
     * Translates an entity name.
     * @param type Type of the entity to get It's name translated
     * @return A TranslatableComponent for the entity. Use {@link TranslatableComponent#toLegacyText()} with {@link Player#spigot()}>{@link Spigot#sendMessage(BaseComponent...)}
     */
    @NotNull
    public static TranslatableComponent translateEntity(@NotNull EntityType type){
        return new TranslatableComponent("entity.minecraft."+type.getKey().getKey());
    }

    /**
     * Translates an enchantment's name
     * @param enchantment An enchantment to translate It's name
     * @return A TranslatableComponent for the enchantment. Use {@link TranslatableComponent#toLegacyText()} with {@link Player#spigot()}>{@link Spigot#sendMessage(BaseComponent...)}
     */
    @NotNull
    public static TranslatableComponent translateEnchantment(@NotNull Enchantment enchantment){
        return new TranslatableComponent("enchantment.minecraft."+enchantment.getKey().getKey());
    }

    /**
     * Translates a Potion effect's name.
     * @param type A PotionEffectType to translate
     * @return A TranslatableComponent for the effect. Use {@link TranslatableComponent#toLegacyText()} with {@link Player#spigot()}>{@link Spigot#sendMessage(BaseComponent...)}
     */
    @NotNull
    public static TranslatableComponent translateEffect(@NotNull PotionEffectType type){
        return new TranslatableComponent("effect.minecraft"+type.getKey().getKey());
    }

    /**
     * Converts key to a {@link TranslatableComponent}
     * @param key Language key.
     * @return A TranslatableComponent for the material. Use {@link TranslatableComponent#toLegacyText()} with {@link Player#spigot()}>{@link Spigot#sendMessage(BaseComponent...)}
     */
    @NotNull
    public static TranslatableComponent translateKey(@NotNull String key){
        return new TranslatableComponent(key);
    }

    /**
     * Alias for {@link TranslateManager#translateMaterial(Material)}
     */
    @NotNull
    public static TranslatableComponent translate(@NotNull Material material){return translateMaterial(material);}

    /**
     * Alias for {@link TranslateManager#translateEntity(EntityType)}
     */
    @NotNull
    public static TranslatableComponent translate(@NotNull EntityType type){return translateEntity(type);}

    /**
     * Alias for {@link TranslateManager#translateEnchantment(Enchantment)}
     */
    @NotNull
    public static TranslatableComponent translate(@NotNull Enchantment enchantment){return translateEnchantment(enchantment);}

    /**
     * Alias for {@link TranslateManager#translateEffect(PotionEffectType)}
     */
    @NotNull
    public static TranslatableComponent translate(@NotNull PotionEffectType effectType){return translateEffect(effectType);}

    /**
     * Alias for {@link TranslateManager#translateKey(String)}
     */
    @NotNull
    public static TranslatableComponent translate(@NotNull String key){return translateKey(key);}

    /**
     * Translates the colors in the text given. Supports default minecraft color codes and hex colors.
     * @param message Message to translate colors.
     * @return Translated message.
     */
    @NotNull
    public static String translateColors(@NotNull String message){
        String newMessage = message;

        Matcher matcher = hexColorsPattern.matcher(newMessage);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()){
            String code = matcher.group(1).substring(1);
            matcher.appendReplacement(buffer, ChatColor.of(code)+"");
        }
        matcher.appendTail(buffer);

        newMessage = ChatColor.translateAlternateColorCodes('&',buffer.toString());
        return newMessage;
    }
}