package pl.piotrkociakx.util.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.plugintemplate.Main;


@Getter
public class ConfigManager {

    private static FileConfiguration config;

    public static FileConfiguration get;

    public ConfigManager() {
        if(get == null) {
            get = config;
        }
        loadConfig();
    }
    private static void loadConfig() {
        JavaPlugin plugin = Main.getInstance();

        try {
            plugin.saveDefaultConfig();
            plugin.reloadConfig();
            config = plugin.getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void reloadConfig() {
        loadConfig();
    }

}