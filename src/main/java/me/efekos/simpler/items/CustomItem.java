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

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * Main class you will use for custom item systems. When making a custom item, always use primitive types and {@link SaveField}
 * annotation in your fields. Annotate handling methods with {@link HandleEvent} annotation, or Simpler won't run them.
 */
public class CustomItem extends AbstractCustomItem {
    /**
     * Identifier of this custom item.
     */
    private final NamespacedKey key;


    /**
     * Creates an instance of this custom item. NOTE TO SUB-CLASSES: Do NOT override this constructor in your class. Make
     * a constructor with no parameters that calls this constructor instead.
     *
     * @param key        Identifier of this item.
     * @param appearance A consumer that will be applied to every instance of this item.
     */
    public CustomItem(NamespacedKey key, Consumer<ItemStack> appearance) {
        super(appearance);
        this.key = key;
    }

    /**
     * Returns the identifier of this custom item.
     *
     * @return Identifier of this custom item.
     */
    public NamespacedKey getKey() {
        return key;
    }
}