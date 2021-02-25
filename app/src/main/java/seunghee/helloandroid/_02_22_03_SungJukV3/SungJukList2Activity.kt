package seunghee.helloandroid._02_22_03_SungJukV3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import seunghee.helloandroid.R

class SungJukList2Activity : AppCompatActivity() {

    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_23_03__sung_juk_list2)

        rv = findViewById(R.id.recyclerView2)

        var list:MutableList<SungJuk_VO> = SungJuk_Service.readSungJuk(this)

        // var rvAdapter = RVAdapter(this, list) 이벤트 없음
        var rvAdapter = RVAdapter(this, list) { student -> goView(student, this) }

        rv.layoutManager = LinearLayoutManager(this)
        // rv.layoutManager = GridLayoutManager(this, 2)    한줄에 2개씩
        rv.setHasFixedSize(true)
        rv.adapter = rvAdapter

    }


    // 이벤트 없는 버전으로 할 때
    // class RVAdapter (var context: Context, var list: MutableList<SungJuk_VO>) : RecyclerView.Adapter<RVAdapter.Holder>() {
    class RVAdapter (var context: Context, var list: MutableList<SungJuk_VO>, var itemClick: (SungJuk_VO) -> Unit ) : RecyclerView.Adapter<RVAdapter.Holder>() {

        // onBindViewHolder 함수 호출시 실제 데이터항목과
        // View 에 출력할 위젯을 알아내서 바인딩하는 bind 함수 정의
        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv = arrayOf(R.id.tvbid, R.id.tvbkname, R.id.tvauthor, R.id.tvmat)


            var tvname = itemView?.findViewById<TextView>(R.id.tvbid)
            var tvkor = itemView?.findViewById<TextView>(R.id.tvbkname)
            var tveng = itemView?.findViewById<TextView>(R.id.tvauthor)
            var tvmat = itemView?.findViewById<TextView>(R.id.tvmat)

            /* 이벤트 없는 버전으로 할 때
            fun bind(sj:SungJuk_VO, context: Context) {
                tvname.text = sj.name
                tvkor.text = sj.kor
                tveng.text = sj.eng
                tvmat.text = sj.mat
            }
             */

            fun bind(student:SungJuk_VO, context: Context) {
                tvname.text = student.name
                tvkor.text = student.kor
                tveng.text = student.eng
                tvmat.text = student.mat

                itemView.setOnClickListener {itemClick(student)} // 항목에 이벤트 추가
            }

        }

        // ViewHoler 객체 생성시 해야할 작업 정의
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            var view = LayoutInflater.from(context).inflate(R.layout.activity__02_23_01__sj_listview, parent, false)
            return Holder(view)
        }

        // 데이터를 ViewHolder 를 통해서 View 객체에 할당함 (바인딩)
        // 즉, onCreateViewHolder 에서 생성한 뷰객체와 데이터 객체를
        // 위에서 내부클래스로 정의한 Holder 클래스의 bind 함수를 통해 바인딩함
        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(list[position], context)
        }

        // View 에 출력할 전체 항목 수 반환
        override fun getItemCount(): Int = list.size


    }

    fun goView (student: SungJuk_VO, context: Context) {

        var view = Intent(context, SungJukViewActivity::class.java)

        view.putExtra("student", student)
        startActivity(view)
    }

}
