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

import com.google.gson.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A basic database class made using {@link Gson}. You can store a {@link List<T>} in this data. Use {@link #save()}
 * and {@link #load()} to load your data.
 * @param <T> Type of the data you want to store as a list.
 */
public class ListDataManager<T extends Storable> {

    /**
     * Main list of all the data stored inside this database.
     */
    private List<T> datas = new ArrayList<>();
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
    public ListDataManager(String path, JavaPlugin plugin) {
        if(!path.endsWith(".json")) throw new InvalidParameterException("path must end with .json");
        this.path = path;
        this.plugin = plugin;
    }

    /**
     * Grabs a data from the list using its {@link UUID}.
     * @param id ID of the data you want to get.
     * @return Data if found, {@code null} otherwise.
     */
    @Nullable
    public T get(@NotNull UUID id){
        for (T data : datas) {
            if(data.getUniqueId().equals(id)) return data;
        }
        return null;
    }

    /**
     * Searches for a data with the given id and deletes the data found.
     * @param id ID of the data you want to delete
     */
    public void delete(UUID id){
        List<T> newList = new ArrayList<>();

        for (T data : datas) {
            if(!data.getUniqueId().equals(id))newList.add(data);
        }

        datas = newList;
    }

    /**
     * Adds the given data to list.
     * @param data Data you want to add to the list.
     */
    public void add(T data){
        datas.add(data);
    }

    /**
     * Finds a data with the given id, and replaces it with the new one.
     * @param id ID of the data you want to update. You can just do {@link T#getUniqueId()} for this.
     * @param newData New data to replace with the old one
     * @return New data.
     */
    public T update(UUID id,T newData){
        delete(id);
        add(newData);
        return newData;
    }

    /**
     * Saves all the data to the plugins data folder using the given path. It will save the data as a '.json' file, to
     * {@link ListDataManager#path} under plugins data folder.
     */
    public void save(){
        Gson gson = new Gson();

        String absPath = plugin.getDataFolder().getAbsolutePath()+path;

        File file = new File(absPath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file,false);

            JsonArray array = new JsonArray(Integer.MAX_VALUE);
            datas.stream().map(Storable::serialize).forEach(array::add);

            gson.toJson(array,writer);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Loads all the data from the save before. You don't have to check if file exists, because method does it.
     */
    public void load(){
        Gson gson = new Gson();
        String absPath = plugin.getDataFolder().getAbsolutePath()+path;
        File file = new File(absPath);

        if(file.exists()){

            try {
                String jsonString = String.join("", Files.readAllLines(file.toPath()));

                JsonElement array = JsonParser.parseString(jsonString);
                if(!array.isJsonArray()) throw new JsonParseException("Data is not list.");
                array.getAsJsonArray().forEach(element -> {
                    new T().deserialize(element);
                    gson.tojson
                });

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Grabs the entire list and returns it.
     * @return All the list of datas.
     */
    public List<T> getAll(){
        return datas;
    }
}
