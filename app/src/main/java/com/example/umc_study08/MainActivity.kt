package com.example.umc_study08

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_study08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var memoAdapter: MemoAdapter
    private lateinit var memoList: MutableList<String>

    /**
     * ActivityResultLauncher : Activity, Fragment에서 다른 Activity 실행 후 그 결과 처리
     * - registerForActivityResult, launch 메서드 제공
     * registerForActivityResult : 실행 결과를 처리할 콜백 함수를 등록
     * launch : 다른 Activity 실행
     * */
    private val addMemoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        //Activity 결과 정보
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
        setContentView(binding.root)


        //List : 읽기 전용
        //mutableListOf : 읽기 쓰기 가능
        memoList = mutableListOf()
        memoAdapter = MemoAdapter(memoList) { position ->
            memoList.removeAt(position) // 메모 삭제
            memoAdapter.notifyDataSetChanged() //Adapter에 데이터 변경을 알림
        }

        binding.recyclerView.adapter = memoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //RecyclerView에 대한 레이아웃 매니저
        /**
         * Activity, Fragment 는 Context 상속받은 class. -> this 사용해 Activity, Fragment Instance 참조 가능
         * */

        binding.addMemoButton.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            addMemoLauncher.launch(intent)
        }
    }
}