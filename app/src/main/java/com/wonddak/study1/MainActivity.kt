package com.wonddak.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    private var backKeyPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)

        binding.btnAdd.setOnClickListener {
            val text_data = binding.textInput.text.toString()

            if (text_data.isNotBlank() or text_data.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.IO) {
                    db.TodoDao().insertTODO(ToDoData(null, text_data))
                    binding.textInput.setText("")
                }
            }else{
                Toast.makeText(this,"내용을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
        }

        db.TodoDao().getToDoData().observe(this, Observer {
            adapter = ToDoAdapter(it, db)
            binding.todoList.adapter = adapter
            binding.todoList.layoutManager = LinearLayoutManager(this)
        })

    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
    }
}