package com.tranzzo.deeplinkdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.tranzzo.deeplinkdemo.ui.theme.DeepLinkDemoTheme

private const val URL_LINK = "https://demo.tranzzo.com/app/deeplink/index.html"
private const val RESULT_LINK = "demo.tranzzo.com/deeplink"

class MainActivity : ComponentActivity() {

    private val flowState: MutableState<Result> = mutableStateOf(Result.Start())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeepLinkDemoTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            when (flowState.value) {
                                is Result.Start -> {
                                    Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(URL_LINK)
                                        startActivity(this)
                                    }
                                }
                                is Result.Finish -> {
                                    flowState.value = Result.Start()
                                }
                            }
                        }
                    ) {
                        Text(
                            text = flowState.value.text,
                            style = TextStyle(color = Color.White)
                        )
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.dataString.orEmpty().contains(RESULT_LINK)) {
            flowState.value = Result.Finish()
        }
    }
}

sealed class Result {

    abstract val text: String

    class Start(override val text: String = "Go to RESULT PAGE!") : Result()

    class Finish(override val text: String = "Reset FLOW!") : Result()
}