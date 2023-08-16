/*
 * Copyright (c) 2023 efekos
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
// I'm specially sensitive with this file. If you copy this class to somewhere else, please credit to me and Simpler, PLEASE. I spent almost
// a week on finishing this class. I could just use nms, or steal someone's code about nms and paste it to here. But I made this class. I
// could sleep, I could read a book, I could study for high school exams, but I made this class, just for your use. Please, whatever you do,
// please do not claim this file as your own. Please, really please. All I expect from you is some respect about how long this class took to make.

package me.efekos.simpler.items;

import com.google.gson.Gson;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Used to show items in chat. To use this class, you must have an {@link ItemStack} to get content for it. How
 * this works is: Simply it replicates everything inside an {@link ItemStack} to something Minecraft would
 * understand. When you use this content with {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}, it
 * gets this content as an item NBT data. This is why most of the item showers use NMS, because they need an
 * exact same copy of the items nbt data, which seems like accessible only using nms. But with this class, you
 * can show most of the normal items in game. {@link ItemContent#from(ItemStack)} copies all the data inside
 * {@link ItemStack} to itself exactly, however some display names might be lost at the items inside a shulker
 * box, even with the fact that their names get saved too. Some extreme items (like a ban shulker or a cursed
 * bundle) have a small chance to make some lag in the server, maybe even crash it.
 */
public class ItemContent extends Content {
    /**
     * Main class for the "tag" value inside an item NBT. Most of the items has their own NBT data to store data
     * inside it. <a href="https://minecraft.fandom.com/wiki/Player.dat_format#Item_structure">See more about item NBT data</a>.
     */
    public static class Tag {
        /**
         * A value probably used to define how many levels you will need to repair an item. Goes up by 1 every time you repair the item using an anvil.
         */
        public Integer RepairCost;
        /**
         * Indicates how much damage this item got. Goes up every time you use a tool. (using an armor means getting hit with them equipped, elytras work the same way.)
         */
        public Integer Damage;
        /**
         * A bit number that hides some information about the item at tooltips. Use this formula to calculate a 'HideFlags' number: {@code (HideEnchantments) | (HideAttributeModifiers << 1) | (HideUnbreakable << 2) | (HideCanDestroy << 3) | (HideCanPlaceOn << 4) | (HideOthers << 5) | (HideDyed << 6) | (HideUpgrade << 7)}
         */
        private final Integer HideFlags;
        /**
         * Data about the custom name, lore and color of the item.
         */
        public Display display;
        /**
         * If true, tooltip shows "Unbrekable", implying that tool won't take any damage when you use it.
         */
        private final boolean Unbreakable;
        /**
         * Numeric ID about a custom model. Usually used by server-resource packs and map-specific resource packs for custom items.
         */
        private final Integer CustomModelData;
        /**
         * List of the enchantments applied to this item.
         */
        private final EnchantCompound[] Enchantments;

        /**
         * Creates a new {@link Tag} that stores the values given.
         * @param damage
         * Indicates how much damage this item got. gets copied into the "Damage" value you see
         * inside tool tags.
         * @param hideFlags
         * Indicates a number about the "HideFlags" value. According to its number, some information about the item becomes hidden from tooltips. To be exact:
         *                  <ul>
         *                  <li>Adding {@code 1} hides enchantments.</li>
         *                  <li>Adding {@code 2} hides attributes.</li>
         *                  <li>Adding {@code 4} hides "Unbreakable" text.</li>
         *                  <li>Adding {@code 8} hides "Can Destroy:" part.</li>
         *                  <li>Adding {@code 16} hides "Can be Placed On:" part.</li>
         *                  <li>Adding {@code 32} hides various other information. Like potion effects, shield patterns, enchantments stored inside enchanting books, written book information and map ID.</li>
         *                  <li>Adding {@code 64} hides "Dyed" text on leather armors.</li>
         *                  <li>Adding {@code 128} hides trims.</li>
         *                  </ul>
         * @param display
         * Data of the display about item. Includes name, lore and leather armor color.
         * @param unbreakable
         * If true, the tooltip will show "Unbreakable" implying that this tool won't take damage when used.
         * @param customModelData
         * A number indicating the custom model data of an item. Usually used by server-resource packs and map-specific resource packs for custom items.
         * @param enchantments
         * List of the enchantments the tooltip should show.
         * @param repairCost
         * Indicates how many level you will need to repair this tool. Goes up by 1 every time you use an anvil to repair your tool.
         */
        public Tag(Integer damage, Integer hideFlags, Display display, boolean unbreakable, Integer customModelData, EnchantCompound[] enchantments,Integer repairCost) {
            Damage = damage;
            HideFlags = hideFlags;
            this.display = display;
            Unbreakable = unbreakable;
            CustomModelData = customModelData;
            Enchantments = enchantments;
            RepairCost = repairCost;
        }

        /**
         * Returns the "RepairCost" value.
         * @return "RepairCost" of the item, a value probably used to define how many levels you will need to repair an item. Goes up by 1 every time you repair the item using an anvil.
         */
        public Integer getRepairCost() {
            return RepairCost;
        }
        /**
         * Indicates how much damage this item got. Goes up every time you use a tool. (using an armor means getting hit with them equipped, elytras work the same way.)
         * @return "Damage" value.
         */
        public Integer getDamage() {
            return Damage;
        }
        /**
         * A bit number that hides some information about the item at tooltips. Use this formula to calculate a 'HideFlags' number: {@code (HideEnchantments) | (HideAttributeModifiers << 1) | (HideUnbreakable << 2) | (HideCanDestroy << 3) | (HideCanPlaceOn << 4) | (HideOthers << 5) | (HideDyed << 6) | (HideUpgrade << 7)}
         * @return "HideFlags" value.
         */
        public Integer getHideFlags() {
            return HideFlags;
        }
        public Display getDisplay() {
            return display;
        }
        public boolean isUnbreakable() {
            return Unbreakable;
        }
        public Integer getCustomModelData() {
            return CustomModelData;
        }
        public EnchantCompound[] getEnchantments() {
            return Enchantments;
        }
    }
    /**
     * Represents a tag for an item that stores an enchantment inside it. Such as enchanted books.
     */
    public static class EnchantmentStorageTag extends Tag {
        public EnchantmentStorageTag(Tag oldTag, EnchantCompound[] storedEnchantments) {
            super(oldTag.Damage,oldTag.HideFlags,oldTag.display,oldTag.Unbreakable,oldTag.CustomModelData,oldTag.Enchantments,oldTag.RepairCost);
            this.StoredEnchantments = storedEnchantments;
        }

