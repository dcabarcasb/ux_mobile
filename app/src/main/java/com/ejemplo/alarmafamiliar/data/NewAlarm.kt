package com.ejemplo.alarmafamiliar.data

object NewAlarm {
    var eventType: EventType = EventType.MEDICAL
    var title: String = "Cita médica de Samuel"
    var date: String = "08 / 09 / 2026"
    var time: String = "3:00 p. m."
    var days: List<String> = listOf("L", "M", "M", "J", "V", "S", "D")
    var advanceNotice: String = "30 minutos antes"
    var sharedWith: List<String> = listOf("Mamá", "Papá", "Samuel")
    var location: String = "Hospital Central"
    var address: String = "#100-2 Av. Principal"
    var notes: String = "Llevar documentos médicos"

    fun reset() {
        eventType = EventType.MEDICAL
        title = "Cita médica de Samuel"
        date = "08 / 09 / 2026"
        time = "3:00 p. m."
        days = listOf("L", "M", "M", "J", "V", "S", "D")
        advanceNotice = "30 minutos antes"
        sharedWith = listOf("Mamá", "Papá", "Samuel")
        location = "Hospital Central"
        address = "#100-2 Av. Principal"
        notes = "Llevar documentos médicos"
    }
}

val NOTICE_OPTIONS = listOf(
    "10 minutos antes",
    "30 minutos antes",
    "1 hora antes",
    "1 día antes"
)