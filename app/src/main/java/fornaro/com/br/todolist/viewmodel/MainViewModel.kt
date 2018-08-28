package fornaro.com.br.todolist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import fornaro.com.br.todolist.model.ToDo
import fornaro.com.br.todolist.repository.ToDoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ToDoRepository(application)
    private var liveData: LiveData<List<ToDo>>? = null

    fun getToDoList(): LiveData<List<ToDo>> {
        if (liveData == null) {
            liveData = repository.getToDoList()
        }
        return liveData!!
    }

    fun setDone(toDo: ToDo) {
        toDo.check = true
        repository.update(toDo)
    }

    fun undoTodo(toDo: ToDo) {
        toDo.check = false
        repository.update(toDo)
    }

}