        private final EnchantCompound[] StoredEnchantments;

        public EnchantCompound[] getStoredEnchantments() {
            return StoredEnchantments;
        }
    }
    /**
     * Represents a tag of a "Written Book" item.
     */
    public static class BookTag extends Tag {
        public BookTag(Tag oldTag, String title, String author, Integer generation) {
            super(oldTag.Damage,oldTag.HideFlags,oldTag.display,oldTag.Unbreakable,oldTag.CustomModelData,oldTag.Enchantments,oldTag.RepairCost);
            this.author = author;
            this.title = title;
            this.generation = generation;
        }

        private final String author;
        private final String title;
        private final Integer generation;

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public Integer getGeneration() {
            return generation;
        }
    }
    /**
     * Represents a tag for "Map" items. They store an ID about where they show in case you didn't know.
     */
    public static class MapTag extends Tag {
        private final Integer map;

        public MapTag(Tag oldTag, Integer map) {
            super(oldTag.Damage,oldTag.HideFlags,oldTag.display,oldTag.Unbreakable,oldTag.CustomModelData,oldTag.Enchantments,oldTag.RepairCost);
            this.map = map;
        }

        public Integer getMap() {
            return map;
        }
    }
    /**
     * Represents a tag for an armor item with an armor trim applied to it.
     */
    public static class TrimTag extends Tag {

        private final TrimCompound Trim;

        public TrimTag(Tag oldTag, TrimCompound trim){
            super(oldTag.Damage,oldTag.HideFlags,oldTag.display,oldTag.Unbreakable,oldTag.CustomModelData,oldTag.Enchantments,oldTag.RepairCost);
            this.Trim = trim;
        }

        public TrimCompound getTrim() {
            return Trim;
        }
    }
    /**
     * Represents a tag for a skull, owned by a player or not.
     */
    public static class SkullTag extends Tag {
        private final String SkullOwner;

        public SkullTag(Tag oldTag, String owner){
            super(oldTag.Damage,oldTag.HideFlags,oldTag.display,oldTag.Unbreakable,oldTag.CustomModelData,oldTag.Enchantments,oldTag.RepairCost);
            this.SkullOwner = owner;
        }

        public String getSkullOwner() {
            return SkullOwner;
        }
    }
    /**
     * Represents a tag for fireworks, storing all explosion data about them.
     */
    public static class FireworkTag extends Tag {
        private final FireworkCompound Fireworks;
        public FireworkTag(Tag oldTag, FireworkCompound compound){
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.Fireworks = compound;
        }

        public FireworkCompound getFireworks() {
            return Fireworks;
        }
    }
    /**
     * Represents a tag for banners, storing all patterns of them.
     */
    public static class BannerTag extends Tag {
        private final BannerCompound BlockEntityTag;

        public BannerTag(Tag oldTag, BannerCompound banner){
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.BlockEntityTag = banner;
        }

        public BannerCompound getBlockEntityTag() {
            return BlockEntityTag;
        }
    }
    /**
     * Represents a tag for "Suspicious Stew" item. In case you didn't know before, sus stews store which effect you will gain after consuming.
     */
    public static class SusStewTag extends Tag {
        private final SusStewEffectCompound[] Effects;

        public SusStewTag(Tag oldTag, SusStewEffectCompound[] potionEffects){
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.Effects = potionEffects;
        }

        public SusStewEffectCompound[] getEffects() {
            return Effects;
        }
    }
    /**
     * Represents a tag for potions,splash potions,lingering potions and tipped arrows, storing all the potion data inside them.
     */
    public static class PotionTag extends Tag {
        private final PotionEffectCompound[] CustomPotionEffects;
        private final String Potion;
        private final Integer CustomPotionColor;

        public PotionTag(Tag oldTag, PotionEffectCompound[] potionEffects, Integer customPotionColor) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.CustomPotionEffects = potionEffects;
            this.Potion = null;
            this.CustomPotionColor = customPotionColor;
        }

        public PotionTag(Tag oldTag, String potion){
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.CustomPotionEffects = null;
            this.Potion = potion;
            this.CustomPotionColor = null;
        }

        public PotionEffectCompound[] getCustomPotionEffects() {
            return CustomPotionEffects;
        }

        public String getPotion() {
            return Potion;
        }

