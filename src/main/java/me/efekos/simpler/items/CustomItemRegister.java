package me.efekos.simpler.items;

public class CustomItemRegister {
    private String id;
    private Class<? extends CustomItem> clazz;

    public String getId() {
        return id;
    }

    public CustomItemRegister setId(String id) {
        this.id = id;
        return this;
    }

    public Class<? extends CustomItem> getClazz() {
        return clazz;
    }

    public CustomItemRegister setClazz(Class<?extends CustomItem> clazz) {
        this.clazz = clazz;
        return this;
    }
}
