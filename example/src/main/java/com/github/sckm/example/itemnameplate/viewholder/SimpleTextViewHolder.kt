package com.github.sckm.example.itemnameplate.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.sckm.example.itemnameplate.R

class SimpleTextViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): SimpleTextViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_simple_text, parent, false)
            return SimpleTextViewHolder(view)
        }
    }

    fun bind(body: String) {
        (itemView as TextView).text = body
    }
}