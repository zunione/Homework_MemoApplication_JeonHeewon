package kr.co.lion.project2_memoapplication

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.project2_memoapplication.databinding.ActivityMainBinding
import kr.co.lion.project2_memoapplication.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    // Activity 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var showActivityLauncher: ActivityResultLauncher<Intent>

    // 메모 리스트
    val memoList = mutableListOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setLauncher()
        setToolbar()
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        activityMainBinding.apply {
            // 리사이클러뷰 갱신
            recyclerViewMain.adapter?.notifyDataSetChanged()
        }
    }

    fun setLauncher() {
        // InputActivity 런처
        val inputContract = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(inputContract){
            // 메모가 정상적으로 입력되었을 경우
            if (it.resultCode == RESULT_OK) {
                // Intent 에서 객체 추출
                val memo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    it.data!!.getParcelableExtra("memo", Memo::class.java)
                } else {
                    it.data!!.getParcelableExtra<Memo>("memo")
                }

                memoList.add(memo!!)
            }
        }

        // ShowActivity 런처
        val showContract = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(inputContract) {
            // 여기까지함
            
        }
    }

    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                title = "메모 관리"
                setTitleTextColor(Color.WHITE)

                // menu_main 연결
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_item_main_add -> {
                            // InputActivity 실행
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    fun setRecyclerView() {
        activityMainBinding.apply {
            recyclerViewMain.apply {
                adapter = RecyclerViewMainAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)

                // 구분선
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    inner class RecyclerViewMainAdapter: RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {
        inner class ViewHolderMain (rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                this.rowMainBinding.root.setOnClickListener {
                    // ShowActivity 실행
                    val showIntent = Intent(this@MainActivity, ShowActivity::class.java)

                    // Intent 에 position 메모 담아준다
                    showIntent.putExtra("memo", memoList[adapterPosition])

                    showActivityLauncher.launch(showIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewRowMainTitle.text = "${memoList[position].title}"
            holder.rowMainBinding.textViewRowMainDate.text = "${memoList[position].date}"
        }
    }
}
















