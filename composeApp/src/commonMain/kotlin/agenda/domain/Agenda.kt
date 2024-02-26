package agenda.domain

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

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
)

@Serializable
@JvmInline
value class Player(private val value: String) {
    override fun toString(): String = value
}
@Serializable
data class Day(val number: Int, val dayOfWeek: DayOfWeek)
@Serializable
@JvmInline
value class Year(private val value: Int) {
    override fun toString(): String = value.toString()
}
@Serializable
@JvmInline
value class Week(private val value: Int) {
    override fun toString(): String = value.toString()
}
@Serializable
enum class HourType { TEAM_TRAINING, ADULT_ACADEMY, KIDS_ACADEMY, MEMBERS_TIME }
@Serializable
data class MaxCapacity(val value: Int = 8)