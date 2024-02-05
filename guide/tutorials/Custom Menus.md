# Creating Custom Menus

To make custom menus using Simpler, you can use `MenuManager`. First, take a look at four static methods of this class:

| Signature                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | Description                                                                                                                                                                                                                                                                                                                                                              |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [setPlugin\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#setPlugin(org.bukkit.plugin.java.JavaPlugin))[JavaPlugin](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/plugin/java/JavaPlugin.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)                                                                                                                                                                                                                                                                                                          | Registers event listeners using your plugin instance.                                                                                                                                                                                                                                                                                                                    |
| [Open\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#Open(org.bukkit.entity.Player,java.lang.Class))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html), [Class\<](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Class.html)`?` extends [Menu](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html)[\>](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Class.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#Open(org.bukkit.entity.Player,java.lang.Class)) | Creates an instance of the given menu, and opens it to the player given.                                                                                                                                                                                                                                                                                                 |
| [getMenuData\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#getMenuData(org.bukkit.entity.Player))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#getMenuData(org.bukkit.entity.Player))                                                                                                                                                                                                                                                                                      | Returns the current [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData)) for the player given.                                                                                                                                                                  |
| [updateMenuData\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html), [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData))                                                                                                                          | Updates [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData)) of the given player using the [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData)) given. |

We'll get to using [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html)s later. So don't worry about those.

## Making a menu

Make a class that extends [Menu](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html). Like the example below:

```java
public class CoolMenu extends Menu {
    public CoolMenu(MenuData data) {
        super(data);
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public int getRows() {
        return 3; // Inventory will have rows*9 slots.e
    }

    @Override
    public String getTitle() {
        return ChatColor.GREEN+"My Cool Menu";
    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

    }

    @Override
    public void fill() {
        // fill the inventory with items here. use this.inventory
    }
}
```

You can do whatever you want to customize your menu. You can take a look at [Menu](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html)'s
methods. There are some methods to help you fill the menu, like
[Menu](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html)[\#createItem\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html#createItem(org.bukkit.Material,java.lang.String,java.lang.String...))[Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html), [String](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/String.html), [String...](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/String.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html#createItem(org.bukkit.Material,java.lang.String,java.lang.String...)).

## Using the menu

If you want to use [MenuManager](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html) in anywhere of you plugin, you have to call
[MenuManager](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)[#setPlugin\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#setPlugin(org.bukkit.plugin.java.JavaPlugin))[JavaPlugin](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/plugin/java/JavaPlugin.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)
in your plugin's `onEnable` method. This method registers event listeners needed using your plugin. After doing that, you are done! Now you can call [MenuManager](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)[#Open\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#Open(org.bukkit.entity.Player,java.lang.Class))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html), [Class\<](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Class.html)`?` extends [Menu](https://efekos.github.io/Simpler/me/efekos/simpler/menu/Menu.html)[\>](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Class.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#Open(org.bukkit.entity.Player,java.lang.Class))
at anywhere to open your custom menu to a player. For example, let's make a command that opens a menu.

````java
@Command(name = "coolmenu",description = "Opens the cool menu!",playerOnly = true,permission = "plugin.coolmenu")
public class CoolMenuCommand extends BaseCommand {
    public CoolMenuCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public @NotNull Syntax getSyntax() {
        return new Syntax();
    }

    @Override
    public void onPlayerUse(Player player, String[] args) {
        MenuManager.Open(player,CoolMenu.class); // this is the thing!
    }

    @Override
    public void onConsoleUse(ConsoleCommandSender sender, String[] args) {

    }
}
````

## Using [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html)s

[MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html) class is the way you need to communicate
between two menus. Two main methods you'll use all the time is tabled below:

| Signature                                                                                                                                                                                                                                                                                                                                                                                                                         | Description                                            |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|
| [get\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html#get(java.lang.String))[String](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/String.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html#get(java.lang.String))                                                                                                                                 | Gets a value from the stored data, using the key given |
| [set\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html#set(java.lang.String,java.lang.Object))[String](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/String.html), [Object](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Object.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html#set(java.lang.String,java.lang.Object)) | Sets a value for the key given.                        |

You can get and update someone's [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html) using
[MenuManager](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)[#getMenuData\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#getMenuData(org.bukkit.entity.Player))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#getMenuData(org.bukkit.entity.Player)) and
[MenuManager](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html)[#updateMenuData\(](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData))[Player](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html), [MenuData](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html)[\)](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuManager.html#updateMenuData(org.bukkit.entity.Player,me.efekos.simpler.menu.MenuData)).
You won't use other methods that often, except [#lastMenu()](https://efekos.github.io/Simpler/me/efekos/simpler/menu/MenuData.html#lastMenu()), which returns the last menu the player had opened, so you can make 'Back' buttons.