package fornaro.com.br.todolist.view.newtodo

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import fornaro.com.br.todolist.R
import fornaro.com.br.todolist.viewmodel.NewTodoViewModel
import kotlinx.android.synthetic.main.item_todo.*

class NewTodoActivity : AppCompatActivity(), NewTodoCallback {

    private lateinit var viewModel: NewTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_todo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_save -> {
                save()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onTitleEmpty() {
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(NewTodoViewModel::class.java)
        viewModel.callback = this
    }

    private fun save() {
        val title = titleText.text.toString()
        if (viewModel.canSaveNewToDoItem(title)) {
            viewModel.insert(title, descriptionText.text.toString())

            finish()
        }
    }
}
