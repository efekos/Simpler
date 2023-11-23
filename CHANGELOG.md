> Note: This changelog started at Simpler 1.5. For changelog of older versions, see github releases.

# 1.5

* **Added** copyright to every class.
* **Added** proper built-in documentation (JavaDoc Coming Soon).
* **Added** `me.efekos.simpler.items.ItemContent`: Serialize an ItemStack to show it in chat **WITHOUT NMS**.
* **Added** `me.efekos.simpler.config.ListDataManager<T>`: Store a list of the datas in '.json' format.
* **Added** `LICENSE.md`
* **Added** `package-info.java` files.
* **Added** `me.efekos.simpler.config.Storable`: Makes your class compatible for `JSONDataManager<T>`.
* **Changed** License to MIT.
* **Changed** most of the `ArrayList<...>` type to `List<...>`
* **Changed** the tutorial 'Creating A Command': Core and sub commands.
* **Changed** `CustomItem#CustomItem`: Now it is possible to create a `CustomItem` using a prepared `UUID` or a random `UUID`.
* **Changed** spigot-api to _1.20.1-R0.1-SNAPSHOT_ (was _1.19-R0.1-SNAPSHOT_ before).
* **Fixed** `NumberArgument` not using the priority given at constructor
* **Removed** `CustomItem#setUniqueItemId`
* **Removed** `Simpler#onEnable`: Useless
* **Removed** `Simpler#onDisable`: Useless
* **Moved** `me.efekos.simpler.commands.syntax.ListArgument` to `me.efekos.simpler.commands.syntax.impl.ListArgument`
* **Moved** `me.efekos.simpler.commands.syntax.StringArgument` to `me.efekos.simpler.commands.syntax.impl.StringArgument`
* **Moved** `me.efekos.simpler.commands.syntax.NumberArgument` to `me.efekos.simpler.commands.syntax.impl.NumberArgument`
* **Moved** `me.efekos.simpler.commands.syntax.PlayerArgument` to `me.efekos.simpler.commands.syntax.impl.PlayerArgument`
* **Moved** `me.efekos.simpler.commands.translation.TranslateManager` to `me.efekos.simpler.translation.TranslateManager`

## 1.5.1
* **Fixed** `CoreCommand#getSub` not working.

## 1.5.2
* **Added** Permission checks to tab completion, you will no longer see tab completion for commands that you don't have permission for.
* **Added** [JavaDoc](https://efekos.github.io/Simpler) for Simpler
* **Added** `MessageConfiguration`: Change the base messages of Simpler.
* **Added** `Simpler#changeMessageConfiguration(MessageConfiguration configuration)`.
* **Added** `ArgumentHandleResult`: Now you can give reasons to players, so they won't mess up arguments again.

## 1.5.3
* **Fixed** a small issue I forgot about in the time I'm writing this.

## 1.5.4
* **Added** `MapDataManager`: Store JSON data in Map format.
* **Changed** `Storable` to be an interface.
* **Deprecated** `JSONDataManager`: Use `ListDataManager` (removal in 1.6)
* **Renamed** `JSONDataManager` to `ListDataManager`

## 1.5.5
* **Fixed** An issue causing due to optional parameters in `CoreCommand`

## 1.5.6
* **Fixed** The same issue, I hope.

## 1.5.7
* **Fixed** The same issue. I won't go further today if it isn't fixed.

## 1.5.8
* **Added** `JsonConfig`: Configuration with JSON files! If you see any bug while using this, please open an issue about it.
* **Changed** `ItemContent`: Now it works for only 1.20.2. Also made every subclass of `ItemContent` different classes. See `@.items.compound` and `@.items.tag` package for more information.
* **Renamed** `Config` to `YamlConfig`

# 1.6

* **Added** `CommandTree`: Make commands that can go infinite subs.
* **Added** `CommandNode`: Main command node that `CommandTree`s use.
* **Added** `ArgumentNode`: Main node for arguments.
* **Added** `LabelNode`: Just one label, like a sub command.
* **Added** `IntegerArgumentNode`: Number argument for `CommandTree`s that only accepts Integer.
* **Added** `PlayerArgumentNode`: Player argument for `CommandTree`s.
* **Added** `StringArgumentNode`: String argument for `CommandTree`s.
* **Added** `ListArgumentNode`: List argument for `CommandTree`s.
* **Added** `CommandExecutive`: Main interface for classes that contains an executing code for `CommandTree`s.
* **Added** `CommandExecuteContext`: Main context class used by `CommandExecutive`.
* **Added** `CommandManager#registerCommandTree`: Register a command tree you created.
* **Added** `TreeCommand`: Actual command that handles a `CommandTree`.
* **Renamed** `NumberArgument` to `IntegerArgument`.
* **Removed** `JSONDataManager`: Please use `ListDataManager`