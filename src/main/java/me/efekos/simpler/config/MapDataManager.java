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

package me.efekos.simpler.config;

import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * A basic database class made using {@link Gson}. You can store a {@link java.util.Map<K,V>} in this data. Use {@link #save()}
 * and {@link #load(Class)} to load your data.
 * @param <K> Type of the keys you will use. Probably {@link String} but you can change it to something else if you want.
 * @param <V>
 *           Type of the data you want to store as a list. Be aware that using incompatible types
 *           in this type might cause errors. Just to let you know, there is a list of the classes
 *           compatible to be used inside V.
 *           <ul>
 *           <li>{@link String}.</li>
 *           <li>{@link Boolean}.</li>
 *           <li>{@link Integer}.</li>
 *           <li>{@link Double}.</li>
 *           <li>{@link Long}.</li>
 *           <li>{@link List<Object>}.</li>
 *           <li>{@code null}.</li>
 *           <li>{@code enum} classes.</li>
 *           <li>Any class that does not contain any type other than the ones above.</li>
 *           </ul>
 */
public class MapDataManager<K, V extends Storable> {

    /**
     * Main map of all the data stored inside this database.
     */
    private Map<K,V> data = new HashMap<>();
    /**
     * Path to the file where all the data will be saved with {@link #save()}.
     */
    private final String path;
    /**
     * Instance of the plugin that uses this database.
     */
    private final JavaPlugin plugin;

    /**
     * Constructs a new manager.
     * @param path Path to the file where all the data will be saved with {@link #save()}. Must end with {@code .json}.
     *             You can't choose a path outside your plugins data folder.
     * @param plugin Instance of the plugin that will use this database. Recommended to be {@code this}, assuming that
     *               you are constructing a database inside your {@link JavaPlugin#onEnable()} method.
     */
    public MapDataManager(String path, JavaPlugin plugin) {
        if(!path.endsWith(".json")) throw new InvalidParameterException("path must end with .json");
        this.path = path;
        this.plugin = plugin;
    }

    /**
     * Grabs a data from the map using its {@link UUID}.
     * @param id ID of the data you want to get.
     * @return Data if found, {@code null} otherwise.
     */
    @Nullable
    public V get(@NotNull K id){
        if(data.containsKey(id))return data.get(id);
        return null;
    }

    /**
     * Searches for a data with the given id and deletes the data found.
     * @param id ID of the data you want to delete
     */
    public void delete(K id){
        if(data.containsKey(id))data.remove(id);
    }

    /**
     * Puts the given data to map.
     * @param key Key of the data in map.
     * @param data Data you want to put to the map.
     */
    public void set(K key,V data){
        this.data.put(key,data);
    }

    /**
     * Saves all the data to the plugins data folder using the given path. It will save the data as a '.json' file, to
     * {@link MapDataManager#path} under plugins data folder.
     */
    public void save(){
        Gson gson = new Gson();

        String absPath = plugin.getDataFolder().getAbsolutePath()+"\\"+path;

        File file = new File(absPath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file,false);
            gson.toJson(data,writer);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Loads all the data from the save before. You don't have to check if file exists, because method does it.
     * @param clazz Accessing a class object of a type parameter is impossible, so you need to give it. Just do
     * {@code Map<K,V>[].class} here, replacing {@code <K>} and {@code <V>} with the name of your type. That type must be same with {@link K} and {@link V}.
     */
    public void load(Class<Map<K,V>> clazz){
        Gson gson = new Gson();
        String absPath = plugin.getDataFolder().getAbsolutePath()+"\\"+path;
        File file = new File(absPath);

        if(file.exists()){
            try {
                Reader reader = new FileReader(file);

                Map<K,V> n = gson.fromJson(reader,clazz);

                data = n;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs the entire map and returns it.
     * @return All the map of data.
     */
    public Map<K,V> getAll(){
        return data;
    }
}
