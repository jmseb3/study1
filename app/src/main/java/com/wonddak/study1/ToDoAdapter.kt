package com.wonddak.study1

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wonddak.study1.databinding.ItemTodoListBinding

class ToDoAdapter(
        val itemlist: List<String>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(binding: ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_text = binding.itemText
        val item_btn_delete = binding.itemBtnDelete

        init {
            item_btn_delete.setOnClickListener {
                itemlist as ArrayList<String>
                itemlist.removeAt(layoutPosition)
                notifyDataSetChanged()
            }
            item_text.setOnClickListener {
                item_text.paintFlags = item_text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.item_text.text = itemlist[position]
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


}