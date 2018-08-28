package fornaro.com.br.todolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import fornaro.com.br.todolist.database.converters.DateConverter
import fornaro.com.br.todolist.database.dao.ToDoDao
import fornaro.com.br.todolist.model.ToDo

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "database")
                        .build()
            }
            return instance!!
        }
    }
}