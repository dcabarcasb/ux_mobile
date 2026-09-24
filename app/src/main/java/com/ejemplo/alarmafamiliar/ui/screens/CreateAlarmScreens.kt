package com.ejemplo.alarmafamiliar.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.alarmafamiliar.data.EventType
import com.ejemplo.alarmafamiliar.data.NOTICE_OPTIONS
import com.ejemplo.alarmafamiliar.data.NewAlarm
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.components.PrimaryButton
import com.ejemplo.alarmafamiliar.ui.components.SectionTitle
import com.ejemplo.alarmafamiliar.ui.theme.AppBackground
import com.ejemplo.alarmafamiliar.ui.theme.CardBlueTint
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue
import com.ejemplo.alarmafamiliar.ui.theme.White

fun EventType.icon(): ImageVector = when (this) {
    EventType.MEDICAL -> Icons.Filled.MedicalServices
    EventType.SCHOOL_MEETING -> Icons.Filled.School
    EventType.SCHOOL_SCHEDULE -> Icons.Filled.Schedule
    EventType.FAMILY_EVENT -> Icons.Filled.FamilyRestroom
    EventType.LEGAL -> Icons.Filled.Gavel
    EventType.OTHER -> Icons.Filled.MoreHoriz
}

@Composable
fun CreateEventScreen(onBack: () -> Unit, onContinue: () -> Unit) {
    var selected by remember { mutableStateOf(NewAlarm.eventType) }
    var title by remember { mutableStateOf(NewAlarm.title) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Crear alarma", onBack = onBack)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Selecciona el tipo de evento",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(16.dp))

            EventType.entries.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    row.forEach { type ->
                        val isSelected = selected == type
                        Surface(
                            color = if (isSelected) CardBlueTint else White,
                            shape = RoundedCornerShape(14.dp),
                            border = if (isSelected) BorderStroke(2.dp, PrimaryBlue) else BorderStroke(1.dp, Navy.copy(alpha = 0.08f)),
                            onClick = { selected = type },
                            modifier = Modifier
                                .weight(1f)
                                .height(88.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Icon(
                                    imageVector = type.icon(),
                                    contentDescription = null,
                                    tint = if (isSelected) PrimaryBlue else Navy,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = type.label,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Navy
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(8.dp))
            SectionTitle(text = "Nombre del evento")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    NewAlarm.title = it
                },
                placeholder = { Text("Ej. Cita médica de Samuel") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Continuar", onClick = {
                NewAlarm.eventType = selected
                NewAlarm.title = title
                onContinue()
            })
            Spacer(Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateScheduleScreen(onBack: () -> Unit, onContinue: () -> Unit) {
    var date by remember { mutableStateOf(NewAlarm.date) }
    var time by remember { mutableStateOf(NewAlarm.time) }
    var days by remember { mutableStateOf(NewAlarm.days) }
    var notice by remember { mutableStateOf(NewAlarm.advanceNotice) }
    var share by remember { mutableStateOf(NewAlarm.sharedWith) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Crear alarma", onBack = onBack)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Configura cuándo recibir la alarma",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(16.dp))

            SectionTitle(text = "Fecha")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = date,
                onValueChange = {
                    date = it
                    NewAlarm.date = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue)
            )
            Spacer(Modifier.height(16.dp))

            SectionTitle(text = "Hora")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = time,
                onValueChange = {
                    time = it
                    NewAlarm.time = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue)
            )
            Spacer(Modifier.height(20.dp))

            SectionTitle(text = "Repetir")
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("L", "M", "M", "J", "V", "S", "D").forEach { day ->
                    val isOn = days.contains(day)
                    Surface(
                        onClick = {
                            days = if (isOn) days.filterNot { it == day } else days + day
                            NewAlarm.days = (if (isOn) days.filterNot { it == day } else days + day)
                        },
                        color = if (isOn) PrimaryBlue else White,
                        shape = CircleShape,
                        border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.3f)),
                        modifier = Modifier.size(width = 38.dp, height = 38.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = day,
                                color = if (isOn) White else Navy,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))

            SectionTitle(text = "Avisar con anticipación")
            Spacer(Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NOTICE_OPTIONS.forEach { option ->
                    FilterChip(
                        selected = notice == option,
                        onClick = {
                            notice = option
                            NewAlarm.advanceNotice = option
                        },
                        label = { Text(option, fontSize = 13.sp) },
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = White
                        )
                    )
                }
            }
            Spacer(Modifier.height(20.dp))

            SectionTitle(text = "Compartir con")
            Spacer(Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Mamá", "Papá", "Samuel").forEach { member ->
                    val isOn = share.contains(member)
                    FilterChip(
                        selected = isOn,
                        onClick = {
                            share = if (isOn) share.filterNot { it == member } else share + member
                            NewAlarm.sharedWith = (if (isOn) share.filterNot { it == member } else share + member)
                        },
                        label = { Text(member, fontSize = 13.sp) },
                        leadingIcon = if (isOn) {
                            {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = White
                        )
                    )
                }
            }

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Continuar", onClick = {
                NewAlarm.date = date
                NewAlarm.time = time
                NewAlarm.days = days
                NewAlarm.advanceNotice = notice
                NewAlarm.sharedWith = share
                onContinue()
            })
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun CreateLocationScreen(onBack: () -> Unit, onContinue: () -> Unit) {
    var location by remember { mutableStateOf(NewAlarm.location) }
    var address by remember { mutableStateOf(NewAlarm.address) }
    var notes by remember { mutableStateOf(NewAlarm.notes) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        BrandHeader(subtitle = "Crear alarma", onBack = onBack)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Agrega la ubicación del evento",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(16.dp))

            SectionTitle(text = "Lugar")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                    NewAlarm.location = it
                },
                leadingIcon = { Icon(Icons.Filled.LocationOn, contentDescription = null, tint = PrimaryBlue) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue)
            )
            Spacer(Modifier.height(16.dp))

            SectionTitle(text = "Dirección")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    NewAlarm.address = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue)
            )
            Spacer(Modifier.height(20.dp))

            MapPlaceholder(locationName = location)
            Spacer(Modifier.height(20.dp))

            SectionTitle(text = "Notas")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = notes,
                onValueChange = {
                    notes = it
                    NewAlarm.notes = it
                },
                placeholder = { Text("Agrega un detalle opcional") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryBlue)
            )

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Continuar", onClick = {
                NewAlarm.location = location
                NewAlarm.address = address
                NewAlarm.notes = notes
                onContinue()
            })
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun MapPlaceholder(locationName: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFE2E7ED), RoundedCornerShape(14.dp))
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Ubicación",
                tint = com.ejemplo.alarmafamiliar.ui.theme.SosRed,
                modifier = Modifier.size(34.dp)
            )
            Surface(
                color = White,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = locationName.ifBlank { "Hospital Central" },
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy
                )
            }
        }
    }
}

