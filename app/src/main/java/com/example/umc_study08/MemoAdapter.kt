package com.example.umc_study08

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter(
    private val memoList: MutableList<Memo>,
    private val onItemClick: (position: Int) -> Unit,
    private val onFavoriteClick: (position: Int) -> Unit,
    private val onSwitchClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    inner class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memoTextView: TextView = itemView.findViewById(R.id.memoTextView)
        val btnFavorite: Button = itemView.findViewById(R.id.btn_favorite)
        val switchFinished: Switch = itemView.findViewById(R.id.switch1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return MemoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = memoList[position]
        holder.memoTextView.text = memo.content

        // 즐겨찾기 버튼 클릭 처리
        holder.btnFavorite.setOnClickListener {
            onFavoriteClick(position)
        }

        // 완료 여부 스위치 클릭 처리
        holder.switchFinished.isChecked = memo.isFinished
        holder.switchFinished.setOnCheckedChangeListener { _, isChecked ->
            onSwitchClick(position)
        }

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}
