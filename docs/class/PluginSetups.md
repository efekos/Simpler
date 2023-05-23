# Plugin Setups

## Summary

###Methods

| Method                                                        | Description                                           | Returns                                                          |
|---------------------------------------------------------------|-------------------------------------------------------|------------------------------------------------------------------|
| static boolean checkUpdates(JavaPlugin plugin,int resourceId) | Checks updates with the given plugin and resource id. | Is this plugin up-to-date according to the resource on SpigotMC? |
| static Metrics setupMetrics(JavaPlugin plugin,int serviceId)  | Setup's a Metrics class for your bStats.              | A Metrics that collects information about the plugin given       |

## Methods

### static boolean checkUpdates(JavaPlugin plugin,int resourceId)
Checks updates with the given plugin and resource id.
- plugin - The plugin to check updates.
- resourceId - Resource ID of the plugin on SpigotMC.

### static Metrics setupMetrics(JavaPlugin plugin,int serviceId)
Setup's a Metrics class for your bStats.
- plugin - The plugin to setup Metrics on it.
- serviceId - Service id of this plugin in bStats.