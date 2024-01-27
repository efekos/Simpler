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

package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Template class for your custom items. Make classes that extends {@link CustomItem} to make your own custom items.
 */
public abstract class CustomItem {

    protected final UUID _itemUuid;

    /**
     * Returns a type id of this item. Think it as an item id like 'diamond_sword' or 'redstone_block'. This value must be different from all the other {@link CustomItem}s you plan to use.
     * @return Type id of this item.
     */
    @NotNull
    public abstract String getId();

    /**
     * Returns an {@link UUID} for this item. You can use this to store data about this item.
     * @return A unique {@link UUID} for this item.
     */
    @NotNull
    public UUID getUniqueItemId(){
        return _itemUuid;
    }

    /**
     * Returns a default meta for this item that will be copied to every {@link org.bukkit.inventory.ItemStack} instance of this item.
     * @return A default {@link ItemMeta} for this {@link CustomItem}.
     */
    @NotNull
    public abstract ItemMeta getDefaultMeta();

    /**
     * Returns a {@link Material} to be used in {@link org.bukkit.inventory.ItemStack} instances of this {@link CustomItem}.
     * @return Default {@link Material} for {@link org.bukkit.inventory.ItemStack}s about this {@link CustomItem}. Make sure that {@link ItemMeta} made inside {@link #getDefaultMeta()} is valid for this material.
     */
    @NotNull
    public abstract Material getMaterial();

    /**
     * Creates a new {@link CustomItem} instance with a random {@link UUID} inside it.
     */
    public CustomItem() {
        this._itemUuid = UUID.randomUUID();
    }

    /**
     * Creates a new {@link CustomItem} instance with the given {@link UUID} inside it.
     * @param uuid New and final {@link UUID} of this instance.
     */
    public CustomItem(UUID uuid){
        this._itemUuid = uuid;
    }
}
