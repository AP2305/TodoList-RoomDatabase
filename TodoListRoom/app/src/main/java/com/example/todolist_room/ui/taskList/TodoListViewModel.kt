package com.example.todolist_room.ui.taskList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist_room.data.db.TodoListDatabase
import com.example.todolist_room.data.db.entities.TodoListItem
import com.example.todolist_room.data.repositories.ToDoRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TodoListViewModel(
    application: Application
): AndroidViewModel(application){


    private val repository : ToDoRepo
    val todoList : LiveData<List<TodoListItem>>

    init {
        val dao = TodoListDatabase.getDatabase(application,viewModelScope).tododao()
        repository = ToDoRepo(dao)
        todoList = repository.todoList
    }

    fun insert(todoItem:TodoListItem) = viewModelScope.launch {
        repository.insert(todoItem)
    }

    fun update(todoItem: TodoListItem) = viewModelScope.launch {
        repository.update(todoItem)
    }

    fun delete(todoItem: TodoListItem) = viewModelScope.async {
        repository.delete(todoItem)
    }

}