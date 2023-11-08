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

import java.util.List;

/**
 * Represents an explosion effect of a firework.
 */
public class ExplosionCompound {
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
