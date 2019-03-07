package com.github.sckm.example.itemnameplate

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_groupie.*

class MainActivity : AppCompatActivity() {

    private val items by lazy {
        mutableListOf(
            GroupieExampleItem(),
            GroupieHyperionExampleItem(),
            ViewHolderHyperionExampleItem()
        )
    }

    private val offsetItemDecoration by lazy {
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val density = parent.context.resources.displayMetrics.density
                val horizontalOffset = (8 * density).toInt()
                val verticalOffset = (4 * density).toInt()
                outRect.set(horizontalOffset, verticalOffset, horizontalOffset, verticalOffset)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupItems()
    }

    private fun setupItems() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
            spanCount = 3
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
            addItemDecoration(offsetItemDecoration)
        }
    }

    abstract class ExamplePageButtonItem : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_exaple_page_button
    }

    class GroupieExampleItem : ExamplePageButtonItem() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            (viewHolder.root as TextView).setText(R.string.groupie_example_button)
            viewHolder.root.setOnClickListener {
                GroupieActivity.startActivity(viewHolder.root.context)
            }
        }
    }

    class GroupieHyperionExampleItem : ExamplePageButtonItem() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            (viewHolder.root as TextView).setText(R.string.groupie_hyperion_example_button)
            viewHolder.root.setOnClickListener {
                HyperionGroupieActivity.startActivity(viewHolder.root.context)
            }
        }
    }

    class ViewHolderHyperionExampleItem : ExamplePageButtonItem() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            (viewHolder.root as TextView).setText(R.string.view_holder_example_button)
            viewHolder.root.setOnClickListener {
                ViewHolderActivity.startActivity(viewHolder.root.context)
            }
        }
    }
}
