package com.wonddak.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wonddak.study1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            binding.todoItem.text = binding.textInput.text
            binding.textInput.setText("")
        }
    }
}