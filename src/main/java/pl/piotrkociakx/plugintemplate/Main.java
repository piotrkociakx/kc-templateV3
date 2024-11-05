package pl.piotrkociakx.plugintemplate;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.hash.OgorHash;
import pl.piotrkociakx.license.LicenseManager;
import pl.piotrkociakx.message.PluginMessage;
import pl.piotrkociakx.plugintemplate.plugin.PluginManager;
import pl.piotrkociakx.util.config.ConfigManager;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager configManager = new ConfigManager();
        PluginManager pluginManager = new PluginManager();
        pluginManager.registerListeners();
        pluginManager.registerCommands("[+] {name} Zarejstrowana!");

        PluginMessage.enableMessage(this);
    }

    @Override
    public void onDisable() {
        PluginMessage.disableMessage(this);
    }
}
