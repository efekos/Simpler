me.efekos.simpler.
# ColorCodes
`public class`

Used to translate colors inside your text.

## Summary

### Methods

| Method                                                            | Description                                   | Returns |
|-------------------------------------------------------------------|-----------------------------------------------|---------|
| `public static` [translate](#translate(string-text))(String text) | Translates the color codes in the text given. | String  |


## Methods

### translate(String text)
Translates the color codes in the text given. You should use `&` for color codes. This method translates color codes with character `&`, because it is most popular character to use with color codes. You can also use hex colors with following syntax:
````text
&#<hexcode>

Examples:
&#ffffff - White
&#000000 - Black
&#ff0000 - Red
etc.
````