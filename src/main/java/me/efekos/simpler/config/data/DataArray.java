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

package me.efekos.simpler.config.data;

import com.google.gson.JsonArray;

import java.util.List;

public class DataArray implements DataObject<List<DataObject<?, ?>>, JsonArray> {

    private final List<DataObject<?, ?>> list;

    private DataArray(DataObject<?, ?>... array) {
        list = List.of(array);
    }

    public static DataArray of(DataObject<?, ?>... array) {
        return new DataArray();
    }

    @Override
    public List<DataObject<?, ?>> value() {
        return list;
    }

    @Override
    public boolean isCompound() {
        return true;
    }

    public DataArray add(DataObject<?, ?> value) {
        list.add(value);
        return this;
    }

    public DataArray add(String value) {
        list.add(DataString.of(value));
        return this;
    }

    public DataArray add(Boolean value) {
        list.add(DataBool.of(value));
        return this;
    }

    public DataArray add(int value) {
        list.add(DataInt.of(value));
        return this;
    }

    public DataArray add(long value) {
        list.add(DataLong.of(value));
        return this;
    }

    public DataArray add(float value) {
        list.add(DataFloat.of(value));
        return this;
    }

    public DataArray add(double value) {
        list.add(DataDouble.of(value));
        return this;
    }

    public DataArray add(short value) {
        list.add(DataShort.of(value));
        return this;
    }

    public DataArray add(DataCompound value) {
        list.add(value);
        return this;
    }

    @Override
    public JsonArray toJson() {
        JsonArray array = new JsonArray();
        for (DataObject<?, ?> dataObject : list) array.add(dataObject.toJson());
        return array;
    }
}
