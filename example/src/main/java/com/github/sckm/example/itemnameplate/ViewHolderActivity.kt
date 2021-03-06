package com.github.sckm.example.itemnameplate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.github.sckm.example.itemnameplate.viewholder.ViewHolderExampleAdapter
import com.github.sckm.itemnameplate.viewholder.ViewHolderNameplateDecoration
import kotlinx.android.synthetic.main.activity_groupie.*

class ViewHolderActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ViewHolderActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupie)

        setupGroupieItems()
    }

    private fun setupGroupieItems() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@ViewHolderActivity, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position < 8) 3 else 1
                    }
                }
            }
            adapter = ViewHolderExampleAdapter()
            addItemDecoration(ViewHolderNameplateDecoration(this@ViewHolderActivity))
        }
    }
}
