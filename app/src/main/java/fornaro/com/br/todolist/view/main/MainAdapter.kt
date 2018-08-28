package fornaro.com.br.todolist.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fornaro.com.br.todolist.databinding.ItemTodoBinding
import fornaro.com.br.todolist.model.ToDo

class MainAdapter(private val listener: OnItemListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface OnItemListener {
        fun onClick(toDo: ToDo)
        fun onLongClick(toDo: ToDo)
    }

    private val list = mutableListOf<ToDo>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val toDo = list[position]
        holder.bind(toDo)
        holder.itemView.apply {
            setOnClickListener {
                listener.onClick(toDo)
                list.removeAt(position)
                notifyItemRemoved(position)
            }
            setOnLongClickListener {
                listener.onLongClick(toDo)
                true
            }
        }
    }

    fun setData(data: List<ToDo>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDo) {
            binding.todo = toDo
            binding.executePendingBindings()
        }

    }
}