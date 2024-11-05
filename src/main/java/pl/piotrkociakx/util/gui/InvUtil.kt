package pl.piotrkociakx.util.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import pl.piotrkociakx.util.chat.ChatUtil
import pl.piotrkociakx.util.config.ConfigManager
import pl.piotrkociakx.util.exception.InventoryException
import pl.piotrkociakx.util.item.ItemSetelizer
import pl.piotrkociakx.util.player.BPlayer

class InvUtil {
    companion object {
        /**
         * Tworzy inventory i je koloruje
         *
         * @param player gracz
         * @param name nazwa inventory
         * @param rows liczba linijek inwentory
         */
        fun create(player : BPlayer, name : String, rows : Int) : Inventory {
            if(rows <1 || rows < 9) {
                throw InventoryException("Invalid rows number")
            }
            return Bukkit.createInventory(player.getPlayer(), rows, ChatUtil.replace(name))
        }

        /**
         * Wypełnia puste sloty
         * @param inventory inventory
         * @param material material wypelnienia
         *
         */
        fun fillEmpty(inventory: Inventory, material: Material) {
            for (slot in 0 until inventory.size) {
                if (inventory.getItem(slot) == null || inventory.getItem(slot)?.type == Material.AIR) {
                    inventory.setItem(slot, ItemStack(material))
                }
            }
        }


        /**
         * @param inventory inventory do dodania itemkow
         * @param path sciezka do sekcji configu (przyklad gui.items)
         * @param player gracz jako Bplayer
         */
        fun addItemsToGUI(inventory: Inventory, path: String, player: BPlayer) {
            val config: FileConfiguration = ConfigManager.get
            val section = config.getConfigurationSection(path) ?: return

            for (itemKey in section.getKeys(false)) {
                try {
                    val itemPath = "$path.$itemKey"
                    val item = ItemSetelizer(itemPath, player)
                    val slot: Int = config.getInt("$itemPath.slots", -1)
                    if (slot >= 0) {
                        inventory.setItem(slot, item.stack)
                    } else {
                        inventory.addItem(item.stack)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

}