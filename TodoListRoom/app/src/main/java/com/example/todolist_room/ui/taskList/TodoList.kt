package com.example.todolist_room.ui.taskList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist_room.R
import com.example.todolist_room.data.db.entities.TodoListItem
import com.example.todolist_room.ui.adapter.TodoListAdapter
import com.example.todolist_room.ui.addTask.AddTask
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.*

class TodoList : AppCompatActivity() {

    private lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)


        recyclerview.layoutManager = LinearLayoutManager(this)

        floatingActioonBtn.setOnClickListener {
            startActivityForResult(Intent(this,AddTask::class.java),1)
        }

        viewModel.todoList.observe(this, Observer {
            val adapter = TodoListAdapter(this,viewModel,it)
            recyclerview.adapter = adapter
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("addTask")?.let {
                var todoitem = TodoListItem(0,it, false)
                viewModel.insert(todoitem)
            }
        }
    }
}
