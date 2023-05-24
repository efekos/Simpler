me.efekos.simpler.commands.
# BaseCommand

An abstract class used for base commands in your plugin. Base commands are simply commands like `/feed`, `/help` etc.

## Summary

### Methods

Abstract Methods:

| Method                    | Description                               | Returns |
|---------------------------|-------------------------------------------|---------|
| getSyntax()               | Returns a syntax for this command.        | Syntax  |
| onConsoleUse(sender,args) | Fires when the console uses this command. | void    |
| onPlayerUse(player,args)  | Fires when a player uses this command.    | void    |