package com.ejemplo.alarmafamiliar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
import com.ejemplo.alarmafamiliar.ui.components.Avatar
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.theme.AppBackground
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.SosRed
import com.ejemplo.alarmafamiliar.ui.theme.White

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Cuenta y preferencias")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))
            Avatar(initial = FakeData.user.initial, size = 84)
            Spacer(Modifier.height(12.dp))
            Text(
                text = FakeData.user.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Usuario de Alarma Familiar",
                fontSize = 13.sp,
                color = Navy.copy(alpha = 0.55f)
            )

            Spacer(Modifier.height(28.dp))
            Surface(
                color = White,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ProfileRow(
                        icon = Icons.Filled.Person,
                        label = "Datos personales",
                        iconTint = com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
                    )
                    androidx.compose.material3.HorizontalDivider(color = Navy.copy(alpha = 0.06f))
                    ProfileRow(
                        icon = Icons.Filled.Notifications,
                        label = "Notificaciones",
                        iconTint = com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
                    )
                    androidx.compose.material3.HorizontalDivider(color = Navy.copy(alpha = 0.06f))
                    ProfileRow(
                        icon = Icons.Filled.Delete,
                        label = "Cerrar sesión",
                        iconTint = SosRed,
                        labelColor = SosRed,
                        onClick = onLogout
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileRow(
    icon: ImageVector,
    label: String,
    iconTint: Color,
    labelColor: Color = Navy,
    onClick: (() -> Unit)? = null
) {
    val action = onClick ?: {}
    Surface(
        onClick = action,
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth(),
        enabled = onClick != null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(iconTint.copy(alpha = 0.12f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.size(14.dp))
            Text(
                text = label,
                fontSize = 15.sp,
                fontWeight = if (iconTint == SosRed) FontWeight.SemiBold else FontWeight.Medium,
                color = labelColor
            )
        }
    }
}