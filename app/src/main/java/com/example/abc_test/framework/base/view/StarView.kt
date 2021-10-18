package com.example.abc_test.framework.base.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.abc_test.framework.base.px

private const val DEFAULT_LINE_WIDTH = 1f

class StarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Add option to change size from xml here if needed
    private var lineWidth: Float = DEFAULT_LINE_WIDTH.px

    var paint = Paint().apply {
        setColor(Color.parseColor("#f2732c"))
        setAntiAlias(true)
        setStyle(Paint.Style.STROKE)
    }

    var path: Path = Path()

    public fun setFill(isFill: Boolean) {
        if (isFill) {
            if (paint.style == Paint.Style.FILL) return
            paint.style = Paint.Style.FILL
        } else {
            if (paint.style == Paint.Style.STROKE) return
            paint.style = Paint.Style.STROKE
        }
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        path.reset();
        var mid = (width / 2).toFloat()
        val min = Math.min(width, height).toFloat()
        val fat = min / 17
        val half = min / 2
        val rad = half - fat
        mid = mid - half

        path.reset();

        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f);
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f);
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f);
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f);
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f);
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f);

        path.close();
        canvas.drawPath(path, paint);

        super.onDraw(canvas);
    }
}