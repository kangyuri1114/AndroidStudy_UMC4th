package com.example.umc_study08


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

class MemoAdapter(
    private val memoList: MutableList<Memo>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    inner class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memoTextView: TextView = itemView.findViewById(R.id.memoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return MemoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = memoList[position]
        holder.memoTextView.text = memo.content

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}