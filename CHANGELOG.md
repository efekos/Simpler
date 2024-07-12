> Note: This changelog started at Simpler 1.5. For changelog of older versions, see github releases.

# 1.5

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added copyright to every class.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added proper built-in documentation (JavaDoc Coming Soon).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `me.efekos.simpler.items.ItemContent`: Serialize an ItemStack to show it in chat **WITHOUT NMS**.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `me.efekos.simpler.config.ListDataManager<T>`: Store a list of the datas in '.json' format.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `LICENSE.md`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `package-info.java` files.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `me.efekos.simpler.config.Storable`: Makes your class compatible for `JSONDataManager<T>`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed License to MIT.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed most of the `ArrayList<...>` type to `List<...>`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed the tutorial 'Creating A Command': Core and sub commands.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CustomItem#CustomItem`: Now it is possible to create a `CustomItem` using a prepared `UUID` or a random `UUID`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed spigot-api to _1.20.1-R0.1-SNAPSHOT_ (was _1.19-R0.1-SNAPSHOT_ before).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed `NumberArgument` not using the priority given at constructor.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `CustomItem#setUniqueItemId`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `Simpler#onEnable`: Useless.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `Simpler#onDisable`: Useless.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `me.efekos.simpler.commands.syntax.ListArgument` to `me.efekos.simpler.commands.syntax.impl.ListArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `me.efekos.simpler.commands.syntax.StringArgument` to `me.efekos.simpler.commands.syntax.impl.StringArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `me.efekos.simpler.commands.syntax.NumberArgument` to `me.efekos.simpler.commands.syntax.impl.NumberArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `me.efekos.simpler.commands.syntax.PlayerArgument` to `me.efekos.simpler.commands.syntax.impl.PlayerArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `me.efekos.simpler.commands.translation.TranslateManager` to `me.efekos.simpler.translation.TranslateManager`.\

## 1.5.1

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed `CoreCommand#getSub` not working.

## 1.5.2
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added Permission checks to tab completion, you will no longer see tab completion for commands that you don't have permission for.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added [JavaDoc](https://efekos.github.io/Simpler) for Simpler.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `MessageConfiguration`: Change the base messages of Simpler.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `Simpler#changeMessageConfiguration(MessageConfiguration configuration)`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ArgumentHandleResult`: Now you can give reasons to players, so they won't mess up arguments again.

## 1.5.3
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed a small issue I forgot about in the time I'm writing this.

## 1.5.4
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `MapDataManager`: Store JSON data in Map format.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `Storable` to be an interface.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-deprecated.svg) Deprecated `JSONDataManager`: Use `ListDataManager` (removal in 1.6).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Renamed `JSONDataManager` to `ListDataManager`

## 1.5.5
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed An issue causing due to optional parameters in `CoreCommand`

## 1.5.6
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed The same issue, I hope.

## 1.5.7
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed The same issue. I won't go further today if it isn't fixed.

## 1.5.8
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `JsonConfig`: Configuration with JSON files! If you see any bug while using this, please open an issue about it.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `ItemContent`: Now it works for only 1.20.2. Also made every subclass of `ItemContent` different classes. See `@.items.compound` and `@.items.tag` package for more information.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Renamed `Config` to `YamlConfig`

# 1.6

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandTree`: Make commands that can go infinite subs.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandNode`: Main command node that `CommandTree`s use.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ArgumentNode`: Main node for arguments.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `LabelNode`: Just one label, like a sub command.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `IntegerArgumentNode`: Number argument for `CommandTree`s that only accepts Integer.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `PlayerArgumentNode`: Player argument for `CommandTree`s.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `StringArgumentNode`: String argument for `CommandTree`s.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ListArgumentNode`: List argument for `CommandTree`s.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandExecutive`: Main interface for classes that contains an executing code for `CommandTree`s.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandExecuteContext`: Main context class used by `CommandExecutive`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandManager#registerCommandTree`: Register a command tree you created.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `TreeCommand`: Actual command that handles a `CommandTree`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `PaginatedMenu`: A menu that shows any amount of `ItemStack`s using page system.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Renamed `NumberArgument` to `IntegerArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `JSONDataManager`: Please use `ListDataManager`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed Java version to 16.

