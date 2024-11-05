package pl.piotrkociakx.plugintemplate.command

import pl.piotrkociakx.util.handler.CommandHandler
import pl.piotrkociakx.util.player.BPlayer
import org.bukkit.configuration.file.FileConfiguration


/**
    Przykładowa komenda w tym przypadku napisana w kotlinie :D
 **/

class ExampleCommand : CommandHandler(
    "test", // Nazwa komendy
    null,  // Permisja. mozesz dac null jesli ma byc bez
    true   // Czy ma byc tylko dla gracza?
) {

    /**
     * @param player Player na sterydach
     * @param config config pluginu
     * @param args argumenty
     */
    override fun command(player: BPlayer, config: FileConfiguration, args: Array<out String>) {
        player.sendMessage("&aAutomatyczne kolory,Twoj nick to ${player.getName()}! Użyłeś komendy /test.")
    }
}
