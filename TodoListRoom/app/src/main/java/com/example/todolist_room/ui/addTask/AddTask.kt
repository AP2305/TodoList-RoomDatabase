package com.example.todolist_room.ui.addTask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist_room.R
import com.example.todolist_room.ui.taskList.TodoList
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        saveTask.setOnClickListener {
            val intent = Intent(this,TodoList::class.java)
            if(task.text.isNullOrBlank()){
                task.error = "Please fill this Field"
            }else{
                val taskText = task.text.toString()

                intent.putExtra("addTask",taskText)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }

    override fun onBackPressed() {

        val intent =  Intent(this,TodoList::class.java)
        setResult(Activity.RESULT_CANCELED,intent)
        finish()
    }

}
