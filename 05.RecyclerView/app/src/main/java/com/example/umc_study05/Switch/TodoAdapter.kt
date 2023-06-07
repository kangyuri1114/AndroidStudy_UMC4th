package com.example.umc_study05.Switch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_study05.databinding.TodoItemBinding

class TodoAdapter(private val itemList: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Todo) {
            binding.todoText.text = item.text
            binding.switch1.setOnCheckedChangeListener(null) // 기존 리스너 제거

            // 아이템 데이터의 isChecked 값에 따라 스위치 상태 설정
            binding.switch1.isChecked = item.isChecked

            binding.switch1.setOnCheckedChangeListener { _, isChecked ->
                item.isChecked = isChecked //값 변경 시 마다 isChecked 업데이트
            }
        }
    }
}