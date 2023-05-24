me.efekos.simpler.commands.syntax.
# Argument
Used to make custom arguments for command syntaxes. Arguments can take a placeholder, priority, and a list.

## Summary

### Methods

| Method                              | Description                                                              | Returns                    |
|-------------------------------------|--------------------------------------------------------------------------|----------------------------|
| getPlaceHolder()                    | Returns a place holder to describe players what this argument should be. | String                     |
| getList(Player player,String given) | Generates arguments for this argument to suggest.                        | ArrayList\<ArgumentResult> |
| getPriority()                       | Returns a priority for this argument.                                    | ArgumentPriority           |
| handleCorrection(String given)      | Checks that is the argument player gave correct.                         | Boolean                    |


## Methods

### getPlaceHolder()

Returns a placeholder to describe players what this argument should be. For example, you can make this placeholder "player" for an argument that should be a player's name.

### getList(player,given)

When someone tries to enter something for this argument, this method will be executed to get suggestions about this argument.

| Argument | Description                                                                           | Type   |
|----------|---------------------------------------------------------------------------------------|--------|
| player   | Player that is trying to enter a value for this argument, and needs to get suggestion | Player |
| given    | Current value player gave so far.                                                     | String |


### getPriority()

Returns a priority for this argument. If this argument returns ArgumentPriority.REQUIRED, it means that a player will need to enter this argument when executing a command that uses this argument.

### handleCorrection(given)

Checks if the given argument is correct. For example, if a player enters a PlayerArgument, but a player with given name does not exists, PlayerArgument#handleCorrection(String) should return false.

| Argument | Description                           | Type   |
|----------|---------------------------------------|--------|
| given    | Value a player gave for this argument | String |