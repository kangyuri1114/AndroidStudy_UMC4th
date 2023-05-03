package com.example.umc_study05


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_study05.databinding.ActivityMemoBinding

/**
 * RecyclerViewAdapter : Adapter 인자에 데이터를 넣어주면, MainActivity에서 수정하면 자동으로 수정됨
 *
 * DataViewHolder : ViewHolder 객체
 *
 * 필수 구현 메서드
 * - onCreateViewHolder : DataViewHolder 만들어질 때 실행할 동작
 * - onBindViewHolder : viewHolder가 실제로 데이터를 표현할 때 호출되는 함수
 * - getItemCount : 표현할 아이템의 총 개수
 *
 * */

class MyAdapter(private val listData: ArrayList<Member>, val onClickDeleteBtn: (data: Member) -> Unit): RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ActivityMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val member = listData[position]
        holder.setData(member)

        holder.binding.delete.setOnClickListener { onClickDeleteBtn.invoke(member) }
    }

    override fun getItemCount(): Int = listData.size
}

class DataViewHolder(val binding: ActivityMemoBinding) : RecyclerView.ViewHolder(binding.root){
    fun setData(member: Member){
        binding.tvMemo.text = member.Memo
    }
}