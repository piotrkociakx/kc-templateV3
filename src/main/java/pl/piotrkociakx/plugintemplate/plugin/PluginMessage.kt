package pl.piotrkociakx.plugintemplate.plugin

import org.bukkit.plugin.java.JavaPlugin
import pl.piotrkociakx.plugintemplate.Main


class PluginMessage {
    private var javaPlugin: JavaPlugin = Main.instance

    fun enableMessage() {
        log("=======================================")
        log("OGOR.TECH - SEZON 2")
        log("Nazwa: " + javaPlugin.name)
        log("Autor: " + javaPlugin.description.authors[0])
        log(" ")
        log("Pomyślnie załadowano plugin!")
        log("=======================================")
    }

    fun disableMessage() {
        log("=======================================")
        log("OGOR.TECH - SEZON 2")
        log("Nazwa: " + javaPlugin.name)
        log("Autor: " + javaPlugin.description.authors[0])
        log(" ")
        log("Pomyślnie wyłączono plugin!")
        log("=======================================")
    }

    private fun log(message: String) {
        javaPlugin.logger.info(message)
    }
}