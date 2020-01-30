package com.example.todolist_room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist_room.data.db.entities.TodoListItem
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = arrayOf(TodoListItem::class),
    version = 1
)
public abstract class TodoListDatabase : RoomDatabase(){
    abstract fun tododao() : ToDoDAO

    companion object{
        @Volatile
        private var INSTANCE : TodoListDatabase?=null
        fun getDatabase(context: Context,scope: CoroutineScope):TodoListDatabase{
            return INSTANCE ?: synchronized(this){
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 TodoListDatabase::class.java,
                 "todoDatabase")
                 .build()
                INSTANCE = instance
                instance
            }
        }
    }

}