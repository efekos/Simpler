<!-- TOC -->
* [Creating a Command With Simpler](#creating-a-command-with-simpler)
  * [Classes for Commands](#classes-for-commands)
  * [@Command](#command)
  * [Creating a Base Command](#creating-a-base-command)
  * [Registering a BaseCommand](#registering-a-basecommand)
  * [Creating a CoreCommand](#creating-a-corecommand)
    * [Creating a SubCommand](#creating-a-subcommand)
  * [Registering a CoreCommand](#registering-a-corecommand)
<!-- TOC -->

# Creating a Command With Simpler

There is a class called `CommandManager` in Simpler. We use this class to register our commands to the server. But before
registering a command, guess what? We have to make one! Let's talk about other classes about commands then.

## Classes for Commands

There are three main classes for registering commands. We can make other classes that extends these classes for our commands.

* `BaseCommand`s. Used for base commands that only have a name and arguments.
* `CoreCommand`s. Used for Core/Sub command system where there is a main command, a bunch of sub commands and arguments.
* And `SubCommand`s. Used to connect with `CoreCommand`s.

## @Command

Every `BaseCommand`, `CoreCommand` and `SubCommand` has to be annotated with `Command` annotation. This annotation is used
for various **final** values about the command.

`Command` annotation has these properties:

| Property    | Description                                                                                                                                                                                          | Type    | Default |
|-------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|---------|
| name        | Name of the command in `lower_case`.                                                                                                                                                                 | String  |         |
| description | A short and brief description about this command.                                                                                                                                                    | String  |         |
| playerOnly  | Defines that can this command be used by both console and players or is it a command only for player. When you set this to true in a `CoreCommand`, every `SubCommand` below it will be player-only. | Boolean | false   |
| permission  | A permission that every player will need to execute this command. When you change this in a `CoreCommand`, every `SubCommand` will require the permission too.                                       | String  | ""      |


Let's start with a basic base command: `/feed`!
## Creating a Base Command

First, we will create our class `Feed` for the command. Your class name can be whatever you want. Then, extend the class 
with `BaseCommand`. Your class should look like this.

````java
import me.efekos.simpler.commands.BaseCommand;

public class Feed extends BaseCommand {
    
}
````

Then, the most important part. We need to annotate our command with @`Command` annotation. Add an annotation like the one
below to your class.

````java
import me.efekos.simpler.commands.Command;
import me.efekos.simpler.commands.BaseCommand;

@Command(name = "feed", description = "Fulls your hunger and health!", permission = "examples.base.feed", playerOnly = true)
public class Feed extends BaseCommand {

}
````
I set `playerOnly` to true because the console must be unable to full its not existing hunger. If your command is something
that console shouldn't use too, then you can set `playerOnly` to true like I did above.

As you can tell from the errors your editor probably gave, we need to override some abstract methods. I use 'IntelliJ IDEA
Community Edition 2023.1.4' in this example, so I can do Ctrl+I to import the implement methods. But in case you are using
another IDE or text editor. The methods below are abstract, meaning you have to override them in your class.

* `BaseCommand#getSyntax()` - Used for syntax of your command.
* `BaseCommand#onPlayerUse(Player,String[])` - Executes when a player uses your command.
* `BaseCommand#onConsoleUse(ConsoleCommandSender,String[])` - Executes when the console uses your command.

> Don't forget to override the two constructors of `BaseCommand` as well. We won't touch or use them, but it is required
> to be in your extended class.

After we do all of this. Your class should look like this:

````java
import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.syntax.Syntax;
import org.jetbrains.annotations.NotNull;

@Command(name = "feed", description = "Fulls your hunger and health!", permission = "examples.base.feed", playerOnly = true)
public class Feed extends BaseCommand {
  public Feed(@NotNull String name) {
    super(name);
  }

  public Feed(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  @NotNull
  public Syntax getSyntax() {
    return new Syntax();
  }

  @Override
  public void onPlayerUse(Player player, String[] args) {

  }

  @Override
  public void onConsoleUse(ConsoleCommandSender sender, String[] args) {

  }
}
````

This is how you create a `BaseCommand`. Rest of this section will be specific to command we are making, /feed.

Everyone knows that /feed fulls your hunger, and health too in some plugins. So I'll make that.

Since our command will be executed by a player, I need to write my code inside `BaseCommnad#onPlayerUse(Player,String[])`.
It will be something like that:

````java
import me.efekos.simpler.commands.BaseCommand;

public class Feed extends BaseCommand{
    //...

    @Override
    public void onPlayerUse(Player player, String[] args) {
        player.setFoodLevel(20);
        player.setHealth(20);
        player.sendMessage("Fulled your health!");
    }
    
    //...
}
````

And finally, there is `Syntax`. I will explain this more at another tutorial, but for this one: It is simply a list of 
arguments for your command. /feed does not require any argument. So I have to make `BaseCommand#getSyntax` return an
empty `Syntax`. Like that:

````java
import me.efekos.simpler.commands.syntax.Syntax;

public class Feed extends BaseCommand {
    //...

    @Override
    public Syntax getSyntax(){
        return new Syntax();
    }

    //...
}
````

> **IMPORTANT** - Always make sure that `BaseCommand#getSyntax` returns a `Syntax` instance. It must not return null.

## Registering a BaseCommand

To register our `BaseCommand`, we will use `CommandManager`, a static manager class for commands. All we have to do is call `CommandManager#registerBaseCommand(JavaPlugin,Class)` at our `JavaPlugin#onEnable()` method like this:

````java
import me.efekos.simpler.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.efekos.example.base.commands.Feed;

public class Example extends JavaPlugin {
  @Override
  public void onEnable() {
    CommandManager.registerBaseCommand(this,Feed.class);
  }
}
````

## Creating a CoreCommand
Let's say we want to seperate the `/feed` command to two commands called `/full health` and `/full hunger`. In a case like
this, we might need a `CoreCommand` to be in action.

`CoreCommand`s are simply a tree containing `SubCommand`s inside them. So we won't do actual command stuff like we did in
`BaseCommand`s before.

First, we need a class extends `CoreCommand` like this:

```java
import me.efekos.simpler.commands.Command;
import me.efekos.simpler.commands.CoreCommand;
import me.efekos.simpler.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Command(name = "full", description = "Commands of fulling your stuff!", permission = "examples.core.full", playerOnly = true)
public class Full extends CoreCommand {

  public Full(@NotNull String name) {
    super(name);
  }

  public Full(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public @NotNull List<Class<? extends SubCommand>> getSubs() {
    return new ArrayList<>();
  }

  @Override
  public void renderHelpList(CommandSender sender, List<SubCommand> subInstances) {

  }
}
```

At this class, we have two important methods:

* **getSubs()** _-_ This method should return every `SubCommand` that will be under this `CoreCommand` in a list. You can
  use `Arrays.asList(...)` as the simplest method.
* **renderHelpList()** _-_ This method should send a help list to the `CommandSender` as message. You can use a for method
  to send messages for each command, combine it with a header and footer, and you are done!

Once you made these two methods work correctly, you can move on to the next step of creating a sub command.

### Creating a SubCommand

`SubCommand` is a superclass of `BaseCommand`, so you can create a sub command just like you did for base commands. All
you need to do is override these methods too:

* **getParent()** _-_ Should return `YourCoreCommandClass.class`.

## Registering a CoreCommand

To register a `CoreCommand`, all you have to do is call `CommandManager#registerCoreCommand` like the example below.

````java
import me.efekos.simpler.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.efekos.examples.core.commands.Full;

public class Examples extends JavaPlugin {
  @Override
  public void onEnable() {
    CommandManager.registerCoreCommand(this,Full.class);
  }
}
````