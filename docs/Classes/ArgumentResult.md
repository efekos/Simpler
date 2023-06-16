me.efekos.simpler.commands.syntax.
# ArgumentResult

Provides a Key/Value system for arguments.

## Summary

### Methods

| Method                 | Description                                                                                  | Returns        |
|------------------------|----------------------------------------------------------------------------------------------|----------------|
| getName()              | Returns the thing that will be suggested to the player.                                      | String         |
| getValue()             | Returns the thing that is actual value.                                                      | String         |
| setName(String name)   | Changes the name of this result. Returns itself to make ArgumentResult work like a builder   | ArgumentResult |
| setValue(String value) | Changes the value of this result. Returns itself to make ArgumentResult work like a builder. | ArgumentResult |

## Methods

### getName()
Returns something to suggest player.

### getValue()
Returns the actual value for the name.

### setName(name)
Changes name of this suggestion.

| Argument | Description                       | Type   |
|----------|-----------------------------------|--------|
| name     | New name of this argument result. | String |


### setValue(value)
Changes value of this suggestion.

| Argument | Description                        | Type   |
|----------|------------------------------------|--------|
| value    | New value of this argument result. | String |
