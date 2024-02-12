package kr.co.lion.project2_memoapplication

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.project2_memoapplication.databinding.ActivityEditBinding
import java.time.LocalDate

class EditActivity : AppCompatActivity() {
    lateinit var activityEditBinding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)

        initTextField()
        setToolbar()
    }

    fun initTextField() {
        // Intent 에 저장된 객체를 가져온다.
        val idx = intent.getIntExtra("index", 0)
        val memo = Util.memoList[idx]

        // 정보 표시
        activityEditBinding.apply {
            // 객체에서 정보를 추출한다.
            editTextEditTitle.setText(memo.title.toString())
            editTextEditText.setText(memo.text.toString())
        }
    }

    fun setToolbar() {
        activityEditBinding.apply {
            toolbarEdit.apply {
                title = "메모 수정"
                setTitleTextColor(Color.WHITE)

                // 뒤로 가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // menu 레이아웃 파일 불러오기
                inflateMenu(R.menu.menu_edit)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_item_edit_done -> {
                            // 입력 유효성 검사
                            checkValidity()

                            val title = editTextEditTitle.text.toString()
                            val text = editTextEditText.text.toString()

                            // 메모를 리스트에서 찾아서 수정
                            val idx = intent.getIntExtra("index", 0)
                            Util.memoList[idx].title = title
                            Util.memoList[idx].text = text

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
        activityEditBinding.apply {
            // 메모 제목
            val title = editTextEditTitle.text.toString()
            if(title.trim().isEmpty()){
                Util.showInfoDialog(this@EditActivity, "입력 오류", "제목을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(editTextEditTitle, this@EditActivity)
                }
                return
            }

            // 메모 내용
            val text = editTextEditText.text.toString()
            if(text.trim().isEmpty()){
                Util.showInfoDialog(this@EditActivity, "입력 오류", "내용을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(editTextEditText, this@EditActivity)
                }
                return
            }
        }
    }
}