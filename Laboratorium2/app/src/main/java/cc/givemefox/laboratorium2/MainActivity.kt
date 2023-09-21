package cc.givemefox.laboratorium2

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MenuItem
import android.view.Gravity
import android.widget.Toast
import java.lang.Float.min
import android.os.Bundle
import android.view.Menu
import android.view.View


class MainActivity : AppCompatActivity() {
    private lateinit var panel: Panel
    private val firstColor = Paint()
    private val secondColor = Paint()

    inner class Panel(context: Context) : View(context) {
        init {
            firstColor.color = Color.BLACK
            secondColor.color = Color.WHITE
        }

        override fun onDraw(canvas: Canvas) {
            val width = panel.width.toFloat()
            val height = panel.height.toFloat()
            val minSize = min(width, height)

            canvas.drawRGB(40, 40, 40)

            for (i in 0 until 8) {
                for (j in 0 until 8) {
                    val color = if ((i + j) % 2 == 0) firstColor else secondColor
                    val left = i * minSize / 8
                    val top = j * minSize / 8
                    val right = (i + 1) * minSize / 8
                    val bottom = (j + 1) * minSize / 8

                    canvas.drawRect(left, top, right, bottom, color)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.creator -> {
                val toast = Toast.makeText(applicationContext, "Creator: GiveMeFox", Toast.LENGTH_SHORT)

                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
            R.id.exit -> {
                finish()
            }
            R.id.black_and_white -> {
                setColors(Color.BLACK, Color.WHITE)
            }
            R.id.red_and_yellow -> {
                setColors(Color.RED, Color.YELLOW)
            }
            else -> return super.onOptionsItemSelected(item)
        }

        panel.postInvalidate()

        return true
    }

    private fun setColors(first: Int, second: Int) {
        firstColor.color = first
        secondColor.color = second
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Panel(this).also { panel = it })

        if (savedInstanceState != null) {
            firstColor.color = savedInstanceState.getInt("first_color")
        }
        if (savedInstanceState != null) {
            secondColor.color = savedInstanceState.getInt("second_color")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("first_color", firstColor.color)
            putInt("second_color", secondColor.color)
        }

        super.onSaveInstanceState(outState)
    }
}
