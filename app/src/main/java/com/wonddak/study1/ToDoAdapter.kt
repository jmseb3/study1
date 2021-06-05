package com.wonddak.study1

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wonddak.study1.databinding.ItemTodoListBinding
import com.wonddak.study1.room.AppDatabase
import com.wonddak.study1.room.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ToDoAdapter(
    val itemlist: List<ToDoData>,
    val db: AppDatabase
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(binding: ItemTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val item_text = binding.itemText
        val item_btn_delete = binding.itemBtnDelete

        init {
            item_btn_delete.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    db.TodoDao().DeleteById(itemlist[layoutPosition])
                }
            }
            item_text.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    if (itemlist[layoutPosition].check) {
                        db.TodoDao().updateCheckFalseById(itemlist[layoutPosition].id!!)
                    } else {
                        db.TodoDao().updateCheckTrueById(itemlist[layoutPosition].id!!)
                    }

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.item_text.text = itemlist[position].todo
        if (itemlist[position].check) {
            holder.item_text.paintFlags =
                holder.item_text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.item_text.paintFlags =
                holder.item_text.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


}