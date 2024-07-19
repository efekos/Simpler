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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.efekos.simpler.config.Storable;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A basic database class made using {@link Gson}. You can store a {@link java.util.Map<String,V>} in this data. Use {@link #save()}
 * and {@link #load()} to load your data.
 *
 * @param <V> Type of the data that you want to store in the list. Beware that only fields annotated with {@link Store}
 *            will actually be stored and all fields annotated with {@link Store} must be a primitive type, {@link String}
 *            or {@link UUID}.
 */
public class MapDataManager<V extends Storable> {

    /**
     * Path to the file where all the data will be saved with {@link #save()}.
     */
    private final String path;
    /**
     * Instance of the plugin that uses this database.
     */
    private final JavaPlugin plugin;
    /**
     * Main map of all the data stored inside this database.
     */
    private Map<String, V> data = new HashMap<>();

    private final Class<V> clazz;

    /**
     * Constructs a new manager.
     *
     * @param path   Path to the file where all the data will be saved with {@link #save()}. Must end with {@code .json}.
     *               You can't choose a path outside your plugins data folder.
     * @param plugin Instance of the plugin that will use this database. Recommended to be {@code this}, assuming that
     *               you are constructing a database inside your {@link JavaPlugin#onEnable()} method.
     */
    public MapDataManager(String path, JavaPlugin plugin, Class<V> clazz) {
        if (!path.endsWith(".json")) throw new InvalidParameterException("path must end with .json");
        this.path = path;
        this.plugin = plugin;
        this.clazz = clazz;
    }

    /**
     * Grabs a data from the map using its {@link UUID}.
     *
     * @param id ID of the data you want to get.
     * @return Data if found, {@code null} otherwise.
     */
    @Nullable
    public V get(@NotNull String id) {
        if (data.containsKey(id)) return data.get(id);
        return null;
    }

    /**
     * Searches for a data with the given id and deletes the data found.
     *
     * @param id ID of the data you want to delete
     */
    public void delete(String id) {
        data.remove(id);
    }

    /**
     * Puts the given data to map.
     *
     * @param key  Key of the data in map.
     * @param data Data you want to put to the map.
     */
    public void set(String key, V data) {
        this.data.put(key, data);
    }

    /**
     * Saves all the data to the plugins data folder using the given path. It will save the data as a '.json' file, to
     * {@link MapDataManager#path} under plugins data folder.
     */
    public void save() {
        Gson gson = new Gson();

        Path absPath = Path.of(plugin.getDataFolder().getAbsolutePath(), path);

        File file = absPath.toFile();
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file, false);

            JsonObject object = new JsonObject();
            data.forEach((k, v) -> object.add(k, DataSerializer.write(v)));

            gson.toJson(object, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the data from the save before. You don't have to check if file exists, because method does it.
     */
    public void load() {
        Gson gson = new Gson();
        Path absPath = Path.of(plugin.getDataFolder().getAbsolutePath(), path);
        File file = absPath.toFile();

        if (file.exists()) {
            try {
                Reader reader = new FileReader(file);

                JsonObject object = gson.fromJson(reader, JsonObject.class);
                data = new HashMap<>();

                object.asMap().forEach((s, jsonElement) ->
                    data.put(s,DataSerializer.read(clazz,jsonElement.getAsJsonObject()))
                );

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs the entire map and returns it.
     *
     * @return All the map of data.
     */
    public Map<String, V> getAll() {
        return data;
    }
}
