package fornaro.com.br.todolist.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import fornaro.com.br.todolist.database.AppDatabase
import fornaro.com.br.todolist.model.ToDo
import org.jetbrains.anko.doAsync

class ToDoRepository(application: Application) {

    private val dao = AppDatabase.getInstance(application).toDoDao()
    private val allToDo = dao.getAll()

    fun getToDoList(): LiveData<List<ToDo>> {
        return allToDo
    }

    fun insert(todo: ToDo) {
        doAsync { dao.insert(todo) }
    }

    fun update(toDo: ToDo) {
        doAsync { dao.update(toDo) }
    }
}