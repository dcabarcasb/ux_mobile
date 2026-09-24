package com.ejemplo.alarmafamiliar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.alarmafamiliar.data.FakeData
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.components.PrimaryButton
import com.ejemplo.alarmafamiliar.ui.components.SectionTitle
import com.ejemplo.alarmafamiliar.ui.theme.CardBlueTint
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
import com.ejemplo.alarmafamiliar.ui.theme.SosRed
import com.ejemplo.alarmafamiliar.ui.theme.White

@Composable
fun HomeScreen(
    onNavigateToAlarmas: () -> Unit,
    onNavigateToMapa: () -> Unit,
    onCreateAlarm: () -> Unit,
    onSos: () -> Unit
) {
    val next = FakeData.alarmGroups.first().alarms.first()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(com.ejemplo.alarmafamiliar.ui.theme.AppBackground)
    ) {
        BrandHeader(subtitle = "Hola, Juan")

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tu familia, organizada y conectada",
                fontSize = 14.sp,
                color = Navy.copy(alpha = 0.6f)
            )
            Spacer(Modifier.height(20.dp))

            SectionTitle(text = "Próxima alarma")
            Spacer(Modifier.height(8.dp))
            Surface(
                color = CardBlueTint,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(PrimaryBlue, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.NotificationsActive,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = next.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "${next.date} · ${next.time} · ${next.location}",
                            fontSize = 13.sp,
                            color = Navy.copy(alpha = 0.7f)
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Navy
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            SectionTitle(text = "Alarmas familiares")
            Spacer(Modifier.height(8.dp))
            Surface(
                color = White,
                shape = RoundedCornerShape(16.dp),
                onClick = onCreateAlarm,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddAlarm,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Programar alarma",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = "Crear un nuevo evento familiar",
                            fontSize = 13.sp,
                            color = Navy.copy(alpha = 0.6f)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = null,
                        tint = Navy.copy(alpha = 0.4f)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            SectionTitle(text = "Acciones rápidas")
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickAction(
                    title = "SOS",
                    subtitle = "Emergencia",
                    icon = Icons.Filled.Sos,
                    container = SosRed,
                    onClick = onSos,
                    modifier = Modifier.weight(1f)
                )
                QuickAction(
                    title = "Ubicación",
                    subtitle = "Mapa familiar",
                    icon = Icons.Filled.LocationOn,
                    container = PrimaryBlue,
                    onClick = onNavigateToMapa,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(20.dp))
            PrimaryButton(text = "+ Programar alarma", onClick = onCreateAlarm)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun QuickAction(
    title: String,
    subtitle: String,
    icon: ImageVector,
    container: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = container,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = modifier.height(110.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = if (title == "SOS") Icons.Filled.Warning else icon,
                contentDescription = title,
                tint = White,
                modifier = Modifier.size(30.dp)
            )
            Column {
                Text(
                    text = title,
                    color = White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    color = White.copy(alpha = 0.85f),
                    fontSize = 12.sp
                )
            }
        }
    }
}