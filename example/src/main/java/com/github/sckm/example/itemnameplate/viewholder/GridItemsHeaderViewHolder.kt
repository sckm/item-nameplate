package com.github.sckm.example.itemnameplate.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sckm.example.itemnameplate.R

class GridItemsHeaderViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): GridItemsHeaderViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_grid_items_header, parent, false)
            return GridItemsHeaderViewHolder(view)
        }
    }
}