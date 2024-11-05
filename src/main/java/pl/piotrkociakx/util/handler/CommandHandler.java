package pl.piotrkociakx.util.handler;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import pl.piotrkociakx.util.config.ConfigManager;
import pl.piotrkociakx.util.player.BPlayer;

import java.util.Arrays;

public abstract class CommandHandler extends Command {

    private final String permission;
    private final boolean playeronly;

    public CommandHandler(String command, String permission, boolean playeronly, String... aliases) {
        super(command);
        if(permission != null) Bukkit.getServer().getPluginManager().addPermission(new Permission(permission, command.toLowerCase()));
        if(aliases != null && aliases.length > 0) this.setAliases(Arrays.asList(aliases));
        this.permission = permission;
        this.playeronly = playeronly;
    }

    public abstract void command(BPlayer player, FileConfiguration config, String[] args);

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, String[] args) {
        if(!(commandSender instanceof Player player && playeronly)) {
            commandSender.sendMessage("Ta komenda jest tylko dla graczy!");
            return true;
        }
        BPlayer apiPlayer = new BPlayer(player);
        FileConfiguration config = ConfigManager.get;

        if(permission != null && !player.hasPermission(permission)) {
            apiPlayer.sendMessage("&cNie masz uprawnien do tej komendy. ("+ permission+")");
            return true;
        }

        command(apiPlayer, config, args);
        return true;

    }
}