        public Integer getCustomPotionColor() {
            return CustomPotionColor;
        }
    }
    /**
     * Represents a tag for lodestone compasses. Storing their target inside them like a normal lodestone compass would do.
     */
    public static class CompassTag extends Tag{
        private final CompassPosCompound LodestonePos;
        private final String LodestoneDimension;
        private final boolean LodestoneTracked;

        public CompassTag(Tag oldTag,CompassPosCompound lodestonePos, String lodestoneDimension, boolean lodestoneTracked) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            LodestonePos = lodestonePos;
            LodestoneDimension = lodestoneDimension;
            LodestoneTracked = lodestoneTracked;
        }

        public CompassPosCompound getLodestonePos() {
            return LodestonePos;
        }

        public String getLodestoneDimension() {
            return LodestoneDimension;
        }

        public boolean isLodestoneTracked() {
            return LodestoneTracked;
        }
    }
    /**
     * Represents a tag for "Bucket of Axolotl". Storing variant of the axolotl inside it.
     */
    public static class AxolotlBucketTag extends Tag {

        private final Integer Variant;

        public AxolotlBucketTag(Tag oldTag,Integer variant) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.Variant = variant;
        }

        public Integer getVariant() {
            return Variant;
        }
    }
    /**
     * Represents a tag for goat horns. Storing which type of goat horn they are.
     */
    public static class GoatHornTag extends Tag {
        private final String instrument;

        public GoatHornTag(Tag oldTag,String instrument) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.instrument = instrument;
        }

        public String getInstrument() {
            return instrument;
        }
    }
    /**
     * Represents a tag for bundles, storing all the items inside them.
     */
    public static class BundleTag extends Tag{

        private final List<ItemContent> Items;

        public BundleTag(Tag oldTag,List<ItemContent>contents) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.Items = contents;
        }

        public List<ItemContent> getItems() {
            return Items;
        }
    }
    /**
     * Represents a tag for crossbows, storing the projectiles loaded into them.
     */
    public static class CrossbowTag extends Tag{
        private final List<ItemContent> ChargedProjectiles;
        private final boolean Charged;

        public CrossbowTag(Tag oldTag,List<ItemContent>contents,boolean charged) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments,oldTag.RepairCost);
            this.ChargedProjectiles = contents;
            this.Charged = charged;
        }

        public List<ItemContent> getChargedProjectiles() {
            return ChargedProjectiles;
        }

        public boolean isCharged() {
            return Charged;
        }
    }
    /**
     * Represents a tag for "Knowledge Book" item, storing which recipes they will give you when you right-click to it.
     */
    public static class KnowledgeBookTag extends Tag{

        private final List<String> Recipes;

        public KnowledgeBookTag(Tag oldTag,List<String> recipeIds) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments, oldTag.RepairCost);
            Recipes = recipeIds;
        }

        public List<String> getRecipes() {
            return Recipes;
        }
    }
    /**
     * Represents a tag for shulker boxes, storing all the items inside it.
     */
    public static class ShulkerBoxTag extends Tag {
        private final ShulkerBoxCompound BlockEntityTag;

        public ShulkerBoxTag(Tag oldTag, ShulkerBoxCompound compound) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments, oldTag.RepairCost);
            BlockEntityTag = compound;
        }

        public ShulkerBoxCompound getBlockEntityTag() {
            return BlockEntityTag;
        }
    }
    /**
     * Represents a tag for firework stars, storing all the explosion data inside them.
     */
    public static class FireworkEffectTag extends Tag{
        private final ExplosionCompound Explosion;

        public FireworkEffectTag(Tag oldTag, ExplosionCompound compound) {
            super(oldTag.Damage, oldTag.HideFlags, oldTag.display, oldTag.Unbreakable, oldTag.CustomModelData, oldTag.Enchantments, oldTag.RepairCost);
            Explosion = compound;
        }

        public ExplosionCompound getExplosion() {
            return Explosion;
        }
    }
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // TAGS
    // COMPOUNDS
    // ˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅

    /**
     * Represents a custom potion effect inside a potion.
     */
    public static class PotionEffectCompound {
        private final Integer Id;
        private final Integer Duration;
        private final Integer Amplifier;
        private final boolean ShowParticles;
        private final boolean Ambient;
        private final boolean ShowIcon;

        public PotionEffectCompound(Integer id, Integer duration, Integer amplifier, boolean showParticles, boolean ambient, boolean showIcon) {
            Id = id;
            Duration = duration;
            Amplifier = amplifier;
            ShowParticles = showParticles;
            Ambient = ambient;
            ShowIcon = showIcon;
        }

        public Integer getId() {
            return Id;
        }

        public Integer getDuration() {
            return Duration;
        }

        public Integer getAmplifier() {
            return Amplifier;
        }

        public boolean isShowParticles() {
            return ShowParticles;
        }

        public boolean isAmbient() {
            return Ambient;
        }

        public boolean isShowIcon() {
            return ShowIcon;
        }
    }
    /**
     * Main display class. Stores "Lore" and "Name" values.
     */
    public static class Display {
        private final String Name;
        private final String[] Lore;


        public Display(TextCompound[] name, TextCompound[][] lore){
            this.Name = name!=null ? new Gson().toJson(name) : null;
            this.Lore = Arrays.stream(lore).map(textCompounds -> new Gson().toJson(textCompounds)).collect(Collectors.toList()).toArray(new String[0]);
        }

        public Display(TextCompound[] name){
            this.Name = name != null ? new Gson().toJson(name) : null;
            this.Lore = null;
        }

        public String getName() {
            return Name;
        }

        public String[] getLore() {
            return Lore;
        }
    }
    /**
     * Display class used for leather armors to store their color as well.
     */
    public static class LeatherArmorDisplay extends Display {

        private final Integer color;

        public LeatherArmorDisplay(TextCompound[] name, TextCompound[][] lore, Integer color) {
            super(name, lore);
            this.color = color;
        }

        public Integer getColor() {
            return color;
        }
    }
    /**
     * Represents a JSON text you might be familiar from /tellraw command. Used to store custom names and lores.
     */
    public static class TextCompound {
        private final List<TextCompound> extra;
        private final boolean italic;
        private final boolean bold;
        private final boolean underlined;
        private final boolean strikethrough;
        private final String color;
        private final String text;
        private final boolean obfuscated;

        public TextCompound(boolean italic, boolean bold, boolean underlined, boolean strikethrough, ChatColor color, String text, boolean obfuscated, List<TextCompound> extra) {
            this.italic = italic;
            this.bold = bold;
            this.underlined = underlined;
            this.strikethrough = strikethrough;
            this.color = String.format("#%02x%02x%02x", color.getColor().getRed(), color.getColor().getGreen(), color.getColor().getBlue());
            this.text = text;
            this.obfuscated = obfuscated;
            this.extra = extra;
        }

        public TextCompound(boolean italic, boolean bold, boolean underlined, boolean strikethrough, String color, String text, boolean obfuscated,List<TextCompound> extra) {
            this.italic = italic;
            this.bold = bold;
            this.underlined = underlined;
            this.strikethrough = strikethrough;
            this.color = color;
            this.text = text;
            this.obfuscated = obfuscated;
            this.extra = extra;
        }

        public List<TextCompound> getExtra() {
            return extra;
        }

        public boolean isItalic() {
            return italic;
        }

        public boolean isBold() {
            return bold;
        }

        public boolean isUnderlined() {
            return underlined;
        }

        public boolean isStrikethrough() {
            return strikethrough;
        }

        public String getColor() {
            return color;
        }

        public String getText() {
            return text;
        }

        public boolean isObfuscated() {
            return obfuscated;
        }
    }
    /**
     * Represents an enchantment with its string id and level.
     */
    public static class EnchantCompound {
        private final String id;
        private final Integer lvl;

        public EnchantCompound(String id, Integer lvl) {
            this.id = id;
            this.lvl = lvl;
        }

        public EnchantCompound(Enchantment id, Integer lvl){
            this.id = id.getKey().getNamespace()+":"+id.getKey().getKey();
            this.lvl = lvl;
        }

        public String getId() {
            return id;
        }

        public Integer getLvl() {
            return lvl;
        }
    }
    /**
     * Represents an armor trim inside an armor data.
     */
    public static class TrimCompound {
        private final String pattern;
        private final String material;

        public TrimCompound(String pattern, String material) {
            this.pattern = pattern;
            this.material = material;
        }

        public TrimCompound(TrimPattern pattern, TrimMaterial material){
            this.pattern = pattern.getKey().getNamespace()+":"+pattern.getKey().getKey();
            this.material = material.getKey().getNamespace()+":"+material.getKey().getKey();
        }

        public String getPattern() {
            return pattern;
        }

        public String getMaterial() {
            return material;
        }
    }
    /**
     * Represents the data inside "Fireworks" tag of a firework.
     */
    public static class FireworkCompound {
        private final Integer Flight;

        private final ExplosionCompound[] Explosions;

        public FireworkCompound(Integer flight, ExplosionCompound[] explosions) {
            Flight = flight;
            Explosions = explosions;
        }

        public Integer getFlight() {
            return Flight;
        }

        public ExplosionCompound[] getExplosions() {
            return Explosions;
        }
    }
    /**
     * Represents an explosion effect of a firework.
     */
    public static class ExplosionCompound {
        private final boolean Flicker;
        private final boolean Trail;
        private final Integer Type;

        private final List<Integer> Colors;
        private final List<Integer> FadeColors;

        public ExplosionCompound(boolean flicker, boolean trail, Integer type, List<Integer> colors, List<Integer> fadeColors) {
            Flicker = flicker;
            Trail = trail;
            Type = type;
            Colors = colors;
            FadeColors = fadeColors;
        }

        public boolean isFlicker() {
            return Flicker;
        }

        public boolean isTrail() {
            return Trail;
        }

        public Integer getType() {
            return Type;
        }

        public List<Integer> getColors() {
            return Colors;
        }

        public List<Integer> getFadeColors() {
            return FadeColors;
        }
    }
    /**
     * Represents a banner item.
     */
    public static class BannerCompound {

        private final Integer Base;
        private final BannerPatternCompound[] Patterns;

        public BannerCompound(Integer base, BannerPatternCompound[] patterns) {
            Base = base;
            Patterns = patterns;
        }

        public Integer getBase() {
            return Base;
        }

        public BannerPatternCompound[] getPatterns() {
            return Patterns;
        }
    }
    /**
     * Represents a pattern of a banner.
     */
    public static class BannerPatternCompound {
        private final String Pattern;
        private final Integer Color;

        public BannerPatternCompound(String pattern, Integer color) {
            Pattern = pattern;
            Color = color;
        }

        public String getPattern() {
            return Pattern;
        }

        public Integer getColor() {
            return Color;
        }
    }
    /**
     * Represents an effect of the "Suspicious Stew" item.
     */
    public static class SusStewEffectCompound {
        private final Integer EffectId;
        private final Integer EffectDuration;

        public SusStewEffectCompound(Integer effectId, Integer effectDuration) {
            EffectId = effectId;
            EffectDuration = effectDuration;
        }

        public Integer getEffectId() {
            return EffectId;
        }

        public Integer getEffectDuration() {
            return EffectDuration;
        }
    }
    /**
     * Represents a position inside a lodestone compass.
     */
    public static class CompassPosCompound {
        private final Integer X;
        private final Integer Y;
        private final Integer Z;

        public CompassPosCompound(Integer x, Integer y, Integer z) {
            X = x;
            Y = y;
            Z = z;
        }

        public Integer getX() {
            return X;
        }

        public Integer getY() {
            return Y;
        }

        public Integer getZ() {
            return Z;
        }
    }
    /**
     * Represents the items inside a shulker box.
     */
    public static class ShulkerBoxCompound {
        private final List<ItemContent> Items;

        public ShulkerBoxCompound(List<ItemContent> items) {
            Items = items;
        }

        public List<ItemContent> getItems() {
            return Items;
        }
    }
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // COMPOUNDS
    // ACTUAL ITEM CONTENT
    // ˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅
    @Override
    public HoverEvent.Action requiredAction() {
        return HoverEvent.Action.SHOW_ITEM;
    }
    /**
     * Item id of this item. String representation of a {@link NamespacedKey} as well.
     */
    private final String id;
    /**
     * Count of the item. Important for items inside shulker boxes.
     */
    private final Integer Count;
    /**
     * All the other data about item. Display, enchantments, lore etc...
     */
    private final String tag;
    /**
     * Number that indicates the slot of this item. Important for items inside shulker boxes.
     */
    private final Integer Slot;
    /**
     * Getter for {@link #id}
     * @return Item id of this content.
     */
    public String getId() {
        return id;
    }
    /**
     * Getter for {@link #Count}
     * @return Count of this content.
     */
    public Integer getCount() {
        return Count;
    }
    /**
     * Getter for {@link #tag}
     * @return The tag which is used to store entire tag data. It is actually a {@link Tag}, but gets converted
     * to {@link String} inside an {@link ItemContent} so Minecraft will understand.
     */
    public String getTag() {
        return tag;
    }
    /**
     * Getter for {@link #Slot}
     * @return Slot of the item.
     */
    public Integer getSlot() {
        return Slot;
    }
    /**
     * Constructs an {@link ItemContent} without a slot. Since {@link #Slot} is only important for contents that
     * will be in under shulker box content, you can use this constructor for other cases.
     * @param id ID of the item. Recommended: {@link NamespacedKey#getNamespace()}+':'+{@link NamespacedKey#getKey()}.
     * @param count Amount of the item. Recommended: {@link ItemStack#getAmount()}.
     * @param tag Actual data about the item. Recommended: {@link Tag#Tag(Integer, Integer, Display, boolean, Integer, EnchantCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack)} if you don't want to struggle constructing a {@link Tag}.
     */
    public ItemContent(String id, Integer count, Tag tag) {
        this.id = id;
        Count = count;
        this.tag = new Gson().toJson(tag)
                .replace("\"Colors\":[","\"Colors\":[I;")
                .replace("\"FadeColors\":[","\"FadeColors\":[I;");
        this.Slot = null;
    }
    /**
     * Constructs an {@link ItemContent} with the data given.
     * @param id ID of the item. Recommended: {@link NamespacedKey#getNamespace()}+':'+{@link NamespacedKey#getKey()}.
     * @param count Amount of the item. Recommended: {@link ItemStack#getAmount()}.
     * @param tag Actual data about the item. Recommended: {@link Tag#Tag(Integer, Integer, Display, boolean, Integer, EnchantCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack, Integer)} if you don't want to struggle constructing a
     *            {@link Tag}.
     * @param slot Slot of the item. Only important if this content will be under a shulker box content.
     */
    public ItemContent(String id, Integer count, Tag tag, Integer slot) {
        this.id = id;
        Count = count;
        this.tag = clearTagString(new Gson().toJson(tag)
                .replace("\"Colors\":[","\"Colors\":[I;")
                .replace("\"FadeColors\":[","\"FadeColors\":[I;"));
        Slot = slot;
    }
    /**
     * Generates an {@link ItemContent} usable in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)} for
     * the {@link ItemStack} given.
     * @param stack Any {@link ItemStack} you want.
     * @return An almost exact copy of the NBT data about the item. Can be used mainly in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}.
     */
    public static ItemContent from(ItemStack stack){
        return from(stack,null);
    }
    /**
     * Generates an {@link ItemContent} usable in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)} for
     * the {@link ItemStack} given. But instead of {@link #from(ItemStack)}, this also add a "Slot" value. It is
     * something important for content inside shulker boxes.
     * @param stack Any {@link ItemStack} you want.
     * @param slot Value you want to put in "Slot".
     * @return An almost exact copy of the NBT data about the item. Can be used mainly in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}.
     */
    public static ItemContent from(ItemStack stack,@Nullable Integer slot){
        NamespacedKey key = stack.getType().getKey();
        String id = key.getNamespace()+":"+key.getKey();

        ItemMeta meta = stack.getItemMeta();

        Tag tag = new Tag(0, calculateHideFlags(meta.getItemFlags()), new Display(getName(meta), getLore(meta)), meta.isUnbreakable(), meta.hasCustomModelData() ? meta.getCustomModelData() : null, getEnchantsCompounds(meta.getEnchants()),0);

        if(meta instanceof EnchantmentStorageMeta){
            EnchantmentStorageMeta enchantmentStorage = (EnchantmentStorageMeta) meta;

            tag = new EnchantmentStorageTag(tag,getEnchantsCompounds(enchantmentStorage.getStoredEnchants()));
        }
        if(meta instanceof BookMeta){
            BookMeta bookMeta = (BookMeta) meta;
            tag = new BookTag(tag,bookMeta.hasTitle()?bookMeta.getTitle():null,bookMeta.hasAuthor()?bookMeta.getAuthor():null, bookMeta.hasGeneration()?getBookGeneration(bookMeta.getGeneration()):null);
        }
        if(meta instanceof MapMeta){
            MapMeta mapMeta = (MapMeta) meta;
            tag = new MapTag(tag,mapMeta.getMapView().getId());
        }
        if(meta instanceof ArmorMeta){
            ArmorMeta armorMeta = (ArmorMeta) meta;
            if(armorMeta.hasTrim()){
                ArmorTrim trim = armorMeta.getTrim();
                tag = new TrimTag(tag,new TrimCompound(trim.getPattern(),trim.getMaterial()));
            }
        }
        if(meta instanceof FireworkMeta){
            FireworkMeta fireworkMeta = (FireworkMeta) meta;
            List<ExplosionCompound> compounds = new ArrayList<>();
            for (FireworkEffect effect : fireworkMeta.getEffects()) {

                compounds.add(new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()), effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }

            tag = new FireworkTag(tag,new FireworkCompound(fireworkMeta.getPower(),compounds.toArray(new ExplosionCompound[0])));
        }
        if(meta instanceof BannerMeta){
            BannerMeta bannerMeta = (BannerMeta) meta;
            List<BannerPatternCompound> patternCompounds = new ArrayList<>();

            for (Pattern pattern : bannerMeta.getPatterns()) {
                patternCompounds.add(new BannerPatternCompound(pattern.getPattern().getIdentifier(), calculatePatternColor(pattern.getColor())));
            }

            tag = new BannerTag(tag,new BannerCompound(calculatePatternColor(stack),patternCompounds.toArray(new BannerPatternCompound[0])));
        }
        if(meta instanceof SkullMeta){
            SkullMeta skullMeta = (SkullMeta) meta;
            if(skullMeta.hasOwner())
                tag = new SkullTag(tag,skullMeta.getOwningPlayer().getName());
        }
        if(meta instanceof SuspiciousStewMeta){
            SuspiciousStewMeta suspiciousStewMeta =(SuspiciousStewMeta) meta;
            List<SusStewEffectCompound> compounds = new ArrayList<>();
            for (PotionEffect effect : suspiciousStewMeta.getCustomEffects()) {
                compounds.add(new SusStewEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration()));
            }

            tag = new SusStewTag(tag,compounds.toArray(new SusStewEffectCompound[0]));
        }
        if(meta instanceof Damageable){
            Damageable dMeta =(Damageable) meta;
            tag.Damage = dMeta.getDamage();
        }
        if(meta instanceof LeatherArmorMeta){
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
            tag.display = new LeatherArmorDisplay(getName(meta),getLore(meta),calculateDecimalColor(leatherArmorMeta.getColor()));
        }
        if(meta instanceof PotionMeta){
            PotionMeta potionMeta = (PotionMeta) meta;

            boolean isCustom = potionMeta.hasCustomEffects()&&potionMeta.hasColor();

            if(isCustom){
                List<PotionEffectCompound> compounds = new ArrayList<>();
                for (PotionEffect effect : potionMeta.getCustomEffects()) {
                    compounds.add(new PotionEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration(),effect.getAmplifier(),effect.hasParticles(),effect.isAmbient(),effect.hasIcon()));
                }

                tag = new PotionTag(tag,compounds.toArray(new PotionEffectCompound[0]),calculateDecimalColor(potionMeta.getColor()));
            } else {
                PotionData data = potionMeta.getBasePotionData();
                String baseName = getBasePotionName(data);
                String strong = data.isUpgraded() ? "strong_" : "";
                String _long = data.isExtended() ? "long_" : "";

                tag = new PotionTag(tag,"minecraft:"+_long+strong+baseName);
            }
        }
        if(meta instanceof CompassMeta){
            CompassMeta compassMeta = (CompassMeta) meta;

            if(compassMeta.hasLodestone()){
                Location lodestone = compassMeta.getLodestone();
                NamespacedKey worldKey = lodestone.getWorld().getKey();

                tag = new CompassTag(tag,new CompassPosCompound(lodestone.getBlockX(),lodestone.getBlockY(),lodestone.getBlockZ()), worldKey.getNamespace()+":"+worldKey.getKey(), compassMeta.isLodestoneTracked());
            }
        }
        if(meta instanceof AxolotlBucketMeta){
            AxolotlBucketMeta bucketMeta = (AxolotlBucketMeta) meta;
            if(bucketMeta.hasVariant()){
                tag = new AxolotlBucketTag(tag,calculateAxolotlVariant(bucketMeta.getVariant()));
            }
        }
        if(meta instanceof MusicInstrumentMeta){
            MusicInstrumentMeta musicInstrumentMeta = (MusicInstrumentMeta) meta;
            MusicInstrument instrument = musicInstrumentMeta.getInstrument();
            tag = new GoatHornTag(tag,instrument.getKey().getKey());
        }
        if(meta instanceof BundleMeta){
            BundleMeta bundleMeta =(BundleMeta) meta;
            if(bundleMeta.hasItems()){
                List<ItemContent> contents = new ArrayList<>();

                for (ItemStack item : bundleMeta.getItems()) {
                    contents.add(from(item));
                }

                tag = new BundleTag(tag,contents);
            }
        }
        if(meta instanceof CrossbowMeta){
            CrossbowMeta crossbowMeta = (CrossbowMeta) meta;
            if(crossbowMeta.hasChargedProjectiles()){
                tag = new CrossbowTag(tag,crossbowMeta.getChargedProjectiles().stream().map(ItemContent::from).collect(Collectors.toList()), true);
            } else {
                tag = new CrossbowTag(tag,new ArrayList<>(),false);
            }
        }
        if(meta instanceof Repairable){
            Repairable repairable = (Repairable) meta;
            if(repairable.hasRepairCost()) tag.RepairCost = repairable.getRepairCost();
        }
        if(meta instanceof KnowledgeBookMeta){
            KnowledgeBookMeta knowledgeBookMeta =(KnowledgeBookMeta) meta;
            List<String> recipes = knowledgeBookMeta.getRecipes().stream().map(namespacedKey -> namespacedKey.getNamespace() + ":" + namespacedKey.getKey()).collect(Collectors.toList());
            tag = new KnowledgeBookTag(tag,recipes);
        }
        if(meta instanceof BlockStateMeta) {
            BlockStateMeta blockStateMeta = (BlockStateMeta) meta;
            BlockState state = blockStateMeta.getBlockState();
            if(state instanceof ShulkerBox){
                ShulkerBox shulkerBox = (ShulkerBox) state;
                List<ItemContent> contents = new ArrayList<>();
                for (int i = 0; i < 27; i++) {
                    ItemStack shulkerItem = shulkerBox.getInventory().getItem(i);
                    if(shulkerItem==null)continue;
                    contents.add(from(shulkerItem,i));
                }
                tag = new ShulkerBoxTag(tag,new ShulkerBoxCompound(contents));
            }
            // NO WAY I'M CHECKING ALL SUBCLASSES OF BLOCKSTATE JUST TO MAKE YOU SEE "(+NBT)". OKAY?
        }
        if(meta instanceof FireworkEffectMeta){
            FireworkEffectMeta effectMeta = (FireworkEffectMeta) meta;
            if(effectMeta.hasEffect()){
                FireworkEffect effect = effectMeta.getEffect();
                tag = new FireworkEffectTag(tag,new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()),effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }
        }

        return new ItemContent(id, stack.getAmount(),tag,slot);
    }
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // ACTUAL ITEM CONTENT
    // UTILS
    // ˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅
    /**
     * Removes default values from the tag string. Example: At {@code {"text":"hi","bold":false}}, {@code "bold":false}
     * is useless because texts are not bold by default. This method removes it from the tag.
     * @param tagString {@link String} version of a {@link Tag}.
     * @return Filtered version of the tag given.
     */
    public static String clearTagString(String tagString){
        return tagString
                .replace(",\"HideFlags\":0","")
                .replace(",\"Damage\":0","")
                .replace(",\"RepairCost\":0","")
                .replace(",\"Unbreakable\":false","")
                .replace(",\"Enchantments\":[]","")
                .replace(",\"Explosions\":[]","")
                .replace(",\"Lore\":[]","")
                .replace(",\\\"HideFlags\\\":0","")
                .replace(",\\\"Damage\\\":0","")
                .replace(",\\\"RepairCost\\\":0","")
                .replace(",\\\"Unbreakable\\\":false","")
                .replace(",\\\"Enchantments\\\":[]","")
                .replace(",\\\"Explosions\\\":[]","")
                .replace(",\\\"Lore\\\":[]","")
                .replace(",\\\"Lore\\\":[]","")
                .replace(",\\\"obfuscated\\\":false","")
                .replace(",\\\"underlined\\\":false","")
                .replace(",\\\"strikethrough\\\":false","")
                .replace(",\\\"bold\\\":false","")
                .replace(",\\\\\\\"obfuscated\\\\\\\":false","")
                .replace(",\\\\\\\"underlined\\\\\\\":false","")
                .replace(",\\\\\\\"strikethrough\\\\\\\":false","")
                .replace(",\\\\\\\"bold\\\\\\\":false","");
    }
    /**
     * Generates {@link EnchantCompound}s for every enchantment inside the {@link Map} given.
     * @param enchantments A {@link Map} of the enchantments. Recommended: {@link ItemMeta#getEnchants()}
     * @return An array that contains and {@link EnchantCompound} for every enchantment given.
     */
    public static EnchantCompound[] getEnchantsCompounds(Map<Enchantment,Integer> enchantments){
        List<EnchantCompound> enchantCompounds = new ArrayList<>();

        enchantments.forEach((enchantment, integer) -> enchantCompounds.add(new EnchantCompound(enchantment.getKey().getNamespace()+":"+enchantment.getKey().getKey(),integer)));
        return enchantCompounds.toArray(new EnchantCompound[0]);
    }
    /**
     * Generates {@link TextCompound}s for the display name under the meta given.
     * @param meta Any {@link ItemMeta}.
     * @return An array of {@link TextCompound} if {@link ItemMeta#hasDisplayName()}.
     */
    public static TextCompound[] getName(ItemMeta meta){
        if(!meta.hasDisplayName()) return null;

        BaseComponent[] bases = TextComponent.fromLegacyText(meta.getDisplayName());
        List<TextCompound> compounds = new ArrayList<>();
        for (BaseComponent baseComponent : bases) {
            if(baseComponent instanceof TextComponent)

                compounds.add(textCompoundFrom((TextComponent) baseComponent));
        }

        return compounds.toArray(new TextCompound[0]);
    }
    /**
     * Converts a {@link TextComponent} to a {@link TextCompound}.
     * @param component Any {@link TextComponent} that you want to convert.
     * @return A {@link TextCompound} representing the component given.
     */
    public static TextCompound textCompoundFrom(TextComponent component){
        List<TextCompound> extras = new ArrayList<>();
        if(component.getExtra()!=null){
            for (BaseComponent baseComponent : component.getExtra()) {
                if(baseComponent instanceof TextComponent){
                    extras.add(textCompoundFrom((TextComponent) baseComponent));
                }
            }
        }
        return new TextCompound(component.isItalic(),component.isBold(),component.isUnderlined(),component.isStrikethrough(),component.getColor(), component.getText(),component.isObfuscated(), !extras.isEmpty() ?extras:null);
    }
    /**
     * Generates arrays of {@link TextCompound} for the lore, each array representing one line of the lore.
     * @param meta Any {@link ItemMeta}.
     * @return Arrays of the lore inside an array.
     */
    public static TextCompound[][] getLore(ItemMeta meta){
        List<TextCompound[]> loreCompounds = new ArrayList<>();
        List<String> lore = meta.getLore();
        if(lore == null) lore = new ArrayList<>();
        for (String s : lore) {
            BaseComponent[] components = TextComponent.fromLegacyText(s);
            TextCompound[] loreLineCompounds = Arrays.stream(components.clone()).map(baseComponent -> textCompoundFrom((TextComponent) baseComponent)).toArray(TextCompound[]::new);
            loreCompounds.add(loreLineCompounds);
        }
        return loreCompounds.toArray(new TextCompound[0][0]);
    }
    /**
     * Generates a number for "HideFlags" property from the flags given.
     * @param flags A {@link Set} of {@link ItemFlag}s. Recommended: {@link ItemMeta#getItemFlags()}
     * @return A number that Minecraft will consider as a list of the flags given.
     */
    public static Integer calculateHideFlags(Set<ItemFlag> flags){
        int hideFlagsBit = 0;
        for (ItemFlag flag : flags) {
            switch (flag){
                case HIDE_DYE: hideFlagsBit += 64;
                case HIDE_UNBREAKABLE: hideFlagsBit += 4;
                case HIDE_ENCHANTS: hideFlagsBit += 1;
                case HIDE_PLACED_ON: hideFlagsBit += 16;
                case HIDE_DESTROYS: hideFlagsBit += 8;
                case HIDE_ATTRIBUTES: hideFlagsBit += 2;
                case HIDE_ARMOR_TRIM: hideFlagsBit+=128;
                case HIDE_POTION_EFFECTS:hideFlagsBit+=32;
            }
        }
        return hideFlagsBit;
    }
    /**
     * Converts a {@link org.bukkit.inventory.meta.BookMeta.Generation} to a number that Minecraft will understand.
     * @param generation Generation of the book. Recommended: {@link BookMeta#getGeneration()}.
     * @return Numeric ID of the generation given.
     */
    public static Integer getBookGeneration(BookMeta.Generation generation){
        if(generation==null)return 0;
        switch (generation){
            case COPY_OF_ORIGINAL: return 1;
            case COPY_OF_COPY: return 2;
            case TATTERED: return 3;
            default: return 0;
        }
    }
    /**
     * Converts type of {@link FireworkEffect} to a number that Minecraft will understand.
     * @param type Type of the effect. Recommended: {@link FireworkEffect#getType()}.
     * @return Numeric ID of the type given.
     */
    public static Integer calculateExplosionType(FireworkEffect.Type type){
        switch (type){
            case BALL_LARGE:return 1;
            case STAR:return 2;
            case CREEPER:return 3;
            case BURST:return 4;
            default:return 0;
        }
    }
    /**
     * Converts a {@link DyeColor} to a number for uses inside a banner pattern.
     * @param color Any {@link DyeColor}.
     * @return Numeric ID of the color given.
     */
    public static Integer calculatePatternColor(DyeColor color){
        switch (color){
            case ORANGE:return 1;
            case MAGENTA:return 2;
            case LIGHT_BLUE:return 3;
            case YELLOW:return 4;
            case LIME:return 5;
            case PINK:return 6;
            case GRAY:return 7;
            case LIGHT_GRAY:return 8;
            case CYAN:return 9;
            case PURPLE:return 10;
            case BLUE:return 11;
            case BROWN:return 12;
            case GREEN:return 13;
            case RED:return 14;
            case BLACK:return 15;
            default: return 0;
        }
    }
    /**
     * Same with {@link #calculatePatternColor(DyeColor)}, but this time it does it based on {@link Material} of the item given.
     * @param stack Any {@link ItemStack} with its {@link Material} being one of the banners.
     * @return Numeric ID for the banner type.
     */
    public static Integer calculatePatternColor(ItemStack stack){
        switch (stack.getType()){
            case ORANGE_BANNER:return 1;
            case MAGENTA_BANNER:return 2;
            case LIGHT_BLUE_BANNER:return 3;
            case YELLOW_BANNER: return 4;
            case LIME_BANNER:return 5;
            case PINK_BANNER:return 6;
            case GRAY_BANNER:return 7;
            case LIGHT_GRAY_BANNER:return 8;
            case CYAN_BANNER:return 9;
            case PURPLE_BANNER:return 10;
            case BLUE_BANNER:return 11;
            case BROWN_BANNER:return 12;
            case GREEN_BANNER:return 13;
            case RED_BANNER:return 14;
            case BLACK_BANNER:return 15;
            default:return 0;
        }
    }
    /**
     * Converts a {@link PotionEffectType} to a number Minecraft will understand.
     * @param type Any {@link PotionEffectType} you want.
     * @return Numeric ID of the effect type given.
     */
    public static Integer calculatePotionEffect(PotionEffectType type){
        switch (type.getKey().getKey()){
            default:return 0;
            case "speed": return 1;
            case "slowness": return 2;
            case "haste": return 3;
            case "mining_fatigue": return 4;
            case "strength": return 5;
            case "instant_health": return 6;
            case "instant_damage": return 7;
            case "jump_boost": return 8;
            case "nausea": return 9;
            case "regeneration": return 10;
            case "resistance": return 11;
            case "fire_resistance": return 12;
            case "water_breathing": return 13;
            case "invisibility": return 14;
            case "blindness": return 15;
            case "night_vision": return 16;
            case "hunger":return 17;
            case "weakness":return 18;
            case "poison": return 19;
            case "wither": return 20;
            case "health_boost": return 21;
            case "absorption": return 22;
            case "saturation": return 23;
            case "glowing": return 24;
            case "levitation": return 25;
            case "luck": return 26;
            case "bad_luck": return 27;
            case "slow_falling": return 28;
            case "conduit_power": return 29;
            case "dolphins_grace": return 30;
            case "bad_omen": return 31;
            case "hero_of_the_village": return 32;
            case "darkness": return 33;
        }
    }
    /**
     * Converts a {@link Color} to a decimal color, a color format used for leather armors and fireworks in Minecraft.
     * @param color Any {@link Color}
     * @return An {@link Integer} for the color given, found using this formula: {@code (Red << 16) + (Green << 8) + Blue}.
     */
    public static Integer calculateDecimalColor(Color color){
        int Red = color.getRed();
        int Blue = color.getBlue();
        int Green = color.getGreen();

        return (Red << 16) + (Green << 8) + Blue;
    }
    /**
     * Converts an {@link org.bukkit.entity.Axolotl.Variant} to a number Minecraft will understand.
     * @param variant Any {@link org.bukkit.entity.Axolotl.Variant}.
     * @return Numeric ID of the variant given.
     */
    public static Integer calculateAxolotlVariant(Axolotl.Variant variant){
        switch (variant){
            case WILD:return 1;
            case GOLD:return 2;
            case CYAN: return 3;
            case BLUE:return 4;
            default:return 0;
        }
    }
    /**
     * Converts a {@link PotionData} to a string Minecraft will understand.
     * @param data Any {@link PotionData}. Recommended: {@link PotionMeta#getBasePotionData()}.
     * @return String ID of the base potion given.
     */
    public static String getBasePotionName(PotionData data){
        switch (data.getType()){
            case JUMP: return "leaping";
            case SPEED:return "swiftness";
            case TURTLE_MASTER:return "turtle_master";
            case INSTANT_HEAL:return "healing";
            case INSTANT_DAMAGE:return "harming";
            default: return data.getType().getEffectType().getKey().getKey();
        }
    }
}
