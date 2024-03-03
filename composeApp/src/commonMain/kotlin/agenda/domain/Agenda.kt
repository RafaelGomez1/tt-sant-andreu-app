package agenda.domain

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlinx.serialization.Serializable

@Serializable
data class Agenda(
    val id: String,
    val day: Day,
    val month: Month,
    val week: Week,
    val year: Year,
    val availableHours: List<AvailableHour> = emptyList()
)

@Serializable
data class AvailableHour(
    val id: String,
    val from: Int,
    val to: Int,
    val capacity: MaxCapacity,
    val type: HourType,
    val registeredPlayers: List<Player>
) {
    fun isAtMaxCapacity(): Boolean = registeredPlayers.size == capacity.value
    fun isNotAtMaxCapacity(): Boolean = !isAtMaxCapacity()
    fun isNotEmpty(): Boolean = registeredPlayers.isNotEmpty()
}

@Serializable
data class Player(val name: String) {
    override fun toString(): String = name
}
@Serializable
data class Day(val number: Int, val dayOfWeek: DayOfWeek)

typealias Year = Int
typealias Week = Int
@Serializable
enum class HourType { TEAM_TRAINING, ADULT_ACADEMY, KIDS_ACADEMY, MEMBERS_TIME }
@Serializable
data class MaxCapacity(val value: Int = 8)