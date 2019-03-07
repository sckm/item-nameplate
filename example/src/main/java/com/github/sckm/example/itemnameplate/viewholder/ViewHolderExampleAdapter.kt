package com.github.sckm.example.itemnameplate.viewholder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ViewHolderExampleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class ViewType {
        SIMPLE_ITEMS_HEADER,
        SIMPLE_TEXT,
        GRID_ITEMS_HEADER,
        COLORFUL_GRID
    }

    companion object {
        private const val SIMPLE_ITEMS_HEADER_COUNT = 1
        private const val SIMPLE_TEXT_COUNT = 3
        private const val GRID_HEADER_COUNT = 1
        private const val COLORFUL_GRID_COUNT = 12
    }

    private val colors by lazy {
        listOf(
            Color.rgb(0xff, 0xd6, 0xd6),
            Color.rgb(0xd6, 0xff, 0xff),
            Color.rgb(0xd6, 0xff, 0xd6),
            Color.rgb(0xd6, 0xea, 0xff),
            Color.rgb(0xff, 0xff, 0xd6)
        )
    }

    override fun getItemViewType(position: Int): Int {
        val l = listOf(
            SIMPLE_ITEMS_HEADER_COUNT to ViewType.SIMPLE_ITEMS_HEADER,
            SIMPLE_TEXT_COUNT to ViewType.SIMPLE_TEXT,
            GRID_HEADER_COUNT to ViewType.GRID_ITEMS_HEADER,
            COLORFUL_GRID_COUNT to ViewType.COLORFUL_GRID
        )

        var cur = 0
        l.forEach { (nextCount, viewType) ->
            if (position <= cur + nextCount) {
                return viewType.ordinal
            }
            cur += nextCount
        }

        throw IllegalStateException()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values()[viewType]) {
            ViewType.SIMPLE_ITEMS_HEADER -> SimpleItemsHeaderViewHolder.create(parent)
            ViewType.SIMPLE_TEXT -> SimpleTextViewHolder.create(parent)
            ViewType.GRID_ITEMS_HEADER -> GridItemsHeaderViewHolder.create(parent)
            ViewType.COLORFUL_GRID -> ColorfulGridViewHolder.create(parent)
        }
    }

    override fun getItemCount(): Int {
        return SIMPLE_ITEMS_HEADER_COUNT + SIMPLE_TEXT_COUNT + GRID_HEADER_COUNT + COLORFUL_GRID_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var cur = 0

        fun acc(count: Int, runnable: (Int) -> Unit): Boolean {
            val ret = if (cur <= position && position <= cur + count) {
                val posInSameViews = position - cur
                runnable(posInSameViews)
                true
            } else {
                false
            }
            cur += count
            return ret
        }

        if (acc(SIMPLE_ITEMS_HEADER_COUNT) { pos -> }) return

        if (acc(SIMPLE_TEXT_COUNT) { pos ->
                holder as SimpleTextViewHolder
                holder.bind("Item $pos")
            }
        ) return

        if (acc(GRID_HEADER_COUNT) { pos -> }) return

        if (acc(COLORFUL_GRID_COUNT) { pos ->
                holder as ColorfulGridViewHolder
                holder.bind(pos.toString(), colors[pos % colors.size])
            }
        ) return


    }
}