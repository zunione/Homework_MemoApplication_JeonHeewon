package kr.co.lion.project2_memoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.project2_memoapplication.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    lateinit var activityShowBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)
    }
}