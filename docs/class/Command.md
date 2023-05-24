me.efekos.simpler.annotations.
# Command

@Command is used in BaseCommand,CoreCommand and SubCommand's to describe some of their static properties. To register a command, you must annotate a @Command to it.

## Properties

| Property    | Description                                                    | Type    | Default |
|-------------|----------------------------------------------------------------|---------|---------|
| name        | The name of this command.                                      | String  | null    |
| description | A short description to describe what this command does.        | String  | null    |
| permission  | Permission that players will need to execute this command.     | String  | ""      |
| playerOnly  | If true, the console or Command Blocks can't use this command. | Boolean | true    |