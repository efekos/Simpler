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

package me.efekos.simpler.items;

import com.google.gson.Gson;
import me.efekos.simpler.items.compound.*;
import me.efekos.simpler.items.tag.*;
import me.efekos.simpler.items.tag.EnchantmentStorageTag;
import me.efekos.simpler.items.tag.ItemTag;
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
 * bundle) have a small chance to make some lag in the server, maybe, just maybe, even crash it.
 */
public class ItemContent extends Content {
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
     * @param tag Actual data about the item. Recommended: {@link ItemTag#ItemTag(Integer, Integer, me.efekos.simpler.items.compound.Display, boolean, Integer, EnchantmentCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack)} if you don't want to struggle constructing a {@link Tag}.
     */
    public ItemContent(String id, Integer count, ItemTag tag) {
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
     * @param tag Actual data about the item. Recommended: {@link ItemTag#ItemTag(Integer, Integer, me.efekos.simpler.items.compound.Display, boolean, Integer, EnchantmentCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack, Integer)} if you don't want to struggle constructing an {@link ItemTag}.
     * @param slot Slot of the item. Only important if this content will be under a shulker box content.
     */
    public ItemContent(String id, Integer count, ItemTag tag, Integer slot) {
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

        ItemTag itemTag = new ItemTag(0, calculateHideFlags(meta.getItemFlags()), new Display(getName(meta), getLore(meta)), meta.isUnbreakable(), meta.hasCustomModelData() ? meta.getCustomModelData() : null, getEnchantsCompounds(meta.getEnchants()),0);

        if(meta instanceof EnchantmentStorageMeta){
            EnchantmentStorageMeta enchantmentStorage = (EnchantmentStorageMeta) meta;

            itemTag = new EnchantmentStorageTag(itemTag,getEnchantsCompounds(enchantmentStorage.getStoredEnchants()));
        }
        if(meta instanceof BookMeta){
            BookMeta bookMeta = (BookMeta) meta;
            itemTag = new BookTag(itemTag,bookMeta.hasTitle()?bookMeta.getTitle():null,bookMeta.hasAuthor()?bookMeta.getAuthor():null, bookMeta.hasGeneration()?getBookGeneration(bookMeta.getGeneration()):null);
        }
        if(meta instanceof MapMeta){
            MapMeta mapMeta = (MapMeta) meta;
            itemTag = new MapTag(itemTag,mapMeta.getMapView().getId());
        }
        if(meta instanceof ArmorMeta){
            ArmorMeta armorMeta = (ArmorMeta) meta;
            if(armorMeta.hasTrim()){
                ArmorTrim trim = armorMeta.getTrim();
                itemTag = new TrimTag(itemTag,new TrimCompound(trim.getPattern(),trim.getMaterial()));
            }
        }
        if(meta instanceof FireworkMeta){
            FireworkMeta fireworkMeta = (FireworkMeta) meta;
            List<ExplosionCompound> compounds = new ArrayList<>();
            for (FireworkEffect effect : fireworkMeta.getEffects()) {

                compounds.add(new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()), effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }

            itemTag = new FireworkTag(itemTag,new FireworkCompound(fireworkMeta.getPower(),compounds.toArray(new ExplosionCompound[0])));
        }
        if(meta instanceof BannerMeta){
            BannerMeta bannerMeta = (BannerMeta) meta;
            List<BannerPatternCompound> patternCompounds = new ArrayList<>();

            for (Pattern pattern : bannerMeta.getPatterns()) {
                patternCompounds.add(new BannerPatternCompound(pattern.getPattern().getIdentifier(), calculatePatternColor(pattern.getColor())));
            }

            itemTag = new BannerTag(itemTag,new BannerCompound(calculatePatternColor(stack),patternCompounds.toArray(new BannerPatternCompound[0])));
        }
        if(meta instanceof SkullMeta){
            SkullMeta skullMeta = (SkullMeta) meta;
            if(skullMeta.hasOwner())
                itemTag = new SkullTag(itemTag,skullMeta.getOwningPlayer().getName());
        }
        if(meta instanceof SuspiciousStewMeta){
            SuspiciousStewMeta suspiciousStewMeta =(SuspiciousStewMeta) meta;
            List<SuspiciousStewEffectCompound> compounds = new ArrayList<>();
            for (PotionEffect effect : suspiciousStewMeta.getCustomEffects()) {
                compounds.add(new SuspiciousStewEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration()));
            }

            itemTag = new SuspiciousStewTag(itemTag,compounds.toArray(new SuspiciousStewEffectCompound[0]));
        }
        if(meta instanceof Damageable){
            Damageable dMeta =(Damageable) meta;
            if(dMeta.hasDamage()) itemTag.Damage = dMeta.getDamage();
        }
        if(meta instanceof LeatherArmorMeta){
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
            itemTag.display = new LeatherArmorDisplay(getName(meta),getLore(meta),calculateDecimalColor(leatherArmorMeta.getColor()));
        }
        if(meta instanceof PotionMeta){
            PotionMeta potionMeta = (PotionMeta) meta;

            boolean isCustom = potionMeta.hasCustomEffects()&&potionMeta.hasColor();

            if(isCustom){
                List<PotionEffectCompound> compounds = new ArrayList<>();
                for (PotionEffect effect : potionMeta.getCustomEffects()) {
                    compounds.add(new PotionEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration(),effect.getAmplifier(),effect.hasParticles(),effect.isAmbient(),effect.hasIcon()));
                }

                itemTag = new PotionTag(itemTag,compounds.toArray(new PotionEffectCompound[0]),calculateDecimalColor(potionMeta.getColor()));
            } else {
                PotionData data = potionMeta.getBasePotionData();
                String baseName = getBasePotionName(data);
                String strong = data.isUpgraded() ? "strong_" : "";
                String _long = data.isExtended() ? "long_" : "";

                itemTag = new PotionTag(itemTag,"minecraft:"+_long+strong+baseName);
            }
        }
        if(meta instanceof CompassMeta){
            CompassMeta compassMeta = (CompassMeta) meta;

            if(compassMeta.hasLodestone()){
                Location lodestone = compassMeta.getLodestone();
                NamespacedKey worldKey = lodestone.getWorld().getKey();

                itemTag = new CompassTag(itemTag,new CompassPositionCompound(lodestone.getBlockX(),lodestone.getBlockY(),lodestone.getBlockZ()), worldKey.getNamespace()+":"+worldKey.getKey(), compassMeta.isLodestoneTracked());
            }
        }
        if(meta instanceof AxolotlBucketMeta){
            AxolotlBucketMeta bucketMeta = (AxolotlBucketMeta) meta;
            if(bucketMeta.hasVariant()){
                itemTag = new AxolotlBucketTag(itemTag,calculateAxolotlVariant(bucketMeta.getVariant()));
            }
        }
        if(meta instanceof MusicInstrumentMeta){
            MusicInstrumentMeta musicInstrumentMeta = (MusicInstrumentMeta) meta;
            MusicInstrument instrument = musicInstrumentMeta.getInstrument();
            itemTag = new GoatHornTag(itemTag,instrument.getKey().getKey());
        }
        if(meta instanceof BundleMeta){
            BundleMeta bundleMeta =(BundleMeta) meta;
            if(bundleMeta.hasItems()){
                List<ItemContent> contents = new ArrayList<>();

                for (ItemStack item : bundleMeta.getItems()) {
                    contents.add(from(item));
                }

                itemTag = new BundleTag(itemTag,contents);
            }
        }
        if(meta instanceof CrossbowMeta){
            CrossbowMeta crossbowMeta = (CrossbowMeta) meta;
            if(crossbowMeta.hasChargedProjectiles()){
                itemTag = new CrossbowTag(itemTag,crossbowMeta.getChargedProjectiles().stream().map(ItemContent::from).collect(Collectors.toList()), true);
            } else {
                itemTag = new CrossbowTag(itemTag,new ArrayList<>(),false);
            }
        }
        if(meta instanceof Repairable){
            Repairable repairable = (Repairable) meta;
            if(repairable.hasRepairCost()) itemTag.RepairCost = repairable.getRepairCost();
        }
        if(meta instanceof KnowledgeBookMeta){
            KnowledgeBookMeta knowledgeBookMeta =(KnowledgeBookMeta) meta;
            List<String> recipes = knowledgeBookMeta.getRecipes().stream().map(namespacedKey -> namespacedKey.getNamespace() + ":" + namespacedKey.getKey()).collect(Collectors.toList());
            itemTag = new KnowledgeBookTag(itemTag,recipes);
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
                itemTag = new ShulkerBoxTag(itemTag,new ShulkerBoxCompound(contents));
            }
            // NO WAY I'M CHECKING ALL SUBCLASSES OF BLOCKSTATE JUST TO MAKE YOU SEE "(+NBT)". OKAY?
        }
        if(meta instanceof FireworkEffectMeta){
            FireworkEffectMeta effectMeta = (FireworkEffectMeta) meta;
            if(effectMeta.hasEffect()){
                FireworkEffect effect = effectMeta.getEffect();
                itemTag = new FireworkEffectTag(itemTag,new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()),effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }
        }

        return new ItemContent(id, stack.getAmount(), itemTag,slot);
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
     * Generates {@link EnchantmentCompound}s for every enchantment inside the {@link Map} given.
     * @param enchantments A {@link Map} of the enchantments. Recommended: {@link ItemMeta#getEnchants()}
     * @return An array that contains and {@link EnchantmentCompound} for every enchantment given.
     */
    public static EnchantmentCompound[] getEnchantsCompounds(Map<Enchantment,Integer> enchantments){
        List<EnchantmentCompound> enchantCompounds = new ArrayList<>();

        enchantments.forEach((enchantment, integer) -> enchantCompounds.add(new EnchantmentCompound(enchantment.getKey().getNamespace()+":"+enchantment.getKey().getKey(),integer)));
        return enchantCompounds.toArray(new EnchantmentCompound[0]);
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
