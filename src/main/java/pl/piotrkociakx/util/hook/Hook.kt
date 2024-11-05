package pl.piotrkociakx.util.hook

import pl.piotrkociakx.plugintemplate.Main


class Hook {
    companion object {
        fun isPlaceHolderAPI() : Boolean {
            val plugin = Main.getInstance()
            return plugin.server.pluginManager.getPlugin("PlaceholderAPI") != null
        }
    }

}