package fornaro.com.br.todolist.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import android.text.format.DateUtils
import java.util.*

@Entity
data class ToDo(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var title: String,
        var description: String?,
        var date: Date = Date(),
        var check: Boolean = false
) {
    fun toDateString(): String {
        return DateUtils.getRelativeTimeSpanString(date.time, DateUtils.DAY_IN_MILLIS,
                DateUtils.DAY_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL) as String
    }
}