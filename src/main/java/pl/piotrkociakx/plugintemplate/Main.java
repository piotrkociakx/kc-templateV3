package pl.piotrkociakx.plugintemplate;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.plugintemplate.plugin.PluginManager;
import pl.piotrkociakx.util.config.ConfigManager;

public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;
    public PluginManager pluginManager;

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager configManager = new ConfigManager();
        this.pluginManager = new PluginManager();
        pluginManager.registerListeners();
        pluginManager.registerCommands("[+] {name} Zarejstrowana!");


        // Startup logic

        pluginManager.sendEnablePluginMessage();
    }

    @Override
    public void onDisable() {
        pluginManager.sendDisablePluginMessage();
    }
}
