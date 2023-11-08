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

package me.efekos.simpler.items.tag;

import me.efekos.simpler.items.ItemContent;
import me.efekos.simpler.items.compound.*;

/**
 * Main class for the "tag" value inside an item NBT. Most of the items has their own NBT data to store data
 * inside it. <a href="https://minecraft.wiki/Player.dat_format#Item_structure">See more about item NBT data</a>.
 */
public class ItemTag {
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
    private final EnchantmentCompound[] Enchantments;

    /**
     * Creates a new {@link ItemTag} that stores the values given.
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
    public ItemTag(Integer damage, Integer hideFlags, Display display, boolean unbreakable, Integer customModelData, EnchantmentCompound[] enchantments, Integer repairCost) {
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
    public EnchantmentCompound[] getEnchantments() {
        return Enchantments;
    }
}
