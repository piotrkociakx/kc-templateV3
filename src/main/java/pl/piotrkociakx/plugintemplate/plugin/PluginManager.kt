package pl.piotrkociakx.plugintemplate.plugin

import org.bukkit.event.Listener
import pl.kociak.util.manager.PluginManagerImplementation
import pl.piotrkociakx.plugintemplate.command.ExampleCommand
import pl.piotrkociakx.util.handler.CommandHandler

class PluginManager : PluginManagerImplementation() {
    override fun cmdMapper(): List<CommandHandler> {
        return listOf(ExampleCommand())
    }

    override fun listenerMapper(): List<Listener> {
        return listOf()
    }

    fun sendDisablePluginMessage() {
        PluginMessage().disableMessage()
    }
    fun sendEnablePluginMessage() {
        PluginMessage().enableMessage()
    }

}