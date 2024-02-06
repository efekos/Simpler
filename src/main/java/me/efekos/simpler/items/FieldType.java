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

/**
 * Represents type of a field annotated with {@link SaveField}.
 */
public enum FieldType {
    /**
     * String type. Your field must be a {@link String} when using this.
     */
    STRING,
    /**
     * Integer type. Your field must be an {@code int} or an {@link Integer} when using this.
     */
    INTEGER,
    /**
     * Double type. Your field must be a {@code double} or a {@link Double} when using this.
     */
    DOUBLE,
    /**
     * Long type. Your field must be a {@code long} or a {@link Long} when using this.
     */
    LONG,
    /**
     * Float type. Your field must be a {@code float} or a {@link Float} when using this.
     */
    FLOAT,
    /**
     * Boolean type. Your field must be a {@code boolean} or a {@link Boolean} when using this.
     */
    BOOLEAN,
    /**
     * Byte type. Your field must be a {@code byte} or a {@link Byte} when using this.
     */
    BYTE
}
