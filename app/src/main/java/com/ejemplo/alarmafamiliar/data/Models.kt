package com.ejemplo.alarmafamiliar.data

enum class EventType(val label: String, val icon: String) {
    MEDICAL("Cita médica", "medical"),
    SCHOOL_MEETING("Reunión escolar", "school_meeting"),
    SCHOOL_SCHEDULE("Horario escolar", "school_schedule"),
    FAMILY_EVENT("Evento familiar", "family"),
    LEGAL("Reunión legal", "legal"),
    OTHER("Otro evento", "other")
}

data class FamilyMember(
    val name: String,
    val initial: String,
    val receivesAlarms: Boolean = true
)

data class Alarm(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val notes: String = "",
    val eventLabel: String = "",
    val isActive: Boolean = true,
    val advanceNotice: String = "30 minutos antes"
)

data class AlarmGroup(
    val label: String,
    val alarms: List<Alarm>
)