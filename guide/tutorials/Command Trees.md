# Command Trees

You can create big command structures using `CommandTree`s. And unlike other systems, you don't have to create an entire
class. All you need to do is create a new `CommandTree` instance.

## Using `CommandNode`s

`CommandTree` is basically a tree that can hold infinite amount of `CommandNode`s. There are 7 different node
implementations built-in:

* `DoubleArgumentNode`
* `EnumArgumentNode`
* `IntegerArgumentNode`
* `LabelNode`
* `ListArgumentNode`
* `PlayerArgumentNode`
* `StringArgumentNode` (doesn't support rest arguments, you can't take two words.)

From this list, the one you will use the most is `LabelNode`. This node basically acts like a sub command. And all the
other nodes are `ArgumentNode`s.

> If you want to make your custom nodes for custom arguments, make sure to extend `ArgumentNode`, not `CommandNode`.

There is an example of creating a command tree below.

````java
new CommandTree("repair","Repair your stuff!","myplugin.cmd.repair")
    .addChild(new LabelNode("armor"))
    .addChild(new LabelNode("inventory"))
    .addChild(new LabelNode("hand")
        .addChild(new LabelNode("main"))
        .addChild(new LabelNode("off"))
    );
````

You can use set methods, such as `#setPermission`, `#setDescription` to improve this tree.

```java
new CommandTree("repair","Repair your stuff!","myplugin.cmd.repair")
    .addChild(new LabelNode("armor").setDescription("Repairs your armor").setPermission("myplugin.cmd.repair.armor"))
    .addChild(new LabelNode("inventory").setDescription("Repairs your inventory").setPermission("myplugin.cmd.repair.inventory"))
    .addChild(new LabelNode("hand").setPermission("myplugin.cmd.repair.hand") // both labels below will require it because of this.
        .addChild(new LabelNode("main").setDescription("Repairs the item in your main hand"))
        .addChild(new LabelNode("off").setDescription("Repairs the item in your off hand"))
    );
```

You can add arguments just like `LabelNode`s, the only noticeable difference is constructors.

```java
new CommandTree("repair","Repair your stuff!","myplugin.cmd.repair")
    .addChild(new LabelNode("armor").setDescription("Repairs your armor").setPermission("myplugin.cmd.repair.armor").addChild(new IntegerArgumentNode(0,150)))
    .addChild(new LabelNode("inventory").setDescription("Repairs your inventory").setPermission("myplugin.cmd.repair.inventory").addChild(new IntegerArgumentNode(0,150)))
    .addChild(new LabelNode("hand").setPermission("myplugin.cmd.repair.hand") // both labels below will require it because of this.
        .addChild(new LabelNode("main").setDescription("Repairs the item in your main hand").addChild(new IntegerArgumentNode(0,150)))
        .addChild(new LabelNode("off").setDescription("Repairs the item in your off hand").addChild(new IntegerArgumentNode(0,150)))
    );
```

And finally, command executives. Executives are the main code of your command. You can create a class that implements
`CommandExecutive` if you would like to. For this example, I'll just make every endpoint say 'Hello!'.

```java
new CommandTree("repair","Repair your stuff!","myplugin.cmd.repair")
    .addChild(new LabelNode("armor").setDescription("Repairs your armor").setPermission("myplugin.cmd.repair.armor").addChild(new IntegerArgumentNode(0,150).setExecutive(context -> context.getSender().sendMessage("Hello!"))))
    .addChild(new LabelNode("inventory").setDescription("Repairs your inventory").setPermission("myplugin.cmd.repair.inventory").addChild(new IntegerArgumentNode(0,150).setExecutive(context -> context.getSender().sendMessage("Hello!"))))
    .addChild(new LabelNode("hand").setPermission("myplugin.cmd.repair.hand") // both labels below will require it because of this.
        .addChild(new LabelNode("main").setDescription("Repairs the item in your main hand").addChild(new IntegerArgumentNode(0,150).setExecutive(context -> context.getSender().sendMessage("Hello!"))))
        .addChild(new LabelNode("off").setDescription("Repairs the item in your off hand").addChild(new IntegerArgumentNode(0,150).setExecutive(context -> context.getSender().sendMessage("Hello!"))))
    );
```

## Registering a CommandTree

After you are done with your `CommandTree`, call `CommandManager#registerCommandTree` in your onEnable method.