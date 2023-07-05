package `me.efekos.simpler.annotations`
extends java.lang.Object

# @Listen

It simply tells to ItemManager that your method should be invoked on item events. Methods with @Listen must:

* Have only one argument. It can be:
 - EntityItemPickupEvent
 - PlayerInteractEvent
 - PlayerDropItemEvent
 - PlayerItemBreakEvent
 - PlayerItemDamageEvent
 - PlayerItemMendEvent
 - PlayerItemConsumeEvent
 - PlayerItemHeldEvent
* Return `void`
* Have only `public`