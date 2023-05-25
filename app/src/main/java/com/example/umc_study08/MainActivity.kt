package com.example.umc_study08

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_study08.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var memoAdapter: MemoAdapter
    private lateinit var memoList: MutableList<Memo>
    private lateinit var memoDatabase: MemoDatabase

    private val addMemoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val memoText = data?.getStringExtra("memo")
            if (memoText != null) {
                val newMemo = Memo(content = memoText)
                lifecycleScope.launch(Dispatchers.IO) {
                    memoDatabase.memoDao().insertMemo(newMemo)
                    loadMemos()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        memoDatabase = MemoDatabase.getInstance(this)
        memoList = mutableListOf()
        memoAdapter = MemoAdapter(memoList, onItemClick =  { position ->
            deleteMemo(position)
        }, onFavoriteClick = { position ->
            toggleFavorite(position)
        }, onSwitchClick = { position ->
            toggleFinished(position)
        })

        binding.recyclerView.adapter = memoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addMemoButton.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            addMemoLauncher.launch(intent)
        }

        binding.goFavorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        loadMemos()
    }

    private fun deleteMemo(position: Int) {
        val memo = memoList[position]
        lifecycleScope.launch(Dispatchers.IO) {
            memoDatabase.memoDao().deleteMemo(memo)
            loadMemos()
        }
    }

    private fun toggleFavorite(position: Int) {
        val memo = memoList[position]
        val isFavorite = !memo.isFavorite
        lifecycleScope.launch(Dispatchers.IO) {
            memo.isFavorite = isFavorite
            memoDatabase.memoDao().updateMemo(memo)
            withContext(Dispatchers.Main) {
                memoAdapter.notifyDataSetChanged()
                if (isFavorite) {
                    val sharedPrefs = getSharedPreferences("memo_preferences", Context.MODE_PRIVATE)
                    val editor = sharedPrefs.edit()
                    editor.putBoolean("favorite_${memo.id}", true)
                    editor.apply()
                }
            }
        }
    }

    private fun toggleFinished(position: Int) {
        val memo = memoList[position]
        val isFinished = !memo.isFinished
        lifecycleScope.launch(Dispatchers.IO) {
            memo.isFinished = isFinished
            memoDatabase.memoDao().updateMemo(memo)
            withContext(Dispatchers.Main) {
                memoAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadMemos() {
        lifecycleScope.launch(Dispatchers.IO) {
            val memos = memoDatabase.memoDao().getAllMemos()
            withContext(Dispatchers.Main) {
                memoList.clear()
                memoList.addAll(memos)
                memoAdapter.notifyDataSetChanged()
            }
        }
    }
}
