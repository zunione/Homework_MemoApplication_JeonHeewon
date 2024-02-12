package kr.co.lion.project2_memoapplication

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.project2_memoapplication.databinding.ActivityShowBinding
import java.time.LocalDate

class ShowActivity : AppCompatActivity() {
    lateinit var activityShowBinding: ActivityShowBinding

    // Activity 런처
    lateinit var editActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        initTextField()
        setLauncher()
        setToolbar()
    }

    fun initTextField() {
        // Intent 에 저장된 객체를 가져온다.
        val idx = intent.getIntExtra("index", 0)
        val memo = Util.memoList[idx]

        // 정보 표시
        activityShowBinding.apply {
            // 객체에서 정보를 추출한다.
            editTextShowTitle.setText(memo.title.toString())
            editTextShowDate.setText(memo.date.toString())
            editTextShowText.setText(memo.text.toString())
        }
    }

    fun setLauncher() {
        // EditActivity 런처
        val inputContract = ActivityResultContracts.StartActivityForResult()
        editActivityLauncher = registerForActivityResult(inputContract){

        }
    }

    fun setToolbar() {
        activityShowBinding.apply {
            toolbarShow.apply {
                title = "메모 보기"
                setTitleTextColor(Color.WHITE)

                // 메뉴 파일과 연결
                inflateMenu(R.menu.menu_show)

                // 뒤로 가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        // 메모 수정
                        R.id.menu_item_show_edit -> {
                            val idx = intent.getIntExtra("index",0)

                            // Intent 에 인덱스 값 담음
                            val editIntent = Intent(this@ShowActivity, EditActivity::class.java)
                            editIntent.putExtra("index", idx)

                            // EditActivity 실행
                            editActivityLauncher.launch(editIntent)
                        }
                        // 메모 삭제
                        R.id.menu_item_show_delete -> {
                            val idx = intent.getIntExtra("index",0)
                            Util.memoList.removeAt(idx)

                            finish()
                        }
                    }
                    true
                }
            }
        }
    }
}