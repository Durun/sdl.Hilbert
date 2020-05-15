package jp.ac.titech.itpro.sdl.hilbert

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var order = 1
    private var orderView: TextView? = null
    private var hilbertView: HilbertView? = null
    private var decButton: Button? = null
    private var incButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        orderView = findViewById(R.id.order_view)
        hilbertView = findViewById(R.id.hilbert_view)
        decButton = findViewById(R.id.dec_button)
        incButton = findViewById(R.id.inc_button)
        decButton!!.setOnClickListener(View.OnClickListener {
            assertTrue(order > 1, "A room to decrement order should exist")
            order--
            display()
        })
        incButton!!.setOnClickListener(View.OnClickListener {
            assertTrue(order < MAX_ORDER, "A room to increment order should exist")
            order++
            display()
        })
    }

    override fun onResume() {
        super.onResume()
        display()
    }

    private fun display() {
        orderView!!.text = getString(R.string.order_view_format, order)
        hilbertView!!.setOrder(order)
        decButton!!.isEnabled = order > 1
        incButton!!.isEnabled = order < MAX_ORDER
    }

    companion object {
        private const val MAX_ORDER = 9
        fun assertTrue(f: Boolean, message: String?) {
            if (BuildConfig.DEBUG && !f) {
                throw AssertionError(message)
            }
        }
    }
}