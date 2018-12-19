package com.github.sckm.example.itemnameplate

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.github.sckm.example.itemnameplate.groupie.ColorfulGridItem
import com.github.sckm.example.itemnameplate.groupie.GridItemsHeader
import com.github.sckm.example.itemnameplate.groupie.SimpleItemsHeader
import com.github.sckm.example.itemnameplate.groupie.SimpleTextItem
import com.github.sckm.itemnameplate.groupie.GroupieItemNameplateDecoration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val colors by lazy {
        listOf(
            Color.rgb(0xff, 0xd6, 0xd6),
            Color.rgb(0xd6, 0xff, 0xff),
            Color.rgb(0xd6, 0xff, 0xd6),
            Color.rgb(0xd6, 0xea, 0xff),
            Color.rgb(0xff, 0xff, 0xd6)
        )
    }

    private val items by lazy {
        val items = mutableListOf(
            SimpleItemsHeader(),
            SimpleTextItem("1st simple item "),
            SimpleTextItem("2nd simple item "),
            SimpleTextItem("3rd simple item ")
        )

        items += GridItemsHeader()
        items += (1..12).map { ColorfulGridItem(it.toString(), colors[it % colors.size]) }
        items
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupGroupieItems()
    }

    private fun setupGroupieItems() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
            spanCount = 3
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
            addItemDecoration(GroupieItemNameplateDecoration(this@MainActivity))
        }

    }
}