@Composable
fun CreateSuccessScreen(onViewAlarms: () -> Unit, onBackHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = com.ejemplo.alarmafamiliar.ui.theme.OnTealAccent,
                modifier = Modifier.size(84.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Alarma creada",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "El evento fue compartido con tu familia",
                fontSize = 14.sp,
                color = Navy.copy(alpha = 0.6f)
            )

            Spacer(Modifier.height(28.dp))
            Surface(
                color = White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = NewAlarm.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )
                    Spacer(Modifier.height(12.dp))
                    SuccessRow(label = "Fecha", value = NewAlarm.date)
                    SuccessRow(label = "Hora", value = NewAlarm.time)
                    SuccessRow(label = "Lugar", value = NewAlarm.location)
                    SuccessRow(label = "Aviso", value = NewAlarm.advanceNotice)
                }
            }

            Spacer(Modifier.height(32.dp))
            PrimaryButton(text = "Ver mis alarmas", onClick = onViewAlarms)
            Spacer(Modifier.height(12.dp))
            Surface(
                onClick = onBackHome,
                color = Color.Transparent,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "VOLVER AL INICIO",
                    modifier = Modifier.padding(14.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = PrimaryBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SuccessRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 14.sp,
            color = Navy.copy(alpha = 0.6f),
            modifier = Modifier.width(72.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Navy
        )
    }
}