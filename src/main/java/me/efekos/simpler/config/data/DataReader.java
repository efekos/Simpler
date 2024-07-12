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
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataReader {

    public static <T> JsonObject write(T instance){
        JsonObject object = new JsonObject();
        Class<?> clazz = instance.getClass();

        try {
            for (Field field : clazz.getFields()) {
                if(!field.isAnnotationPresent(Store.class)) continue;
                String name = field.getAnnotation(Store.class).value();
                if(object.has(name)) throw new RuntimeException("Simpler error: "+clazz+" has multiple fields stored with name "+name+".");

                boolean b = field.canAccess(instance);
                if(!b) field.setAccessible(true);
                Object value = field.get(instance);

                //primitive
                wrt(object, name, value, field);
            }

            return object;
        } catch (Exception e){
            throw new RuntimeException("Simpler error. Please report to https://github.com/efekos/Simpler/issues",e);
        }
    }

    private static void wrt(JsonObject object,String name,Object value,Field field){

        Class<?> type = field.getType();

        //primitive
        if(type==Boolean.class||type==boolean.class)object.addProperty(name, (boolean) value);
        if(type==Short.class||type==short.class)object.addProperty(name, (short) value);
        if(type==Long.class||type==long.class)object.addProperty(name, (long) value);
        if(type==Float.class||type==float.class)object.addProperty(name, (float) value);
        if(type==Double.class||type==double.class)object.addProperty(name, (double) value);
        if(type==String.class)object.addProperty(name, (String) value);
        if(type==UUID.class)object.addProperty(name, value.toString());

        if(type == Map.class) {
            JsonObject objectToPut = new JsonObject();
            Map<?,?> casted = (Map) value;
            casted.forEach((o, o2) -> {
                if(!(o instanceof UUID)&& !(o instanceof String)) throw new RuntimeException("Simpler error: Maps inside storable classes must have String or UUID keys.");
                objectToPut.add(o.toString(), write(o2));
            });
            object.add(name, objectToPut);
        }

        if(type == List.class || value instanceof List<?>) {
            List<?> casted = (List<?>) value;
            JsonArray arrayToAdd = new JsonArray();
            for (Object o : casted) arrayToAdd.add(write(o));
            object.add(name, arrayToAdd);
        }


    }

}
