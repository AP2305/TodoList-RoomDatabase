package com.example.todolist_room.data.repositories

import androidx.lifecycle.LiveData
import com.example.todolist_room.data.db.ToDoDAO
import com.example.todolist_room.data.db.entities.TodoListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ToDoRepo (private val tododao:ToDoDAO){

    val todoList : LiveData<List<TodoListItem>> = tododao.getList()

    suspend fun insert(todoListItem: TodoListItem){
        tododao.insert(todoListItem)
    }

    suspend fun update(todoListItem: TodoListItem){
        tododao.update(todoListItem)
    }

    suspend fun delete(todoListItem: TodoListItem){
        tododao.delete(todoListItem)
    }
}