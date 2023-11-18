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

import me.efekos.simpler.items.compound.PotionEffectCompound;

public class PotionTag extends ItemTag{
    private final PotionEffectCompound[] custom_potion_effects;
    private final String Potion;
    private final Integer CustomPotionColor;

    public PotionTag(ItemTag oldItemTag, PotionEffectCompound[] potionEffects, Integer customPotionColor) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.custom_potion_effects = potionEffects;
        this.Potion = null;
        this.CustomPotionColor = customPotionColor;
    }

    public PotionTag(ItemTag oldItemTag, String potion){
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.custom_potion_effects = null;
        this.Potion = potion;
        this.CustomPotionColor = null;
    }

    public PotionEffectCompound[] getCustomPotionEffects() {
        return custom_potion_effects;
    }

    public String getPotion() {
        return Potion;
    }

    public Integer getCustomPotionColor() {
        return CustomPotionColor;
    }
}