package com.ejemplo.alarmafamiliar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ejemplo.alarmafamiliar.ui.navigation.AlarmaNavHost
import com.ejemplo.alarmafamiliar.ui.theme.AlarmaFamiliarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlarmaFamiliarTheme {
                AlarmaNavHost()
            }
        }
    }
}