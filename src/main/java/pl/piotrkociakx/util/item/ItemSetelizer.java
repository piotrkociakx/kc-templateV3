package pl.piotrkociakx.util.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pl.piotrkociakx.util.chat.ChatUtil;
import pl.piotrkociakx.util.config.ConfigManager;
import pl.piotrkociakx.util.player.BPlayer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemSetelizer {

    public ItemStack stack;


    /**
     *
     * @param path scieżka do sekcji konfiguracji
     * @param player gracz
     */
    public ItemSetelizer(String path, BPlayer player) {
        FileConfiguration configManager = ConfigManager.get;

        path = path+".";

        Material material = Material.getMaterial(configManager.getString(path + "material", "STONE"));
        String name = configManager.getString(path + "name", null);
        List<String> lore = configManager.getStringList(path + "lore");
        String enchant = configManager.getString(path + "enchant", null);
        int enchantlvl = configManager.getInt(path + "enchantlvl", 1);
        String skullNickName = configManager.getString(path + "skullName", "tpajkociakxd");
        String skullValue = configManager.getString(path + "skullValue", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIzOWFhZjZhNGZkNDNiZmZjYmYwZWIyOGMxMWQ5MmUzM2UwOWMzNzlmODQyNzcxZWE4OTYyYTk2NzJhZTM0NCJ9fX0=");
        int customModelId = configManager.getInt(path + "customModelData", 0);
        List<String> properites = configManager.getStringList(path + "flags");


        if (material != null && material != Material.AIR) {
            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            if (meta == null) {
                this.stack = null;
                return;
            }

            if (material == Material.PLAYER_HEAD) {
                SkullMeta skullMeta = (SkullMeta) meta;
                if (!skullValue.isEmpty()) {
                    stack.setItemMeta(getCustomSkull(skullMeta, skullValue));
                } else {
                    skullMeta.setOwner(skullNickName);
                    stack.setItemMeta(skullMeta);
                }
            }

            if (enchant != null && Enchantment.getByName(enchant) != null) {
                if(enchantlvl != 1 && enchantlvl != 0) {
                    meta.addEnchant(Enchantment.getByName(enchant), enchantlvl, true);
                } else {
                    meta.addEnchant(Enchantment.getByName(enchant), 1, true);
                }

            }


            if (!lore.isEmpty()) {
                List<String> coloredLore = lore.stream()
                        .map(line -> ChatUtil.replace(line, player.getPlayer()))
                        .collect(Collectors.toList());
                meta.setLore(coloredLore);
            }

            if (!properites.isEmpty()) {
                for (String flag : properites) {
                    try {
                        ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());
                        meta.addItemFlags(itemFlag);
                    } catch (IllegalArgumentException ignored) {
                    }
                }
            }


            if (name != null) {
                meta.setDisplayName(ChatUtil.replace(name, player.getPlayer()));
            }

            if (customModelId != 0) {
                meta.setCustomModelData(customModelId);
            }


            stack.setItemMeta(meta);


            this.stack = stack;
            return;
        }
        this.stack = null;
    }

    public static SkullMeta getCustomSkull(SkullMeta skullMeta, String value) {
        UUID staticUUID = UUID.fromString("c056c161-8a58-4ba1-8990-636db20d6018");
        GameProfile profile = new GameProfile(staticUUID, null);
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skullMeta;
    }
}