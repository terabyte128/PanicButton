package com.samwolfson.panicbutton;

import org.bukkit.plugin.java.JavaPlugin;

public class PanicButton extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
