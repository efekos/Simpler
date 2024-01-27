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

package me.efekos.simpler.items.compound;

import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

/**
 * Represents an armor trim inside an armor data.
 * @deprecated This class is moved into <a href="https://github.com/efekos/ItemContent">another repository</a>, and will
 * be removed from here soon.
 */
@Deprecated
public class TrimCompound {
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
