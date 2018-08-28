package fornaro.com.br.todolist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import fornaro.com.br.todolist.model.ToDo
import fornaro.com.br.todolist.repository.ToDoRepository
import fornaro.com.br.todolist.view.newtodo.NewTodoCallback

class NewTodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ToDoRepository(application)
    var callback: NewTodoCallback? = null

    fun insert(title: String, description: String) {
        val todo = ToDo(title = title, description = description)
        repository.insert(todo)
    }

    fun canSaveNewToDoItem(text: String): Boolean {
        return if (text.isBlank()) {
            callback?.onTitleEmpty()
            false
        } else true
    }
}