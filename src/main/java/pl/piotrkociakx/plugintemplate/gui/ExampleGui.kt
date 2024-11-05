package pl.piotrkociakx.plugintemplate.gui

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import pl.piotrkociakx.util.gui.InvUtil
import pl.piotrkociakx.util.player.BPlayer


class ExampleGui {

    fun inventory(player : Player) : Inventory {
        var inv : Inventory = InvUtil.create(BPlayer(player), "&aJakiestam inwentory", 5)

        return inv
    }
}