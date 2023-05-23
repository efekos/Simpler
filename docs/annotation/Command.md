# Command
`public @interface`\
For `class`

Defines some properties about a Command. CoreCommand, SubCommand and BaseCommand requires a `@Command` to be registered.

## Summary

### Properties

| Property    | Description                                         | Type     | Default |
|-------------|-----------------------------------------------------|----------|---------|
| name        | Name of the command                                 | String   |         |
| permission  | Permission a user will need to execute this command | String   |         |
| description | A description for this command                      | String   |         |
| playerOnly  | Is this command player only?                        | Boolean  | false   |

## Properties

### name
The name of this command to be called. Examples:
* a BaseCommand with name "feed" will be registered as `/feed`
* a CoreCommand with name "feed" will be registered as `/feed <sub>`
* a SubCommand with name "all" will be registered as `/feed all`

### description
A short description about this command. It will be showed on a help command.

### permission
Permission that players will need to execute this command. If you do not enter a permission, it means that this command does not require a permission.

### playerOnly
Represents that is this command player-only? Note that player-only means this command only can be executed from a Player. If you set `playerOnly` to `true`, a ConsoleCommandSender won't be able to use this command.