package com.example.abc_test.framework.presentation.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.abc_test.framework.presentation.common.adapter.MyViewHolder
import com.example.abc_test.R
import com.example.abc_test.framework.base.px

abstract class SwipeToDeleteCallback(private val context: Context) : ItemTouchHelper.Callback() {

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white)
    private val background = GradientDrawable().apply {
        color = ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        cornerRadii = floatArrayOf(
            12f.px, 12f.px, 12f.px, 12f.px,
            12f.px, 12f.px, 12f.px, 12f.px
        )
    }
    private val backgroundColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
    private val textPaint = Paint().apply {
        color = ContextCompat.getColor(context, android.R.color.white)
        strokeWidth = 4f
        textSize = 11f.px
    }
    private val textBounds = Rect().apply {
        textPaint.getTextBounds("delete", 0, 6, this)
    }


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        if (viewHolder is MyViewHolder) {
            if (viewHolder.isSwipeable) {
                return makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.START
                )
            }

        }
        return ItemTouchHelper.ACTION_STATE_IDLE
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        drawBackground(c, itemView, background, backgroundColor, dX)

        deleteIcon?.apply { drawIcon(c, itemView, this) }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }

    private fun drawBackground(
        canvas: Canvas,
        itemView: View,
        background: GradientDrawable,
        color: Int,
        dX: Float
    ) {
        background.setBounds(
            itemView.right + dX.toInt() - 40.px,
            itemView.top,
            itemView.right - 20.px,
            itemView.bottom - 15.px
        )
        background.draw(canvas)
    }

    private fun drawIcon(canvas: Canvas, itemView: View, icon: Drawable) {

        val iconWidth = icon.intrinsicWidth
        val iconHeight = icon.intrinsicHeight

        val itemHeight = itemView.bottom - itemView.top

        val margin = (itemHeight - iconHeight) / 2

        val top = 32.px
        val left = itemView.right - margin - iconWidth
        val right = itemView.right - margin
        val bottom = top + iconHeight

        canvas.drawText("delete", 0, 6, left.toFloat() - (textBounds.width() - iconWidth) / 2f, bottom + 17f.px, textPaint)

        icon.setBounds(left, top, right, bottom)
        icon.draw(canvas)
    }
}