## 1.6.1

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed Java version to 17.

## 1.6.2

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed a line of code that shouldn't exist in the final build.

## 1.6.3

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandExecuteContext#getSenderAsPlayer`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandExecuteContext#getSenderAsConsole`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CommandExecuteContext` to be a class.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CommandExecuteContext#args` to `CommandExecuteContext#getArgs`\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CommandExecuteContext#sender` to `CommandExecuteContext#getSender`

## 1.6.4

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandExecutive.consoleExecutive`: Executive that only runs when the sender is console. Old executive will
keep running if the sender is a player.

## 1.6.5

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandNode.description` - Going to be used for auto-generated help menus.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandNode.parent` - Going to be used for auto-generated help menus.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed License year to 2024.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `Simpler` class to be a class that doesn't extend `JavaPlugin`, because it isn't necessary.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `plugin.yml` from resources.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-deprecated.svg) Deprecated `ItemContent`, and it's needed classes. Everything here will be moved into [another repository](https://github.com/efekos/ItemContent).

## 1.6.6

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `BaseCommand#hasPermission`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CoreCommand#hasPermission`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ArgumentHandleResult#hasReason`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `DoubleArgument`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `DoubleArgumentNode`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `EnumArgument`: List argument but for enums.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `EnumArgumentNode`: List argument but for enums.

## 1.6.7

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed A bug that prevents players from executing any `SubCommand` or `CoreCommand`

# 1.7

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `HandleEvent`: Annotation to put to your custom item event listeners.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `HandleType`: Determines the type of the handler.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `SaveField`: Lets you save the value of that field.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `FieldType`: Type of the field you want to save.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CustomItemRegistry`: Register `CustomItem`s using this registry.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ItemManager#isCustom(ItemStack)`: Returns true if given stack is a custom item.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `ItemManager#getItem(ItemStack)`: Returns the custom item instance of the stack.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `SubOf`: Add to your sub commands.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `Simpler#registerCommands(JavaPlugin)`: Scans your classes and registers commands.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `TreeCommandHandler`: Annotate with @`CommandTreeHandler`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `CommandTreeHandler`: Used for classes extending `TreeCommandHandler`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CoreCommand#getSubs` to be not abstract because you don't have to manually add sub commands anymore.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `CustomItem`'s entire behaviour.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-deprecated.svg) Deprecated `CommandManager#registerCoreCommand(JavaPlugin,Class<? extends CoreCommand>)`: You have to define sub
command classes yourself.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `@.annotations.Command` to `@.commands.Command`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `@.events` package: Event classes now need access to non-public methods.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `@.items.compound`: Moved into [another repository](https://github.com/efekos/ItemContent).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `@.items.tag`: Moved into [another repository](https://github.com/efekos/ItemContent).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `@.items.ItemContent`: [another repository](https://github.com/efekos/ItemContent).\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-minus.svg) Removed `@.annotations` package: Useless

## 1.7.1

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added icons to changelogs.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Changed `ListDataManager` and `MapDataManager`'s `#load` methods. Now they don't need a parameter.

## 1.7.2

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-fix.svg) Fixed a bug that saves files to wrong places in data managers when the plugin is running on an ubuntu server

# 1.8

![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `@.config.data`: All classes about data.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `@.config.data.DataSerializer`: Main serializer object to read and write data.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `@.config.data.Store`: Annotation used to store fields.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-plus.svg) Added `@.config.data.UniqueID`: Annotation used to mark unique fields.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `@.config.ListDataManager` to `@.config.data.ListDataManager`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-moved.svg) Moved `@.config.MapDataManager` to `@.config.data.MapDataManager`.\
![](https://raw.githubusercontent.com/efekos/efekos/main/icons/symbol-change.svg) Updated SpigotMC version to 1.21