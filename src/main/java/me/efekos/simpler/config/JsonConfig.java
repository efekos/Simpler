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

package me.efekos.simpler.config;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Another type of configuration that can read JSON files.
 */
public class JsonConfig {

    /**
     * Name of the resource that will be cloned from plugin resources. This makes it really easy to make custom configs. All you have to do it change the resource name.
     */
    private final String resourceName;

    /**
     * A map of the keys that has a string value. Every string is stored here.
     */
     private final Map<String,String> stringValues = new HashMap<>();
    /**
     * A map of the keys that has a number value. Any number that isn't decimal is stored here.
     */
    private final Map<String,Integer> numberValues = new HashMap<>();
    /**
     * A map of the keys that has a number value. Only numbers that is compatible for a double is stored here.
     */
    private final Map<String,Double> doubleValues = new HashMap<>();
    /**
     * A map of the keys that has a boolean value. Every boolean is stored here.
     */
     private final Map<String,Boolean> booleanValues = new HashMap<>();
    /**
     * A map of the keys that has a boolean value. Every string that has only one character is stored here.
     */
    private final Map<String,Character> characterValues = new HashMap<>();
    /**
     * A map of the keys that has an array value. Type of the array is unknown.
     */
    private final Map<String,List<?>> arrayValues = new HashMap<>();
    /**
     * List of the keys that has the value {@code null}.
     */
    private final List<String> nullValues = new ArrayList<>();

    /**
     * The plugin that uses this config.
     */
    private final JavaPlugin plugin;

    /**
     * Another type of configuration that can read JSON files.
     * @param resourceName Name of the resource you want to use as this config. Make sure that your resource name ends with `.yml` and you have a resource with the exact same name on your 'resources' folder.
     * @param plugin An instance of your plugin.
     */
    public JsonConfig(String resourceName, JavaPlugin plugin) {
        this.resourceName = resourceName.endsWith(".json")?resourceName:resourceName+".json";
        this.plugin = plugin;
    }

    /**
     * Loads the default configuration file to your plugin's data folder. Recommended to use inside {@link JavaPlugin#onEnable()}.
     */
    public void setup() throws FileNotFoundException{
        File file = new File(plugin.getDataFolder(), resourceName);

        if(!file.exists()){
            try{
                plugin.saveResource(resourceName, false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        JsonObject object = JsonParser.parseReader(new JsonReader(new FileReader(file))).getAsJsonObject();
        readObject("",object);
    }

    private void readObject(String preKey,JsonObject object){
        Set<String> keys = object.keySet();

        for (String key : keys) {
            JsonElement element = object.get(key);

            if(element.isJsonNull()) {
                nullValues.add(preKey+key);
                continue;
            }

            if(element.isJsonPrimitive()){
                JsonPrimitive primitive = (JsonPrimitive) element;
                if(primitive.isBoolean()) {
                    booleanValues.put(preKey+key,primitive.getAsBoolean());
                    continue;
                }

                if(primitive.isNumber()){
                    readNumber(preKey,key,primitive);
                    continue;
                }

                if(primitive.isString()){
                    readString(preKey,key,primitive);
                    continue;
                }

            } // if element is primitive

            if(element.isJsonObject()) {
                JsonObject jsonObject = (JsonObject) element;
                readObject(preKey+key+".",jsonObject);
            }

            if(element.isJsonArray()){
                List<JsonElement> list = ((JsonArray) element).asList();
                List<Object> listToSave = new ArrayList<>();


                for (JsonElement jsonElement : list) {
                    if(jsonElement.isJsonNull()) {
                        listToSave.add(null);
                        continue;
                    }
                    if(jsonElement.isJsonPrimitive()){
                        JsonPrimitive primitive = (JsonPrimitive) jsonElement;
                        if(primitive.isBoolean()) {
                            listToSave.add(primitive.getAsBoolean());
                            continue;
                        }

                        if(primitive.isNumber()){
                            listToSave.add(primitive.getAsNumber());
                            continue;
                        }

                        if(primitive.isString()){
                            listToSave.add(jsonElement.getAsString());
                            continue;
                        }

                    }
                    if(jsonElement.isJsonObject()) {
                        listToSave.add(jsonElement.getAsJsonObject());
                    }
                }

                arrayValues.put(preKey+key,listToSave);
            }

        } // string key : keys
    } // method end

    private void readNumber(String preKey,String key,JsonPrimitive primitive){
        if((double) primitive.getAsInt() != primitive.getAsDouble()) { // number should be a double
            doubleValues.put(preKey+key,primitive.getAsDouble());
        } else { // number can be double or integer, so storing the key to both maps.
            doubleValues.put(preKey+key,primitive.getAsDouble());
            numberValues.put(preKey+key,primitive.getAsInt());
        }
    }

    private void readString(String preKey,String key,JsonPrimitive primitive){
        stringValues.put(preKey+key,primitive.getAsString());
        if(primitive.getAsString().length()==1) characterValues.put(preKey+key,primitive.getAsString().charAt(0));
    }
}
