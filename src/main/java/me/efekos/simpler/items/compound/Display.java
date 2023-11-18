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

import com.google.gson.Gson;

import java.util.Arrays;

public class Display {
    private final String Name;
    private final String[] Lore;

    public Display(TextCompound[] name, TextCompound[][] lore){
        this.Name = name!=null ? new Gson().toJson(name) : null;
        this.Lore = Arrays.stream(lore).map(textCompounds -> new Gson().toJson(textCompounds)).toArray(String[]::new);
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