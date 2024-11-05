package pl.piotrkociakx.util.chat;

import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.piotrkociakx.util.hook.Hook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtil {

    @Getter
    private static final boolean usePlaceholderAPI = Hook.Companion.isPlaceHolderAPI();

    public static String replace(String message, Player player) {
        String processedMessage = replaceColors(message);
        processedMessage = replaceHexColors(processedMessage);
        processedMessage = replacePlaceholders(player, processedMessage);
        return replacePlaceholdersWithAPI(player, processedMessage);
    }

    public static String replace(String message) {
        String processedMessage = replaceColors(message);
        processedMessage = replaceHexColors(processedMessage);
        return replacePlaceholdersWithAPI(null, processedMessage);
    }

    public static String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> replace(List<String> list) {
        list.replaceAll(ChatUtil::replace);
        return list;
    }

    public static List<String> replace(List<String> list, Player player) {
        list.replaceAll(message -> replace(message, player));
        return list;
    }

    public static String replaceHexColors(String message) {
        Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String color = matcher.group(1);
            StringBuilder replacement = new StringBuilder("§x");
            for (char c : color.toCharArray()) {
                replacement.append("§").append(c);
            }
            matcher.appendReplacement(buffer, replacement.toString());
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static String replacePlaceholders(Player player, String message) {
        if (player == null) {
            return message;
        }

        Map<String, String> placeholders = getPlayerPlaceholders(player);
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return message;
    }

    private static Map<String, String> getPlayerPlaceholders(Player player) {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("player", player.getName());
        placeholders.put("ip", player.getAddress().toString());
        placeholders.put("uuid", player.getUniqueId().toString());
        placeholders.put("displayname", player.getDisplayName());
        placeholders.put("world", player.getWorld().getName());
        placeholders.put("health", String.valueOf(player.getHealth()));
        placeholders.put("maxhealth", String.valueOf(player.getMaxHealth()));
        placeholders.put("food", String.valueOf(player.getFoodLevel()));
        placeholders.put("level", String.valueOf(player.getLevel()));
        placeholders.put("exp", String.valueOf(player.getExp()));
        placeholders.put("x", String.valueOf(player.getLocation().getX()));
        placeholders.put("y", String.valueOf(player.getLocation().getY()));
        placeholders.put("z", String.valueOf(player.getLocation().getZ()));
        return placeholders;
    }

    public static String replacePlaceholdersWithAPI(Player player, String message) {
        if (usePlaceholderAPI) {
            return PlaceholderAPI.setPlaceholders(player, message);
        }
        return message;
    }

}