package me.efekos.simpler.translation;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateManager {

    public static final Pattern hexColorsPattern = Pattern.compile("(&#[0-9a-fA-F]{6})");

    /**
     * Translates a material name.
     * @param material Material to get It's name translated
     * @return Translated name.
     */
    @NotNull
    public static String translateMaterial(Material material){
        TranslatableComponent component = new TranslatableComponent();

        if(material.isBlock()){
            component.setTranslate("block.minecraft."+material.getKey().getKey());
        } else if(material.isItem()){
            component.setTranslate("block.minecraft."+material.getKey().getKey());
        }
        return component.toLegacyText();
    }

    /**
     * Translates an entity name.
     * @param type Type of the entity to get It's name translated
     * @return Translated name.
     */
    @NotNull
    public static String translateEntity(@NotNull EntityType type){
        return new TranslatableComponent("entity.minecraft."+type.getKey().getKey()).toLegacyText();
    }

    /**
     * Translates an enchantment's name
     * @param enchantment An enchantment to translate It's name
     * @return Translated name.
     */
    @NotNull
    public static String translateEnchantment(@NotNull Enchantment enchantment){
        return new TranslatableComponent("enchantment.minecraft."+enchantment.getKey().getKey()).toLegacyText();
    }

    /**
     * Translates a Potion effect's name.
     * @param type A PotionEffectType to translate
     * @return Translated name
     */
    @NotNull
    public static String translateEffect(@NotNull PotionEffectType type){
        return new TranslatableComponent("effect.minecraft"+type.getKey().getKey()).toLegacyText();
    }

    /**
     * Translates a custom translation key.
     * @param key Key to translate.
     * @return Translated message.
     */
    @NotNull
    public static String translateKey(@NotNull String key){
        return new TranslatableComponent(key).toLegacyText();
    }

    @NotNull
    public static String translate(@NotNull Material material){return translateMaterial(material);}
    @NotNull
    public static String translate(@NotNull EntityType type){return translateEntity(type);}
    @NotNull
    public static String translate(@NotNull Enchantment enchantment){return translateEnchantment(enchantment);}
    @NotNull
    public static String translate(@NotNull PotionEffectType effectType){return translateEffect(effectType);}
    @NotNull
    public static String translate(@NotNull String key){return translateKey(key);}

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