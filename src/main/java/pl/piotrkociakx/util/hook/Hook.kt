package pl.piotrkociakx.util.hook

import pl.piotrkociakx.plugintemplate.Main


class Hook {
    companion object {
        fun isPlaceHolderAPI() : Boolean {
            val plugin = Main.instance
            return plugin.server.pluginManager.getPlugin("PlaceholderAPI") != null
        }
    }

}