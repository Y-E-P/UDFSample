package com.pou.udfsample.utils

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class ListPaddingDecoration(
    context: Context,
    val top: Int = 0,
    val left: Int = 0,
    val right: Int = 0,
    val bottom: Int = 0
) : ItemDecoration() {
    private val mPaddingTop: Int
    private val mPaddingLeft: Int
    private val mPaddingRight: Int
    private val mPaddingBottom: Int

    init {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        mPaddingTop = top.applyDimen(metrics)
        mPaddingLeft = left.applyDimen(metrics)
        mPaddingRight = right.applyDimen(metrics)
        mPaddingBottom = bottom.applyDimen(metrics)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = parent.adapter ?: return
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        when {
            itemPosition == 0 -> {
                outRect.left = mPaddingTop
            }
            itemPosition == adapter.itemCount - 1 -> {
                outRect.right = mPaddingTop
            }
            else -> {
                outRect.left = right
            }
        }

    }

    private fun Int.applyDimen(metrics: DisplayMetrics): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            metrics
        ).toInt()
    }

}