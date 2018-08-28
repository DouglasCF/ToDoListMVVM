package fornaro.com.br.todolist.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import fornaro.com.br.todolist.model.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDo WHERE `check` = 0")
    fun getAll(): LiveData<List<ToDo>>

    @Insert
    fun insert(toDo: ToDo)

    @Delete
    fun delete(toDo: ToDo)

    @Update
    fun update(toDo: ToDo)
}