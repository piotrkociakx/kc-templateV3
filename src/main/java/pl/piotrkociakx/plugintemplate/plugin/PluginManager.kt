package pl.piotrkociakx.plugintemplate.plugin

import org.bukkit.event.Listener
import pl.kociak.util.manager.PluginManager
import pl.piotrkociakx.util.handler.CommandHandler

class PluginManager : PluginManager() {
    override fun cmdMapper(): List<CommandHandler> {
        return listOf()
    }

    override fun listenerMapper(): List<Listener> {
        return listOf()
    }

}