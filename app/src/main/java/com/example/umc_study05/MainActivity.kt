package com.example.umc_study05

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_study05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var memoAdapter: MemoAdapter
    private lateinit var memoList: MutableList<String>
    private lateinit var addMemoButton: Button

    private val addMemoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val memoText = data?.getStringExtra("memo")
            if (memoText != null) {
                memoList.add(memoText)
                memoAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoList = mutableListOf()
        memoAdapter = MemoAdapter(memoList) { position ->
            memoList.removeAt(position)
            memoAdapter.notifyDataSetChanged()
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = memoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addMemoButton = findViewById(R.id.addMemoButton)
        addMemoButton.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            addMemoLauncher.launch(intent)
        }
    }
}
