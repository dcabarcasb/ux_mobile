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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.alarmafamiliar.data.FakeData
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.theme.AppBackground
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
import com.ejemplo.alarmafamiliar.ui.theme.SosRed
import com.ejemplo.alarmafamiliar.ui.theme.White

@Composable
fun MapScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Mapa familiar")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Ubicaciones vinculadas a tu familia",
                fontSize = 14.sp,
                color = Navy.copy(alpha = 0.6f)
            )
            Spacer(Modifier.height(16.dp))

            MapPlaceholder(
                locationName = "Hospital Central",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(Modifier.height(20.dp))
            FakeData.mapLocations.forEach { loc ->
                Surface(
                    color = White,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(PrimaryBlue.copy(alpha = 0.12f), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = null,
                                tint = PrimaryBlue,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = loc.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Navy
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = loc.caption,
                                fontSize = 13.sp,
                                color = Navy.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}