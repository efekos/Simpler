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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import me.efekos.simpler.config.Storable;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A basic database class made using {@link Gson}. You can store a {@link List}<{@link T}> in this data. Use {@link #save()}
 * and {@link #load()} to load your data.
 *
 * @param <T> Type of the data that you want to store in the list. Beware that only fields annotated with {@link Store}
 *            will actually be stored and all fields annotated with {@link Store} must be a primitive type, {@link String}
 *            or {@link UUID}.
 */
public class ListDataManager<T extends Storable> {

    /**
     * Path to the file where all the data will be saved with {@link #save()}.
     */
    private final String path;
    /**
     * Instance of the plugin that uses this database.
     */
    private final JavaPlugin plugin;
    /**
     * Main list of all the data stored inside this database.
     */
    private List<T> datas = new ArrayList<>();

    private final Class<T> clazz;

    /**
     * Constructs a new manager.
     *
     * @param path   Path to the file where all the data will be saved with {@link #save()}. Must end with {@code .json}.
     *               You can't choose a path outside your plugins data folder.
     * @param plugin Instance of the plugin that will use this database. Recommended to be {@code this}, assuming that
     *               you are constructing a database inside your {@link JavaPlugin#onEnable()} method.
     */
    public ListDataManager(String path, JavaPlugin plugin, Class<T> clazz) {
        if (!path.endsWith(".json")) throw new InvalidParameterException("path must end with .json");
        this.path = path;
        this.plugin = plugin;
        this.clazz = clazz;
    }

    /**
     * Grabs a data from the list using its {@link UUID}.
     *
     * @param id ID of the data you want to get.
     * @return Data if found, {@code null} otherwise.
     */
    @Nullable
    public T get(@NotNull UUID id) {
        return datas.stream().filter(t -> t.getUniqueId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Searches for a data with the given id and deletes the data found.
     *
     * @param id ID of the data you want to delete
     */
    public void delete(UUID id) {
        List<T> newList = new ArrayList<>();

        for (T data : datas) {
            if (!data.getUniqueId().equals(id)) newList.add(data);
        }

        datas = newList;
    }

    /**
     * Adds the given data to list.
     *
     * @param data Data you want to add to the list.
     */
    public void add(T data) {
        datas.add(data);
    }

    /**
     * Finds a data with the given id, and replaces it with the new one.
     *
     * @param id      ID of the data you want to update. You can just do {@link T#getUniqueId()} for this.
     * @param newData New data to replace with the old one
     * @return New data.
     */
    public T update(UUID id, T newData) {
        delete(id);
        add(newData);
        return newData;
    }

    /**
     * Saves all the data to the plugins data folder using the given path. It will save the data as a '.json' file, to
     * {@link ListDataManager#path} under plugins data folder.
     */
    public void save() {
        Gson gson = new Gson();

        File file = Path.of(plugin.getDataFolder().getAbsolutePath(), path).toFile();

        file.getParentFile().mkdir();
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            JsonArray array = new JsonArray();
            datas.stream().map(DataSerializer::write).forEach(array::add);
            gson.toJson(array, writer);
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
        Path path1 = Path.of(plugin.getDataFolder().getAbsolutePath(), path);

        if (Files.exists(path1)) {
            try {
                String s = Files.readString(path1, StandardCharsets.UTF_8);

                datas.clear();
                for (JsonElement element : gson.fromJson(s, JsonArray.class)) datas.add(DataSerializer.read(clazz,element.getAsJsonObject()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs the entire list and returns it.
     *
     * @return All the list of datas.
     */
    public List<T> getAll() {
        return datas;
    }
}