package megh.mcjcbd

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import megh.mcjcbd.ui.theme.McjcbdTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            McjcbdTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage()
                }
            }
        }
    }
}

@Composable
fun GreetingText(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.uiabday) }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Happy Birthday,",
            fontSize = 50.sp,
            lineHeight = 55.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )
        Text(
            text = "$name.",
            fontSize = 60.sp,
            lineHeight = 60.sp,
            textAlign = TextAlign.End,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )

        ElevatedButton(
            onClick = {
                if (!isPlaying) {
                    isPlaying = true
                    mediaPlayer.start()
                    mediaPlayer.setOnCompletionListener {
                        isPlaying = false
                        showDialog = true
                    }
                }
            }, shape = RoundedCornerShape(12.dp),
            enabled = !isPlaying,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF4F2548),
                contentColor = Color.Yellow,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.DarkGray
            ), modifier = Modifier
                .shadow(
                    4.dp,
                    shape = RoundedCornerShape(12.dp),
                )
        ) {
            Text(
                text = if (isPlaying) "< spinning cat.gif >" else "Receive your gift",
                fontSize = 21.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Image(
            painter = painterResource(R.drawable.chervela),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            alpha = 0.8f,
            modifier = Modifier
                .border(
                    BorderStroke(10.dp, Color(0xFF3A1C35)),
                    RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .width(300.dp)
        )

        if (showDialog) {
            val names = listOf(
                "Manasvi Chervela",
                "Dopysvi Camera",
                "Lampsvi Candela",
                "Pamasvi Stairwella",
                "Moneysvi Cashvela",
                "Brokesvi Scamvela",
                "Clonesvi Doubela",
                "Dancevi Ballera",
                "Lil Peepee",
                "Manasfeet",
                "Man-asvi",
                "Manas W i",
                "Monoshbi Cherbela",
            )

            val randomName = remember { names.random() }

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("It is your birthday.") },
                text = { Text("Dear ${randomName},\n\nHappy Birthday. Wishing you lots of wealth, health, happiness, prosperity, security and longevity. 🎁") },
                confirmButton = {
                    ElevatedButton(onClick = { showDialog = false }) {
                        Text("Noted")
                    }
                }
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                mediaPlayer.release()
            }
        }
    }
}

@Composable
fun GreetingImage() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.androidparty),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        GreetingText("Manasvi Chervela", modifier = Modifier.fillMaxSize().padding(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingTextPreview() {
    McjcbdTheme {
        GreetingImage()
    }
}