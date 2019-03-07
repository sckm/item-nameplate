package com.github.sckm.example.itemnameplate.viewholder

import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.sckm.example.itemnameplate.R

class ColorfulGridViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): ColorfulGridViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_colorful_grid, parent, false)
            return ColorfulGridViewHolder(view)
        }
    }

    fun bind(body: String, @ColorInt color: Int) {
        (itemView as TextView).apply {
            text = body
            setBackgroundColor(color)
        }
    }
}