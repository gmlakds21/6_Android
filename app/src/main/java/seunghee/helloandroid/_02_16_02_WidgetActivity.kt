package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/* 02_16_02
 * # 위젯 widget
 * 안드로이드 화면(액티비티)을 구성하는데 사용하는 요소
 */
class _02_16_02_WidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_16_02__widget)
    }
}

/*
 * # 뷰
 * View 클래스를 상속해서 만든 위젯들
 *
 * # 뷰그룹
 * ViewGroup 클래스를 상속해서 만든 위젯들
 * 뷰들을 적절한 위치에 배치하거나 여러 뷰들을 하나로 묶는데 사용 - 배치 관리자
 * 배치관리자 : 적절한 위치에 배치
 * 컨테이너 : 여러 뷰들을 하나로 묶음
 *
 * # 텍스트 뷰
 * 사용자가 수정할 수 없는 텍스트를 표시하는 위젯
 * text : 글 내용
 * textSize : 글자의 크기
 * textColor : 글자의 색
 * Lines : 글자의 줄 수
 *
 * # 에디트 텍스트
 * 사용자에게 문자열이나 숫자를 입력받을 수 있는 위젯
 * inputType : 입력받는 형태
 * layout_width : 넓이 지정
 * hint : 기본 입력 값
 *
 * # 버튼
 * 사용자가 손으로 클릭할 수 있는 위젯
 * 버튼을 통해 사용자가 어떤 결정을 선택하게 하거나
 * 다음 화면으로 전환하는등의 인트랙티브한 응답을 할 수 있음
 * backgroundTint : 배경색 지정
 *
 * # 체크박스
 * 사용자가 여러 항목들 중 하나 이상의 항목을 선택할 수 있게 해주는 위젯
 * checked : 항목의 선택여부를 표시
 *
 * # 라디오버튼
 * 사용자가 여러 항목들 중  하나의 항목만을 선택할 수 있게 해주는 위젯
 * checked
 *
 * # 토글버튼
 * 사용자가 선택을 하면 커짐이 표시되고 다시 선택하면 꺼짐을 표시하는 위젯
 * textOn : 토글 버튼이 꺼져있을 때 표시할 문자열
 * textOff : 토글 버튼이 꺼져있을 때 표시할 문자열
 *
 * # 이미지 뷰
 * 이미지를 화면에 보여주기 위한 위젯
 * src : 이미지 경로 지정
 * scaleType : 이미지를 어떻게 보여줄지 지정
 *
 * # 색상명 사용하기
 * 위젯에 색상을 지정하려면 보통 16진수 코드를 이용함
 * 단, 색상이름으로 색상을 지정하려면
 * values - colors.xml 에 색상명과 16진수 코드를 선언하고
 * @color/색상명 형태로 사용하면 됨
 * 색상은 AABBCCDD 형태로 정의하는데
 * AA - 투명도, BB - 빨강, CC - 녹색, DD - 파랑
 *
 */