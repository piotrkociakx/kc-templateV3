package pl.kociak.util.manager

import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.event.Listener
import pl.piotrkociakx.plugintemplate.Main
import pl.piotrkociakx.util.handler.CommandHandler
import java.lang.reflect.Field

abstract class PluginManagerImplementation {

    /**
     * Tutaj Dodaj liste komend do zarejstrowania
     */
    abstract fun cmdMapper() : List<CommandHandler>

    abstract fun listenerMapper() : List<Listener>


    /**
     * Rejestruje komendy z listy `commands`
     *
     * @param logMessage wiadomosc dodaniu komendy
     * zmienne: `{name}`.
     * Przykład: "[+] {name} dodana.".
     */
    fun registerCommands(logMessage: String) {
        try {
            val bukkitCommandMap: Field = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            bukkitCommandMap.isAccessible = true
            val commandMap = bukkitCommandMap.get(Bukkit.getServer()) as CommandMap

            val minecraftLogger = Main.instance.logger

            cmdMapper().forEach { command ->
                command.register(commandMap)

                command.aliases.forEach { alias ->
                    command.setName(alias)
                    command.register(commandMap)
                }
                if (logMessage.isNotEmpty()) {
                    minecraftLogger.info(logMessage.replace("{name}", command.name))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun registerListeners() {
        listenerMapper().forEach { listener -> Main.instance.server.pluginManager.registerEvents(listener, Main.instance)}
    }
}
