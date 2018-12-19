package com.github.sckm.example.itemnameplate.groupie

import android.support.annotation.ColorInt
import android.widget.TextView
import com.github.sckm.example.itemnameplate.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class SimpleItemsHeader : Item<ViewHolder>(R.layout.item_simple_items_header.toLong()) {
    override fun getLayout(): Int = R.layout.item_simple_items_header

    override fun bind(viewHolder: ViewHolder, position: Int) {
        // no-op
    }
}

class SimpleTextItem(private val body: String) : Item<ViewHolder>(body.hashCode().toLong()) {
    override fun getLayout(): Int = R.layout.item_simple_text

    override fun bind(viewHolder: ViewHolder, position: Int) {
        (viewHolder.root as TextView).text = body
    }
}

class GridItemsHeader : Item<ViewHolder>(R.layout.item_grid_items_header.toLong()) {
    override fun getLayout(): Int = R.layout.item_grid_items_header

    override fun bind(viewHolder: ViewHolder, position: Int) {
        // no-op
    }
}

class ColorfulGridItem(
    private val body: String,
    @ColorInt private val color: Int
) : Item<ViewHolder>(R.layout.item_colorful_grid.toLong()) {
    override fun getLayout(): Int = R.layout.item_colorful_grid

    override fun bind(viewHolder: ViewHolder, position: Int) {
        (viewHolder.root as TextView).apply {
            text = body
            setBackgroundColor(color)
        }
    }

    override fun getSpanSize(spanCount: Int, position: Int): Int = 1
}
