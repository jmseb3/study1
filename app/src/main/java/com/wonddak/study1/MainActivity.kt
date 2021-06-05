package com.wonddak.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wonddak.study1.databinding.ActivityMainBinding
import com.wonddak.study1.room.AppDatabase
import com.wonddak.study1.room.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var adapter: ToDoAdapter? = null


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db =AppDatabase.getInstance(this)

        binding.btnAdd.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.TodoDao().insertTODO(ToDoData(null,binding.textInput.text.toString()))
                binding.textInput.setText("")
            }
        }

        db.TodoDao().getToDoData().observe(this, Observer {
            adapter = ToDoAdapter(it,db)
            binding.todoList.adapter = adapter
            binding.todoList.layoutManager = LinearLayoutManager(this)
        })

    }
}