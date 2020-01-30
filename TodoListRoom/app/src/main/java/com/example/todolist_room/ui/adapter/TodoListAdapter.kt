package com.example.todolist_room.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_room.R
import com.example.todolist_room.data.db.entities.TodoListItem
import com.example.todolist_room.ui.taskList.TodoListViewModel
import kotlinx.android.synthetic.main.list_item_layout.view.*
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.nio.file.Files.delete
import java.util.concurrent.ExecutorService


class TodoListAdapter(
    private val context: Context,
    private val viewModel: TodoListViewModel,
    tasks: List<TodoListItem>
):RecyclerView.Adapter<TodoListAdapter.ToDoViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var todoItems = tasks

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(todoListItem: TodoListItem) {

            itemView.itemTitle.text = todoListItem.desc
            itemView.status.isChecked = todoListItem.done
            itemView.status.setOnCheckedChangeListener { _, _ ->
                todoListItem.done = itemView.status.isChecked
                viewModel.update(todoListItem)
            }

            itemView.setOnLongClickListener{
                deleteDialog(todoListItem)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_layout,parent,false)
        return ToDoViewHolder(itemView)
    }

    override fun getItemCount(): Int = todoItems.size


    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

//    internal fun setTasks(tasks: List<TodoListItem>){
//        this.todoItems = tasks
//    }

    fun deleteDialog(todoListItem: TodoListItem){
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Delete")
        alert.setPositiveButton("YES") { _, _ ->
                viewModel.delete(todoListItem)
//                notifyDataSetChanged()
        }
        alert.setNegativeButton("Cancel") { _, _ ->  }
        alert.create().show()
    }

}