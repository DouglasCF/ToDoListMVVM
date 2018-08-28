package fornaro.com.br.todolist.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import fornaro.com.br.todolist.R
import fornaro.com.br.todolist.databinding.ActivityMainBinding
import fornaro.com.br.todolist.model.ToDo
import fornaro.com.br.todolist.view.newtodo.NewTodoActivity
import fornaro.com.br.todolist.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.OnItemListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()

        setupViewModel()

        fab.setOnClickListener { openNewToDoScreen() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getToDoList().observe(this, Observer {
            binding.isEmpty = it == null || it.isEmpty()
            viewAdapter.setData(it!!)
        })
    }

    private fun setupRecyclerView() {
        viewAdapter = MainAdapter(this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = viewAdapter
        }
    }

    private fun openNewToDoScreen() {
        startActivity(Intent(this, NewTodoActivity::class.java))
    }

    override fun onClick(toDo: ToDo) {
        viewModel.setDone(toDo)
        Snackbar.make(layout, R.string.todo_marked_as_done, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.undo) { viewModel.undoTodo(toDo) }
                .show()
    }

    override fun onLongClick(toDo: ToDo) {

    }
}
