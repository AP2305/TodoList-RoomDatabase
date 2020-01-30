package com.example.todolist_room.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist_room.data.db.entities.TodoListItem

@Dao
interface ToDoDAO{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(listItem:TodoListItem)

    @Update
    suspend fun update(listItem: TodoListItem)

    @Query("Select * from TodoList")
    fun getList(): LiveData<List<TodoListItem>>

    @Query("Delete from TodoList")
    suspend fun deleteList()

    @Delete
    suspend fun delete(listItem: TodoListItem)
}