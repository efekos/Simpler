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

public class CustomItemRegistry{

    private Path getDataPath(JavaPlugin plugin){
        File dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();

        return Path.of(dataFolder.getPath(), "simpler_data");
    }

    public void save(JavaPlugin plugin, Map<UUID,CustomItem> items){

        Path simplerDataFolderPath = getDataPath(plugin);

        try {

            Path jsonFilePath = getItemsJsonPath(simplerDataFolderPath);

            Map<String, JsonObject> map = new HashMap<>();

            items.forEach((uuid, customItem) -> {

                JsonObject object = new JsonObject();

                object.addProperty("id",customItem.getKey().toString());

                customItem.putSaveFields(object);
                map.put(uuid.toString(),object);

            });

            JsonObject object = new JsonObject();
            map.forEach(object::add);

            jsonFilePath.toFile().createNewFile();
            FileWriter writer = new FileWriter(jsonFilePath.toFile());
            new Gson().toJson(object,writer);

            writer.flush();
            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @NotNull
    private static Path getItemsJsonPath(Path simplerDataFolderPath) throws IOException {
        if(!Files.exists(simplerDataFolderPath)) Files.createDirectory(simplerDataFolderPath);
        if(!Files.isDirectory(simplerDataFolderPath)) throw new NotDirectoryException(simplerDataFolderPath.toString());

        return Path.of(simplerDataFolderPath.toString(), "items.json");
    }

    public Map<UUID,CustomItem> load(JavaPlugin plugin){
        Path simplerDataPath = getDataPath(plugin);

        try {

            HashMap<UUID, CustomItem> map = new HashMap<>();

            Path jsonFilePath = getItemsJsonPath(simplerDataPath);
            if(!Files.exists(jsonFilePath)) return new HashMap<>();

            String s = Files.readString(jsonFilePath);
            JsonElement e = JsonParser.parseString(s);
            if(!e.isJsonObject()) throw new JsonParseException("Expected an object, got something else at plugin '"+plugin.getName()+"'");

            JsonObject object = e.getAsJsonObject();

            object.asMap().forEach((uuid, element) -> {
                UUID itemId = UUID.fromString(uuid);

                if(!element.isJsonObject()) throw new JsonParseException("Expected an object, got something else at plugin '"+plugin.getName()+"'");

                JsonObject itemObject = element.getAsJsonObject();

                Class<? extends CustomItem> itemClass = itemMap.get(NamespacedKey.fromString(itemObject.get("id").getAsString()));

                try {
                    Constructor<? extends CustomItem> constructor = itemClass.getConstructor();

                    CustomItem instance = constructor.newInstance();
                    instance.loadSaveFields(itemObject);
                    map.put(itemId,instance);

                } catch (Exception exception){
                    exception.printStackTrace();
                }

            });

            return map;

        } catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }

    }

    private final Map<NamespacedKey,Class<? extends CustomItem>> itemMap = new HashMap<>();

    public void registerItem(NamespacedKey key,Class<? extends CustomItem> item){
        if(itemMap.containsKey(key)) throw new IllegalStateException("Same key used twice: "+key);
        itemMap.put(key,item);
    }
}
