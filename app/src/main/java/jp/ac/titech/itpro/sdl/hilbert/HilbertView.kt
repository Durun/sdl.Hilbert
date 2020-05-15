package jp.ac.titech.itpro.sdl.hilbert

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import jp.ac.titech.itpro.sdl.hilbert.Turtle.Drawer
import java.lang.Math.min

class HilbertView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()
    private lateinit var bufferBitmap: Bitmap
    private lateinit var bufferCanvas: Canvas

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        bufferBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bufferCanvas = Canvas(bufferBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bufferBitmap, 0f, 0f, paint)
    }

    fun setOrder(n: Int) {
        post {
            bufferCanvas.drawHilbert(n)
            invalidate()
        }
    }

    private fun Canvas.drawHilbert(order: Int) {
        val w = width
        val h = height
        paint.color = Color.DKGRAY
        this.drawRect(0f, 0f, w.toFloat(), h.toFloat(), paint)
        paint.color = Color.WHITE
        paint.strokeWidth = 3f
        val size = min(w, h)
        val step = size.toDouble() / (1 shl order)
        val turtle = HilbertTurtle(Drawer { x0, y0, x1, y1 -> this.drawLine(x0.toFloat(), y0.toFloat(), x1.toFloat(), y1.toFloat(), paint) })
        turtle.setPos((w - size + step) / 2, (h + size - step) / 2)
        turtle.setDir(HilbertTurtle.E)
        turtle.draw(order, step, HilbertTurtle.R)
    }
}