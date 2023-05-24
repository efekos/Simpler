me.efekos.simpler.commands.translation.
# TranslateManager

## Summary

### Properties

| Property         | Description                             | Type    |
|------------------|-----------------------------------------|---------|
| hexColorsPattern | A pattern used to check for hex colors. | Pattern |


### Methods

| Method                                                               | Description                                                                                      | Returns                 |
|----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|-------------------------|
| translateMaterial(@NotNull Material material)                        | Generates a TranslatableComponent that translates the name of given material.                    | TranslatableComponent   |
| translateEntity(@NotNull EntityType type)                            | Generates a TranslatableComponent that translates the name of given entity type.                 | TranslatableComponent   |
| translateEnchantment(@NotNull Enchantment enchantment)               | Generates a TranslatableComponent that translates the name of given enchantment.                 | TranslatableComponent   |
| translateEffect(@NotNull PotionEffectType type)                      | Generates a TranslatableComponent that translates the name of given effect.                      | TranslatableComponent   |
| translateKey(@NotNull String key)                                    | Generates a TranslatableComponent that uses given key.                                           | TranslatableComponent   |
| translate(@NotNull Material material)                                | Alias for translateMaterial(Material).                                                           | TranslatableComponent   |
| translate(@NotNull EntityType type)                                  | Alias for translateEntity(EntityType)                                                            | TranslatableComponent   |
| translate(@NotNull Enchantment enchantment)                          | Alias for translateEnchantment(Enchantment)                                                      | TranslatableComponent   |
| translate(@NotNull PotionEffectType type)                            | Alias for translateEffect(PotionEffectType)                                                      | TranslatableComponent   |
| translate(@NotNull String key)                                       | Alias for translateKey(String)                                                                   | TranslatableComponent   |
| [translateColors](#translatecolorsmessage)(@NotNull String message)  | Translates all color codes in the text given. Supports vanilla minecraft codes and hex colors.   | String                  |

## Properties

### hexColorsPattern
A patterns used to check for hex colors in translateColors(@NotNull String message).

## Methods

### translateMaterial(material)
Generates a TranslatableComponent that can be used to translate the name of given material. There is a simple method for doing that: If a material is a block, it's key starts with `"block.minecraft."`. But if it is an item, it's key starts with `"item.minecraft.`. Then, we use that material's key to the last, and put the result to a TranslatableComponent.

| Argument | Description                       | Type              |
|----------|-----------------------------------|-------------------|
| material | Material that will be translated. | @NotNull Material |

### translateEntity(type)
Generates a TranslatableComponent that can be used to translate the name of given entity. Method is kinda same with #translateMaterial(Material), but instead of checking a value, we just put `"entity.minecraft."` and that entity's name.

| Argument | Description                                 | Type                |
|----------|---------------------------------------------|---------------------|
| type     | Type of the entity that will be translated. | @NotNull EntityType |

### translateEnchantment(enchantment)
Generates a TranslatableComponent that can be used to translate the name of given enchantment. Method is same with #translateEntity(Entity).

| Argument    | Description                             | Type                 |
|-------------|-----------------------------------------|----------------------|
| enchantment | An enchantment that will be translated. | @NotNull Enchantment |


### translateEffect(type)
Generates a TranslatableComponent that can be used to translate the name of given effect. Method is same with #translateEntity(Entity).

| Argument | Description                            | Type                      |
|----------|----------------------------------------|---------------------------|
| type     | An effect type that will be translated | @NotNull PotionEffectType |


### translateKey(key)
Generates a TranslatableComponent that can be used to translate given key. There is no method, we just put the given key to a TranslatableComponent

| Argument | Description      | Type            |
|----------|------------------|-----------------|
| key      | Translation key. | @NotNull String |

### translate(any)
An alias for all translate methods.

| Argument | Description                    | Type                                                            |
|----------|--------------------------------|-----------------------------------------------------------------|
| any      | Thing that will be translated. | Material \ EntityType \ Enchantment \ PotionEffectType \ String |

### translateColors(message)
Translates all the chat colors in given message. Supports every vanilla chat color, just use `&` instead of `§`. Also support hex colors, which is a thing added to minecraft in 1.16 . To use hex colors inside your message, type `&#xxxxxx` and replace x's with your hex color code.

| Argument | Description                                 | Type   |
|----------|---------------------------------------------|--------|
| message  | Text to translate color codes inside of it. | String |