> This documentation is cancelled and only will be for tutorials, due to there will be a JavaDoc for Simpler soon.

<!-- TOC -->
* [Introduction](#introduction)
  * [Features](#features)
    * [Commands](#commands)
    * [Items](#items)
    * [Menus](#menus)
<!-- TOC -->

# Introduction
Simpler is an API to help you to make Minecraft plugins. It has a lot of features to make developing a plugin much faster.

## Features

### Commands
Command features helps you to make commands, getting rid of some `if` statements and providing advanced syntaxes for your command. You can use a `BaseCommand`, but you can also use `CoreCommand` with a `SubCommand` for creating core commands that contains other sub commands inside of it.

### Items
Item features makes creating custom items so much easier. All you have to do is create a `CustomItem` class and that's it! Now you can use this custom item through `ItemManager#giveItem(Player,CustomItem)` and listen for your custom events.

### Menus
Menu features create custom menus, and let you listen for them. It's a good way to make use interfaces using GUI's.


Starting to use Simpler in your project is really simple. All you need to do is [install](Installation.md) Simpler as a dependecy in your project.