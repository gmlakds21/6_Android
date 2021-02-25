package seunghee.helloandroid._02_22_03_SungJukV3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import seunghee.helloandroid.R

class ListSungJukActivity : AppCompatActivity() {

    lateinit var listview1 : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_03__sung_juk_list)
        title = "성적 리스트"

        listview1 = findViewById(R.id.listview1)

        advListView(this)

    }

    // 리스트뷰 기본 사용법
    fun basicListView(v: View) {

        var list:MutableList<String> = ArrayList()
        list.add("디아블로3")
        list.add("디아블로4")
        list.add("디아블로5")
        list.add("디아블로6")
        list.add("디아블로7")

        // 문자열 배열에 정의되 ㄴ데이터를 simple_list_item_1 형태로 뷰를 이용해서 리스트 뷰에 출력하는 어댑터를 선언
        var lvadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        /*
        simple_list_item_1 : 안드로이드가 미리 정의해둔 뷰 (한줄에 하나의 항목만 출력)
        simple_list_item_2 : 안드로이드가 미리 정의해둔 뷰 (한줄에 두개의 항목만 출력)
        simple_list_item_choice : 안드로이드가 미리 정의해둔 뷰 (simple_1 + 라디오 버튼 포함)
        simple_list_item_checked : 안드로이드가 미리 정의해둔 뷰 (simple_1 + 체크박스 버튼 포함)
         */
        // 앞에서 선언한 어댑터를 리스트뷰에 초기화함
        listview1.setAdapter(lvadapter)
        // listview1.adapter = lvadapter
        // 의미는 같음

    }

    // 사용자 정의 리스트 뷰
    // 기본뷰가 아닌 임의의 뷰는 새로운 레이아웃파일로 미리 정의해두어야 함
    fun advListView(context: Context) {

        /*
        var students:MutableList <SungJuk_VO> = ArrayList()
        students.add(SungJuk_VO("지현", "99", "90", "85"))
        students.add(SungJuk_VO("혜교", "76", "56", "89"))
        students.add(SungJuk_VO("수지", "43", "65", "78"))
        */

        // 앱내 생성된 sungjuk.txt 에서 성적데이터를 읽어옴
        var students:MutableList<SungJuk_VO> = SungJuk_Service.readSungJuk(context)

        // ListViewAdapter 에서 나온걸 listview1 에 넣음
        var adapter = ListViewAdapter(context, students)
        listview1.adapter = adapter

        // List 에서 클릭시 정보를 전달하기 위해
        // 리스트뷰 항목 클릭시 클릭한 항목에 대한 정보를 출력하는 이벤트 추가
        // parent : AdapterView<*> 객체, 리스트뷰에 적용된 어댑터 객체를 의미
        // view : View 객체, 어댑터 객체에 정의된 뷰 객체를 의미
        // i : 힝목 클릭시 항목 위치값을 의미
        // id : 항목 클릭시 항목 고유값을 의미
        listview1.setOnItemClickListener { parent, view, i, id ->

            // 항목 클릭시 위치값을 이용해서 해당 성적데이터를 가져옴옴
            var student = parent.getItemAtPosition(i) as SungJuk_VO

            // 데이터를 확인해볼 수 있음
            // Toast.makeText(context, "${item.name} ${i}", Toast.LENGTH_SHORT).show()

            var view : Intent = Intent(context, SungJukViewActivity::class.java)
            // view.putExtra("student", student) 객체 타입으로 자료를 넘기면 문제가 생김
            // 클래스는 반드시 parcelable 을 구현해야 함
            view.putExtra("student", student)
            startActivity(view)
        }
    }



    // recyclerView 기본
    fun basicRecyclerView (v: View) {

    }

    // 사용자 정의 recyclerView
    fun advRecyclerView (v: View) {

    }


}

// 추가하라는대로 다 하고, Override 내용은 직접 입력함
class ListViewAdapter(var context: Context, var students: MutableList<SungJuk_VO>) : BaseAdapter() {

    // 데이터 항목수 출력
    override fun getCount(): Int {
        return students.size
    }

    // 선택한 항목 출력하기
    override fun getItem(pos: Int): SungJuk_VO {
        return students[pos]
    }

    //
    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    // 데이터의 각 항목을 뷰의 어느 위치에 출력할 것인지 정의
    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View? {
        var convertView = view

        // LayoutInflater : 레이아웃 파일을 뷰 객체로 객체화 하는 함수
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.activity__02_23_01__sj_listview, null)

        // 각 데이터의 항목을 각각 추출해서 뷰의 지정한 객체에 적절히 항당함
        var student: SungJuk_VO = students[pos]
        convertView?.findViewById<TextView>(R.id.tvbid)?.text = student.name
        convertView?.findViewById<TextView>(R.id.tvbkname)?.text = student.kor
        convertView?.findViewById<TextView>(R.id.tvauthor)?.text = student.eng
        convertView?.findViewById<TextView>(R.id.tvmat)?.text = student.mat

        return convertView;
    }

}
