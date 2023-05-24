me.efekos.simpler.commands.syntax.
# Syntax

Represents a command syntax of BaseCommand, or CoreCommand.

## Summary

### Methods

| Method                     | Description                                                           | Returns              |
|----------------------------|-----------------------------------------------------------------------|----------------------|
| Syntax()                   |                                                                       |                      |
| getArguments()             | Returns a list of the arguments that this Syntax contains             | ArrayList\<Argument> |
| withArgument(Argument arg) | Add the given argument to the last of argument list. Returns itself.  | Syntax               |

## Methods

### getArguments()
Returns a list of the that this Syntax contains. Used in default command handling in Simpler.

### withArgument(arg)
Adds given argument to the last of argument list. But instead of `void`, returns itself after adding the argument. Because:
````java
Syntax s = new Syntax();
s.withArgument(arg1);
s.withArgument(arg2);
return s;
````
This is a long code, especially if you have more than two arguments. But you can do
````java
return new Syntax()
        .withArgument(arg1)
        .withArgument(arg2)
````
instead for a shorter and hopefully more understandable code.

| Argument | Description                      | Type     |
|----------|----------------------------------|----------|
| arg      | Argument to use with this syntax | Argument |
