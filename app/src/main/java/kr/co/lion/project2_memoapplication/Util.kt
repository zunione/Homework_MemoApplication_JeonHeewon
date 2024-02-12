package kr.co.lion.project2_memoapplication

import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.concurrent.thread

class Util {
    companion object{
        // 동물 객체들을 담을 리스트
        val memoList = mutableListOf<Memo>()

        // 포커스를 주고 키보드를 올려주는 메서드
        fun showSoftInput(view: View, context: Context){
            // 포커스를 준다.
            view.requestFocus()

            thread {
                SystemClock.sleep(1000)
                val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, 0)
            }
        }

        // 키보드를 내려준다.
        fun hideSoftInput(activity: AppCompatActivity){
            // 현재 포커스를 가지고 있는 View 있다면 키보드를 내린다.
            if(activity.window.currentFocus != null){
                val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0);
            }
        }

        // 안내를 위한 다이얼로그를 보여준다.
        fun showInfoDialog(context: Context, title:String, message:String, listener: (DialogInterface, Int) -> Unit){
            val dialogBuilder = MaterialAlertDialogBuilder(context)
            dialogBuilder.setTitle(title)
            dialogBuilder.setMessage(message)
            dialogBuilder.setPositiveButton("확인", listener)
            dialogBuilder.show()
        }
    }
}