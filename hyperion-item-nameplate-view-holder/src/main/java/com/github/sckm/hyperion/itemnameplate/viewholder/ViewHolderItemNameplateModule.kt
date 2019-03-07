package com.github.sckm.hyperion.itemnameplate.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sckm.itemnameplate.viewholder.ViewHolderNameplateDecoration
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

class ViewHolderItemNameplateModule : PluginModule() {

    private lateinit var itemDecoration: RecyclerView.ItemDecoration

    private val onAddButtonClickListener = View.OnClickListener {
        findRecyclerViewThen(extension.contentRoot) { recyclerView ->
            recyclerView.addItemDecorationIfNotAdded(itemDecoration)
        }
    }

    override fun getName(): Int {
        return R.string.view_holder_item_decoration_module_title
    }

    override fun onCreate() {
        super.onCreate()
        itemDecoration = ViewHolderNameplateDecoration(context)
    }

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View? {
        return layoutInflater.inflate(R.layout.layout_item_decoration_module2, parent, false).apply {
            setOnClickListener(onAddButtonClickListener)
        }
    }

    private fun findRecyclerViewThen(v: ViewGroup, block: (RecyclerView) -> Unit) {
        (0 until v.childCount)
            .map { v.getChildAt(it) }
            .filterIsInstance(ViewGroup::class.java)
            .forEach {
                (it as? RecyclerView)?.run(block)

                findRecyclerViewThen(it, block)
            }
    }

    private fun RecyclerView.addItemDecorationIfNotAdded(decoration: RecyclerView.ItemDecoration) {
        val isNotAdded = (0 until this.itemDecorationCount)
            .map { getItemDecorationAt(it) }
            .none { itemDecoration -> itemDecoration == decoration }

        if (isNotAdded) {
            this.addItemDecoration(decoration)
        }
    }
}