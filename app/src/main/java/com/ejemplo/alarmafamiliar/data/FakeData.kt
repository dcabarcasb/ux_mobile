package com.ejemplo.alarmafamiliar.data

object FakeData {

    val user = FamilyMember(name = "Juan", initial = "J", receivesAlarms = true)

    val familyMembers = listOf(
        FamilyMember(name = "Mamá", initial = "M", receivesAlarms = true),
        FamilyMember(name = "Papá", initial = "P", receivesAlarms = true),
        FamilyMember(name = "Samuel", initial = "S", receivesAlarms = true)
    )

    val alarmGroups = listOf(
        AlarmGroup(
            label = "HOY",
            alarms = listOf(
                Alarm(
                    id = 1,
                    title = "Cita médica de Samuel",
                    date = "Hoy",
                    time = "3:00 p. m.",
                    location = "Hospital Central",
                    eventLabel = "Cita médica",
                    notes = "Llevar documentos médicos"
                )
            )
        ),
        AlarmGroup(
            label = "MAÑANA",
            alarms = listOf(
                Alarm(
                    id = 2,
                    title = "Reunión escolar",
                    date = "Mañana",
                    time = "8:00 a. m.",
                    location = "Colegio",
                    eventLabel = "Reunión escolar"
                )
            )
        ),
        AlarmGroup(
            label = "VIERNES",
            alarms = listOf(
                Alarm(
                    id = 3,
                    title = "Reunión legal",
                    date = "Viernes",
                    time = "10:00 a. m.",
                    location = "Juzgado",
                    eventLabel = "Reunión legal"
                )
            )
        )
    )

    val historyAlarms = listOf(
        Alarm(
            id = 4,
            title = "Horario escolar",
            date = "Lunes",
            time = "7:30 a. m.",
            location = "Colegio",
            eventLabel = "Horario escolar",
            isActive = false
        )
    )

    val mapLocations = listOf(
        MapLocation(
            name = "Hospital Central",
            caption = "Cita de Samuel · 3:00 p. m."
        ),
        MapLocation(
            name = "Colegio Samuel",
            caption = "Reunión escolar · 8:00 a. m."
        )
    )
}

data class MapLocation(
    val name: String,
    val caption: String
)