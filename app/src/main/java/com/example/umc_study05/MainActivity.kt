package com.example.umc_study05

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_study05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val listData: ArrayList<Member> = arrayListOf()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val myAdapter = MyAdapter(listData, onClickDeleteBtn = { deleteTask(it)})

        /**
         * Activity 간 데이터 주고 받는 registerForActivityResult
         **/
        getResultText =
            registerForActivityResult (ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val mString = result.data?.getStringExtra("Back")

                    listData.apply { add(Member("$mString")) }

                    /**
                     * RecyclerView 업데이트 방법 중 1
                     * - notifyItemRangeInserted : 연속된 여러 개의 아이템 내용 변경 시
                     * - notifyDataSetChanged :리스트의 크기와 아이템 둘 다 변경 시
                     * */
                    myAdapter.notifyItemRangeInserted(listData.size,1)
                }
            }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, MemoActivity::class.java)
            getResultText.launch(intent)
        }
        binding.RV.adapter = myAdapter
        binding.RV.layoutManager = LinearLayoutManager(this)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteTask(data: Member) {
        listData.remove(data)
        binding.RV.adapter?.notifyDataSetChanged()
    }
}


