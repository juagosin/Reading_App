package com.juagosin.readingAPP.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.juagosin.readingAPP.presentation.navigation.MyScaffold
import com.juagosin.readingAPP.presentation.theme.EstudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstudioTheme {
                MyScaffold(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
