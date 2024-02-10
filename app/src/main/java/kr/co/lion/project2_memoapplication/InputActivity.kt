package kr.co.lion.project2_memoapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.project2_memoapplication.databinding.ActivityInputBinding
import java.time.LocalDate

class InputActivity : AppCompatActivity() {
    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setToolbar()
    }

    fun setToolbar() {
        activityInputBinding.apply {
            toolbarInput.apply {
                title = "메모 작성"
                setTitleTextColor(Color.WHITE)

                // 뒤로 가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // menu_input.xml 과 연결
                inflateMenu(R.menu.menu_input)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_item_input_done -> {
                            // 입력 유효성 검사 해야 함
                            val title = editTextInputTitle.text.toString()
                            val text = editTextInputText.text.toString()
                            val date = LocalDate.now().toString()

                            // 메모 내용을 객체에 담음
                            val memo = Memo(title, text, date)

                            // 데이터를 담을 Intent 생성
                            val inputIntent = Intent()

                            Log.d("test1234", date)

                            // 객체를 Intent 에 저장
                            inputIntent.putExtra("memo", memo)

                            setResult(RESULT_OK, inputIntent)
                            finish()
                        }
                    }
                    true
                }
            }
        }
    }
}