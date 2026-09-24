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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.alarmafamiliar.data.Alarm
import com.ejemplo.alarmafamiliar.data.AlarmGroup
import com.ejemplo.alarmafamiliar.data.FakeData
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.components.PrimaryButton
import com.ejemplo.alarmafamiliar.ui.components.SectionTitle
import com.ejemplo.alarmafamiliar.ui.theme.AppBackground
import com.ejemplo.alarmafamiliar.ui.theme.CardBlueTint
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.OnTealAccent
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
import com.ejemplo.alarmafamiliar.ui.theme.TealAccent
import com.ejemplo.alarmafamiliar.ui.theme.White

@Composable
fun AlarmsScreen(onCreateAlarm: () -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val titleTab = if (tabIndex == 0) "Próximas y hoy" else "Historial"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Alarmas familiares")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = White,
                contentColor = PrimaryBlue,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .tabIndicatorOffset(tabPositions[tabIndex]),
                        color = PrimaryBlue
                    )
                }
            ) {
                Tab(
                    selected = tabIndex == 0,
                    onClick = { tabIndex = 0 },
                    text = {
                        Text("PRÓXIMAS Y HOY", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Navy)
                    }
                )
                Tab(
                    selected = tabIndex == 1,
                    onClick = { tabIndex = 1 },
                    text = {
                        Text("HISTORIAL", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Navy)
                    }
                )
            }

            Spacer(Modifier.height(16.dp))
            if (tabIndex == 0) {
                FakeData.alarmGroups.forEach { group ->
                    AlarmGroupSection(group)
                }
            } else {
                FakeData.historyAlarms.forEach { alarm ->
                    HistoryCard(alarm)
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        PrimaryButton(
            text = "+ Programar alarma",
            onClick = onCreateAlarm,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun AlarmGroupSection(group: AlarmGroup) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SectionTitle(text = group.label)
        Spacer(Modifier.height(8.dp))
        group.alarms.forEach { alarm ->
            AlarmCard(alarm)
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun AlarmCard(alarm: Alarm) {
    Surface(
        color = White,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(CardBlueTint, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Alarm,
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alarm.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${alarm.time} · ${alarm.location}",
                    fontSize = 13.sp,
                    color = Navy.copy(alpha = 0.6f)
                )
            }
            Surface(
                color = TealAccent,
                shape = RoundedCornerShape(50)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = OnTealAccent,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        text = "Activa",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnTealAccent
                    )
                }
            }
        }
    }
}

@Composable
private fun HistoryCard(alarm: Alarm) {
    Surface(
        color = White,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Navy.copy(alpha = 0.08f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Alarm,
                    contentDescription = null,
                    tint = Navy.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alarm.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${alarm.time} · ${alarm.location}",
                    fontSize = 13.sp,
                    color = Navy.copy(alpha = 0.45f)
                )
            }
            Text(
                text = "Completada",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy.copy(alpha = 0.45f)
            )
        }
    }
}