package kr.co.lion.project2_memoapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.project2_memoapplication.databinding.ActivityInputBinding

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
            }
        }
    }
}