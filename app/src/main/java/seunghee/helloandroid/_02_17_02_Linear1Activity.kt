package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class _02_17_02_Linear1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_17_02__linear1)
    }
}
/* #레이아웃
 * 위젯들을 적절하게 배치하는 것을 의미
 * ViewGroup 클래스를 상속받아 만들어진 클래스를 이용
 *
 * # 선형 linear 레이아웃
 * 좌측 상단을 기준으로 오른쪽 방향으로 또는 아래 방향으로
 * 차곡차곡 위젯들을 배치하는 방식
 * wrap_content : 남은 공간을 사이좋게 나눠 갖음
 * match_parent : 부모 레이어의 공간을 꽉 채워서 사용
 * gravity : 위젯의 내용을 정렬
 *
 * # 상대 relative 레이아웃
 *
 *
 * # 제약 constraints 레이아웃
 * 구글에서 새롭게 추천하고 있는 레이아웃
 * 기존의 레이아웃을 이용해서 복잡한 화면 구성시
 * 발생하는 성능저하와 디자인의 어려움을
 * 개선하기 위한 목적으로 개발
 * API 레벨 9(진저브레드) 부터 지원하기 시작함
 */