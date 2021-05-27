package com.wonddak.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wonddak.study1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var todolist  = mutableListOf("테스트1","테스트2","테스트3")
    private var adapter: ToDoAdapter? = null


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            todolist.add(binding.textInput.text.toString())
            adapter!!.notifyDataSetChanged()
            binding.textInput.setText("")
        }

        adapter = ToDoAdapter(todolist)
        binding.todoList.adapter = adapter
        binding.todoList.layoutManager = LinearLayoutManager(this)


    }
}