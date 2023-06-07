package com.example.umc_study08

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_study08.databinding.ActivityFavoriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: MemoAdapter
    private lateinit var favoriteList: MutableList<Memo>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var memoDatabase: MemoDatabase
    private lateinit var memoList: List<Memo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("memo_preferences", Context.MODE_PRIVATE)
        memoDatabase = MemoDatabase.getInstance(this)
        favoriteList = mutableListOf()

        favoriteAdapter = MemoAdapter(favoriteList, onItemClick = { position ->
            val memo = favoriteList[position]
            // 클릭한 메모에 대한 처리 작성
        }, onFavoriteClick = { position ->
            val memo = favoriteList[position]
            toggleFavorite(memo)
        }, onSwitchClick = { position ->
            val memo = favoriteList[position]
            toggleSwitch(memo)
        })

        binding.recyclerViewFavorite.adapter = favoriteAdapter
        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch(Dispatchers.IO) {
            memoList = memoDatabase.memoDao().getAllMemos()

            withContext(Dispatchers.Main) {
                loadFavorites(memoList)
            }
        }
    }

    private fun loadFavorites(memoList: List<Memo>) {
        favoriteList.clear()

        lifecycleScope.launch(Dispatchers.IO) {
            for (memo in memoList) {
                val sharedPrefs = getSharedPreferences("memo_preferences", Context.MODE_PRIVATE)
                val isFavorite = sharedPrefs.getBoolean("favorite_${memo.id}", false)
                if (isFavorite) {
                    favoriteList.add(memo)
                }
            }

            withContext(Dispatchers.Main) {
                favoriteAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun toggleFavorite(memo: Memo) {
        val isFavorite = sharedPreferences.getBoolean("favorite_${memo.id}", false)
        val editor = sharedPreferences.edit()
        editor.putBoolean("favorite_${memo.id}", !isFavorite)
        editor.apply()
        loadFavorites(memoList)
    }

    private fun toggleSwitch(memo: Memo) {
        val isFinished = memo.isFinished
        memo.isFinished = !isFinished

        lifecycleScope.launch(Dispatchers.IO) {
            memoDatabase.memoDao().updateMemo(memo)
        }
    }
}
