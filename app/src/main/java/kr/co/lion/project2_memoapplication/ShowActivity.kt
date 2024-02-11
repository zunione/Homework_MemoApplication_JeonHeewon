package kr.co.lion.project2_memoapplication

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.project2_memoapplication.databinding.ActivityShowBinding
import java.time.LocalDate

class ShowActivity : AppCompatActivity() {
    lateinit var activityShowBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        initTextField()
        setToolbar()
    }

    fun initTextField() {
        // 정보 표시
        activityShowBinding.apply {
            // Intent 에 저장된 객체를 가져온다.
            val memo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("memo", Memo::class.java)!!
            } else {
                intent.getParcelableExtra<Memo>("obj")!!
            }

            // 객체에서 정보를 추출한다.
            editTextShowTitle.setText(memo.title.toString())
            editTextShowDate.setText(memo.date.toString())
            editTextShowText.setText(memo.text.toString())
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
                        R.id.menu_item_show_edit -> {

                        }
                        R.id.menu_item_show_delete -> {

                        }
                    }
                    true
                }
            }
        }
    }
}