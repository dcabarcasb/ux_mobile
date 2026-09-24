package com.ejemplo.alarmafamiliar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.alarmafamiliar.ui.components.PrimaryButton
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
import com.ejemplo.alarmafamiliar.ui.theme.White

private data class Feature(val icon: ImageVector, val text: String)

@Composable
fun OnboardingScreen(onLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            color = PrimaryBlue,
            shape = CircleShape,
            modifier = Modifier.size(96.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Alarm,
                contentDescription = "Alarma Familiar",
                tint = White,
                modifier = Modifier.padding(24.dp)
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "ALARMA FAMILIAR",
            color = White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Tu familia, organizada y conectada",
            color = White.copy(alpha = 0.85f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(40.dp))

        listOf(
            Feature(Icons.Filled.NotificationsActive, "Organiza las actividades de tu familia"),
            Feature(Icons.Filled.Groups, "Eventos y recordatorios compartidos"),
            Feature(Icons.Filled.CheckCircle, "Alarmas para todos los integrantes")
        ).forEach { feature ->
            Surface(
                color = White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(
                        imageVector = feature.icon,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = feature.text,
                        color = White,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(40.dp))
        PrimaryButton(text = "Iniciar sesión", onClick = onLogin)
        Spacer(Modifier.height(24.dp))
    }
}