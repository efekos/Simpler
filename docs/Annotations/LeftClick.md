package `me.efekos.simpler.annotations`\
extends [java.lang.Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html)

# @LeftClick

While making custom items, Simpler checks right click and left clicks to the item with [PlayerInteractEvent](https://helpch.at/docs/1.19.2/org/bukkit/event/player/PlayerInteractEvent.html). But you will need to separate Left-Click event and Right-Click event when registering events for your custom item. And to do this, you should use @LeftClick and @RightClick.