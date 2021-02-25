package seunghee.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class _02_22_01_HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_01__hello)
    }

    fun goNext(v: View) {

        // 자바 버전
        // var intent : Intent = Intent(this, _02_22_01_Sub1Activity.class)

        // 코틀린 버전
        // var intent : Intent = Intent(this, _02_22_01_Sub1Activity::class.java)

        var sub1 : Intent = Intent(this, _02_22_01_Sub1Activity::class.java)
        startActivity(sub1)
    }
}

/*
 * 액티비티 activity
 * 안드로이드 구성요소 중 화면을 담당하는 파트
 * 여러 액티비티들을 만들어 두고 목적에 따라
 * 유기적으로 작동하도록 만들려면 인텐트가 절대적으로 필요
 * 즉, 한 액티비티에서 다른 액티비티들을 실행하거나
 * 실행한 액티비티에 데이터를 전달하는 등의 작업을 하려면
 * 인텐트를 사용해야 함
 *
 * 인텐트 intent
 * 다른 액티비티를 실행하거나 데이터를 전달할 수 있는
 * 안드로이드 구성요소 중 하나
 *
 * 리스트 뷰  List View
 * 사용자가 정의한 데이터 목록을 항목 단위로 구성하여 화면에 출력하는 구성요소
 * 목록의 항목들은 세로방향으로 나열되며 항목 수가 리스트 뷰의 크기보다 많으면
 * 스크롤 기능을 사용해서 나머지 항목들은 가려둘 수 있음
 * 안드로이드 구성 요소중 사용빈드가 가장 많은 위젯
 *
 * 한편, 리스트뷰에 데이터를 추가해서 화면에 표시하려면 반드시 어댑터를 사용해야만 함
 * 즉, 이것을 통해 model 과 view 를 연결해서 사용자가 원하는 UI 형태로
 * 데이터를 표현 할 수 있기 때문임
 *
 * 하지만, 스크롤 할 때 마다 화면에 보여줄 항목을 다시 그리기 때문에
 * 성능상 오버헤드가 발생 -> 현재는 RecyclerView 를 사용할 것을 추천
 *
 * 직렬화와 역직렬화
 * serialization, marshaling
 * 생성된 데이터 객체 유형을 그대로 유지하면서 파일에 저장하는 기법
 * 즉, 데이터를 텍스트 형태로 풀어서 파일에 저장하는 것이 아니고
 * 메모리상에 만들어진 형태 그대로 파일에 저장하는 것을 의미
 *
 * 반면, 파일에 저장된 객체형태의 데이터를 메모리에 그대로 옮기는 과정을 역직렬화라고 함
 *
 * 직렬화/역직렬화를 하려면 클래스 정의시 Serializable 이라는 인터페이스를 구현해야 함
 *
 * 안드로이드의 직렬화와 역직렬화
 * 자바에서는 Serializable 을 구현해서 만든 클래스로 직렬화를 할 수 있음
 * 단, 안드로이드에서는 자바의 Serializable 보다 복잡하지만
 * Parcelable 을 구현해서 만든 클래스로 직렬화하는 것을 추천함
 *
 * Parcelable 클래스 구현 방법
 * 1. Parcelable 인터페이스를 구현
 * 2. writeToParcel, describeContents 매서드, CREATOR 싱글콘 클래스등을 재정의해 줌
 * 3. 안드로이드 스튜디오에서는 자동으로 생성해 줌 : Add Parcelable Implementation 메뉴 사용
 *
 * Parcelable 객체 보내고 받기
 * 하나의 액티비티에서 다른 액티비티로 일반적인 데이터(문자, 숫자)를 전달해야 하는 경우
 * 보내는 측에서는 putExtra 매서드를 이용해서 보낼 데이터를 셋팅하고
 * 받는 측에서는 getExtra 매서드를 이용해서 받은 데이터를 가져올 수 있음
 *
 * 한편, 객체형 데이터를 보내고 받는 경우
 * 보내는 측에서는 putExtra 매서드를 이용해서 보낼 데이터를 셋팅하고
 * 받는 측에서는 getParcelableExtra 매서드를 이용해서 받은 데이터를 가져올 수 있음
 *
 *
 * RecyclerView 위젯
 * 안드로이드 5.5 (롤리팝)에서 처음 소개되었음
 * ListView 위젯보다 유연하고 성능이 향상된 위젯으로 구글에서 사용 추천 위젯
 * 즉, 화면의 뷰가 바뀌면 전체 데이터를 다시 불러와서 화면에 출력해야 하는 비효율적인 면이 있었음
 *
 * ViewHolder 패턴을 이용해서 어댑터를 정의할 것이 강제 (리스트뷰에서는 선택사항)
 * LayoutManager 를 이요해서 다양한 모양의 뷰를 커스텀화 할 수 있음
 * ItemDecoration 클래스를 이용해서 item 간의 간격을 조절할 수 있음
 * ItemAnimator 클래스를 이용해서 item 삽입, 제거, 이동시 애니메이션 효과 적용 가능
 * 리스트 뷰에 비해 이벤트 처리 강화
 *
 * RecyclerView 의 LayoutManager
 * RecyclerView 위젯에 표시된 항목을 어떤 형태로 배치할 것인지 결정하는 데 사용하는 객체
 * LinearLayoutManager          : 수평/수직 형태로 항목을 배치
 * GridLayoutManeger            : 격자형으로 항목읇 배치
 * StaggeredGridLayoutManager   : itemView 마다 크기가 다른 목록으로 항목 배치
 *
 * RecyclerView 의 Click Animation 추가하기
 * sj_listView.xml 의 취상위 root 요소에 다음 내용 추가
 * android : background = "?attr/selectableItemBackground"
 */