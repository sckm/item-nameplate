package com.github.sckm.hyperion.itemnameplate.groupie

import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class GroupieItemNameplateDecorationPlugin : Plugin() {
    override fun createPluginModule(): PluginModule? {
        return GroupieItemNameplateDecorationModule()
    }
}