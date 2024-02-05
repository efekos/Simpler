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

package me.efekos.simpler.items.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.efekos.simpler.exception.InvalidAnnotationException;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

abstract class AbstractCustomItem {
    private final Consumer<ItemStack> appearance;
    private final List<Method> methods = new ArrayList<>();
    private final Map<SaveField,Field> saveFieldsMap = new HashMap<>();

    public AbstractCustomItem(Consumer<ItemStack> appearance) {
        this.appearance = appearance;
        findMethods();
        findSaveFields();
    }

    private void findSaveFields() {
        for (Field field : getClass().getFields()) {
            SaveField annotation = field.getAnnotation(SaveField.class);
            if(annotation==null)continue;
            saveFieldsMap.put(annotation,field);
        }
    }

    public ItemStack makeAppearance(ItemStack stack) {
        ItemStack clonedStack = stack.clone();

        appearance.accept(clonedStack);

        return clonedStack;
    }

    public void runMethods(Event event) {
        for (Method method : methods) {
            try {
                method.invoke(this, event);
            } catch (InvocationTargetException ignored) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void putSaveFields(JsonObject object){
        Field[] fields = this.getClass().getFields();

        saveFieldsMap.forEach((annotation, field) -> {
            String s = annotation.value();

            try {
                Object o = field.get(this);

                switch (annotation.fieldType()){
                    case STRING -> object.addProperty(s,o.toString());
                    case FLOAT -> object.addProperty(s,o instanceof Float?(Float)o:(float)o);
                    case LONG -> object.addProperty(s,o instanceof Long?(Long)o:(long)o);
                    case DOUBLE -> object.addProperty(s,o instanceof Double?(Double)o:(double)o);
                    case INTEGER -> object.addProperty(s,o instanceof Integer?(Integer)o:(int)o);
                    case BYTE -> object.addProperty(s,o instanceof Byte?(Byte)o:(byte)o);
                    case BOOLEAN -> object.addProperty(s,o instanceof Boolean?(Boolean) o:(boolean)o);
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    public void loadSaveFields(JsonObject object){
        saveFieldsMap.forEach((key, field) -> {
            JsonElement jsonElement = object.get(key.value());

            try {
                switch (key.fieldType()){
                    case STRING -> field.set(this,jsonElement.getAsString());
                    case INTEGER -> field.set(this,jsonElement.getAsInt());
                    case DOUBLE -> field.set(this,jsonElement.getAsDouble());
                    case LONG -> field.set(this,jsonElement.getAsLong());
                    case BOOLEAN -> field.set(this,jsonElement.getAsBoolean());
                    case FLOAT -> field.set(this,jsonElement.getAsFloat());
                    case BYTE -> field.set(this,jsonElement.getAsByte());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        });
    }

    public void findMethods() {
        Method[] allMethods = this.getClass().getMethods();

        for (Method method : allMethods) {
            if (method.getAnnotation(HandleEvent.class) != null) {

                if (!Modifier.isPublic(method.getModifiers()))
                    throw new RuntimeException(new InvalidAnnotationException("me.efekos.simpler.items.custom.HandleEvent must be applied to a public method, " + method.getName() + " is not."));

                methods.add(method);
            }
        }
    }
}
