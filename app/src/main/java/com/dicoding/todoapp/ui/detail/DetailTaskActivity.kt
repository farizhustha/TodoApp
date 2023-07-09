package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val id = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)
            .get(DetailTaskViewModel::class.java)

        viewModel.setTaskId(id)

        viewModel.task.observe(this) { task ->
            findViewById<TextInputEditText>(R.id.detail_ed_title).setText(task.title)
            findViewById<TextInputEditText>(R.id.detail_ed_description).setText(task.description)
            findViewById<TextInputEditText>(R.id.detail_ed_due_date)
                .setText(DateConverter.convertMillisToString(task.dueDateMillis))
            findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
                viewModel.deleteTask()
                Toast.makeText(this, R.string.delete_task_success, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}