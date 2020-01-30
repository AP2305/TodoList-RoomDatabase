package com.example.todolist_room.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoList")
data class TodoListItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "description")
    var desc:String,
    var done:Boolean
)