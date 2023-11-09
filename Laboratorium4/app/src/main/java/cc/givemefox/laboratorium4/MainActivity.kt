package cc.givemefox.laboratorium4

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cc.givemefox.laboratorium4.ui.theme.Laboratorium4Theme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onStart() {
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onResume() {
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Toast.makeText(this, "SaveInstanceState", Toast.LENGTH_SHORT).show()
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onPause() {
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onStop() {
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onRestart() {
        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show()
        super.onRestart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)

        setContent {
            Laboratorium4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    TxtField()
                    DraggableFox()
                }
            }
        }
    }
}

@Composable
fun eventsHandler() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtField() {
    var text by remember { mutableStateOf("") }
    var bcState by remember { mutableStateOf("") }
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (bcState) {
                    "red" -> Color.Red
                    "green" -> Color.Green
                    "blue" -> Color.Blue
                    "yellow" -> Color.Yellow
                    "pink" -> Color.Magenta
                    else -> Color(
                        red = 39,
                        green = 41,
                        blue = 50,
                    )
                }
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = text,
            placeholder = {
                Text("input")
            },
            onValueChange = { text = it; bcState = it; },
        )

        Button(
            onClick = { activity?.finish() },
        ) {
            Text("Exit")
        }

        Text(
            text = "spy box - try clicking HOME and BACK",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun DraggableFox() {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels

    var offsetX by remember { mutableStateOf(width / 2f) }
    var offsetY by remember { mutableStateOf(height / 2f) }

    Text(text = "\uD83E\uDD8A",
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .size(100.dp, 100.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorium4Theme {
        DraggableFox()
        TxtField()
    }
}