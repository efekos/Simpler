# Introduction
Simpler is an API to help you making Minecraft plugins. It has a lot of features to make developing a plugin much faster.

## Features

### Commands
Command features helps you making commands, getting rid of some `if` statements and providing advanced syntaxes for your command. You can use a [BaseCommand](Classes/BaseCommand.md), but you can also use [SubCommand](Classes/SubCommand.md) with a [CoreCommand](Classes/CoreCommand.md) for creating core commands that contains other sub commands inside of it.

### Items
Item features makes creating custom items so much easier. All you have to do is create a [CustomItem](Classes/CustomItem.md) class and that's it! Now you can use this custom item through [ItemManager](Classes/ItemManager.md)[#giveItem(](Classes/ItemManager.md#give-item)) and listen for your custom events.

### Menus
Menu features create custom menus, and let you listen for them. It's a good way to make use interfaces using GUI's.


+Starting to use Simpler in your project is really simple. All you need to do is [install](Installation.md) Simpler as a dependecy in your project.