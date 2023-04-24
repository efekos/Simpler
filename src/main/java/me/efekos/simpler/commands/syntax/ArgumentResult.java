package me.efekos.simpler.commands.syntax;

public class ArgumentResult {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public ArgumentResult setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ArgumentResult setValue(String value) {
        this.value = value;
        return this;
    }
}
