package jp.ac.titech.itpro.sdl.hilbert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var order = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            order = it.getInt("order")
        }

        dec_button.setOnClickListener {
            assertTrue(order > 1, "A room to decrement order should exist")
            order--
            display()
        }
        inc_button.setOnClickListener {
            assertTrue(order < MAX_ORDER, "A room to increment order should exist")
            order++
            display()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("order", order)
    }

    override fun onResume() {
        super.onResume()
        display()
    }

    private fun display() {
        order_view.text = getString(R.string.order_view_format, order)
        hilbert_view.setOrder(order)
        dec_button.isEnabled = order > 1
        inc_button.isEnabled = order < MAX_ORDER
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