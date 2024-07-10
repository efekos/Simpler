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

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class DataCompound implements DataObject<Map<String, DataObject<?,?>>,JsonObject> {

    private Map<String,DataObject<?,?>> values = new HashMap<>();

    public DataCompound(){

    }

    public DataCompound putString(String key,String value){
        values.put(key,DataString.of(value));
        return this;
    }

    public DataCompound putBool(String key,Boolean value){
        values.put(key,DataBool.of(value));
        return this;
    }

    public DataCompound putInt(String key,Integer value){
        values.put(key,DataInt.of(value));
        return this;
    }
    public DataCompound putDouble(String key,Double value){
        values.put(key,DataDouble.of(value));
        return this;
    }

    public DataCompound putFloat(String key,Float value){
        values.put(key,DataFloat.of(value));
        return this;
    }

    public DataCompound putShort(String key,Short value){
        values.put(key,DataShort.of(value));
        return this;
    }

    public DataCompound putLong(String key,Long value){
        values.put(key,DataLong.of(value));
        return this;
    }

    public DataCompound putCompound(String key,DataCompound value){
        values.put(key,value);
        return this;
    }

    @Override
    public Map<String, DataObject<?,?>> value() {
        return Map.copyOf(values);
    }

    @Override
    public boolean isCompound() {
        return false;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        values.forEach((s, dataObject) -> object.add(s,dataObject.toJson()));
        return object;
    }

}
