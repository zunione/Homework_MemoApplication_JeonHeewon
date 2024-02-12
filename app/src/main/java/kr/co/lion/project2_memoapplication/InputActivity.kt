package kr.co.lion.project2_memoapplication

import android.content.DialogInterface
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
                            // 입력 유효성 검사
                            checkValidity()

                            val title = editTextInputTitle.text.toString()
                            val text = editTextInputText.text.toString()
                            val date = LocalDate.now().toString()

                            // 메모를 리스트에 담음
                            val memo = Memo(title, text, date)
                            Util.memoList.add(memo)

                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                    true
                }
            }
        }
    }

    fun checkValidity() {
        activityInputBinding.apply {
            // 메모 제목
            val title = editTextInputTitle.text.toString()
            if(title.trim().isEmpty()){
                Util.showInfoDialog(this@InputActivity, "입력 오류", "제목을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(editTextInputTitle, this@InputActivity)
                }
                return
            }

            // 메모 내용
            val text = editTextInputText.text.toString()
            if(text.trim().isEmpty()){
                Util.showInfoDialog(this@InputActivity, "입력 오류", "내용을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(editTextInputText, this@InputActivity)
                }
                return
            }
        }
    }
}