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

import com.google.gson.*;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A registry class used for registering custom items. Loads and saves custom item data. You can't access the instance
 * stored as {@link ItemManager}, but you can use it with the methods {@link ItemManager#registerItem(NamespacedKey, Class)},
 * {@link ItemManager#loadCustomItems()} and {@link ItemManager#saveCustomItems()}
 */
public final class CustomItemRegistry {

    /**
     * Returns the {@link Path} simpler will create inside the given plugin's folder.
     *
     * @param plugin The plugin that is currently using this registry.
     * @return A path that leads do a directory called 'simpler_data' under the given plugin's data folder.
     */
    private Path getDataPath(JavaPlugin plugin) {
        File dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();

        return Path.of(dataFolder.getPath(), "simpler_data");
    }

    /**
     * Saves the given data to a JSON file under the data folder of the plugin given.
     *
     * @param plugin The plugin that is currently using this registry.
     * @param items  A map of all the {@link CustomItem} instances.
     */
    public void save(JavaPlugin plugin, Map<UUID, CustomItem> items) {

        Path simplerDataFolderPath = getDataPath(plugin);

        try {

            Path jsonFilePath = getItemsJsonPath(simplerDataFolderPath);

            Map<String, JsonObject> map = new HashMap<>();

            items.forEach((uuid, customItem) -> {

                JsonObject object = new JsonObject();

                object.addProperty("id", customItem.getKey().toString());

                customItem.putSaveFields(object);
                map.put(uuid.toString(), object);

            });

            JsonObject object = new JsonObject();
            map.forEach(object::add);

            jsonFilePath.toFile().createNewFile();
            FileWriter writer = new FileWriter(jsonFilePath.toFile());
            new Gson().toJson(object, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Does some checks and returns the 'items.json' file path.
     *
     * @param simplerDataFolderPath 'simpler_data' folder path.
     * @return A path that leads to the 'items.json' file.
     * @throws IOException If an IO process fails.
     */
    @NotNull
    private static Path getItemsJsonPath(Path simplerDataFolderPath) throws IOException {
        if (!Files.exists(simplerDataFolderPath)) Files.createDirectory(simplerDataFolderPath);
        if (!Files.isDirectory(simplerDataFolderPath))
            throw new NotDirectoryException(simplerDataFolderPath.toString());

        return Path.of(simplerDataFolderPath.toString(), "items.json");
    }

    /**
     * Loads custom item data from a JSON file under the data folder of the plugin given.
     *
     * @param plugin The plugin that is currently using this registry.
     * @return A map of all the {@link CustomItem} instances loaded.
     */
    public Map<UUID, CustomItem> load(JavaPlugin plugin) {
        Path simplerDataPath = getDataPath(plugin);

        try {

            HashMap<UUID, CustomItem> map = new HashMap<>();

            Path jsonFilePath = getItemsJsonPath(simplerDataPath);
            if (!Files.exists(jsonFilePath)) return new HashMap<>();

            String s = Files.readString(jsonFilePath);
            JsonElement e = JsonParser.parseString(s);
            if (!e.isJsonObject())
                throw new JsonParseException("Expected an object, got something else at plugin '" + plugin.getName() + "'");

            JsonObject object = e.getAsJsonObject();

            object.asMap().forEach((uuid, element) -> {
                UUID itemId = UUID.fromString(uuid);

                if (!element.isJsonObject())
                    throw new JsonParseException("Expected an object, got something else at plugin '" + plugin.getName() + "'");

                JsonObject itemObject = element.getAsJsonObject();

                Class<? extends CustomItem> itemClass = itemMap.get(NamespacedKey.fromString(itemObject.get("id").getAsString()));

                try {
                    Constructor<? extends CustomItem> constructor = itemClass.getConstructor();

                    CustomItem instance = constructor.newInstance();
                    instance.loadSaveFields(itemObject);
                    map.put(itemId, instance);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            });

            return map;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }

    /**
     * A map of the items registered.
     */
    private final Map<NamespacedKey, Class<? extends CustomItem>> itemMap = new HashMap<>();

    /**
     * Registers a custom item class.
     *
     * @param key  Identifier of this custom item type.
     * @param item Class of the custom item.
     * @throws IllegalStateException if you use the same identifier more than once.
     */
    public void registerItem(NamespacedKey key, Class<? extends CustomItem> item) {
        if (itemMap.containsKey(key)) throw new IllegalStateException("Same key used more than once: " + key);
        itemMap.put(key, item);
    }

    /**
     * A non-public constructor to prevent people from creating new instances of {@link CustomItemRegistry}.
     */
    CustomItemRegistry() {
    }
}
