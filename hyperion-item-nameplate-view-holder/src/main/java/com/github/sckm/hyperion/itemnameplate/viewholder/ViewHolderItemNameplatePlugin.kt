package com.github.sckm.hyperion.itemnameplate.viewholder

import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class ViewHolderItemNameplatePlugin : Plugin() {
    override fun createPluginModule(): PluginModule? {
        return ViewHolderItemNameplateModule()
    }
}