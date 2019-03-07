package com.github.sckm.itemnameplate.viewholder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorInt
import android.support.annotation.Px
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import kotlin.math.min

class ViewHolderNameplateDecoration(
    context: Context,
    paddingDp: Float = 2f,
    private val textSizeDp: Float = 10f,
    @ColorInt private val textColor: Int = Color.YELLOW,
    @ColorInt private val backgroundColor: Int = Color.argb(0xAA, 0, 0, 0)
) : RecyclerView.ItemDecoration() {

    private val textPaint: Paint = Paint().apply {
        color = textColor
        textSize = dpToPx(context, textSizeDp).toFloat()
    }

    private val backgroundPaint = Paint().apply {
        color = backgroundColor
        textSize = dpToPx(context, textSizeDp).toFloat()
    }

    private val padding = dpToPx(context, paddingDp)

    private val tmpRect = Rect()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        (0 until parent.childCount)
            .map { parent.getChildAt(it) }
            .forEach { child ->
                val viewHolder = parent.getChildViewHolder(child)
                viewHolder?.let {
                    val text = viewHolder.javaClass.simpleName
                    val l = child.left.toFloat() + child.translationX
                    val r = child.right.toFloat() + child.translationX
                    val t = child.top.toFloat() + child.translationY
                    val b = child.bottom.toFloat() + child.translationY

                    textPaint.getTextBounds(text, 0, text.length, tmpRect)
                    val tl = tmpRect.left
                    val tt = tmpRect.top
                    val tr = tmpRect.right
                    val tb = tmpRect.bottom
                    val textHeight = tb - tt
                    val textWidth = tr - tl

                    // TODO ellipsize
                    c.drawRect(
                        l, t + tb, min(r, l + textWidth + padding * 2),
                        min(b, t + textHeight + tb + padding * 2),
                        backgroundPaint
                    )
                    c.drawText(text, l + padding, t + textHeight + padding, textPaint)
                }
            }
    }

    @Px
    fun dpToPx(c: Context, dp: Float): Int {
        val metrics = c.resources.displayMetrics
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            metrics
        ) + 0.5f).toInt()
    }
}