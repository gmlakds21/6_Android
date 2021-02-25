package seunghee.helloandroid

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class _02_16_01_MainActivity : AppCompatActivity() {

    // 변수 선언시 초기화를 바로 못하는 경우 late init 키워드 사용
    lateinit var btnSayHello : Button

    lateinit var btnGoogle : Button
    // lateinit var btn911 : Button
    // lateinit var btnGallery : Button
    // lateinit var btnFinish : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // 안드로이드 앱의 실행 진입점
        // 즉, 현재 액티비티가 생성되면 뭔가를 실행하라는 의미



       super.onCreate(savedInstanceState)
        // 화면에 무언가를 출력함, res - layout - activity_main.xml
        setContentView(R.layout.activity__02_16_01__main)

        btnSayHello = findViewById(R.id.btnGoogle)
        // activity_main.xml 에서 id로 위젯을 찾아서 변수에 할당
        btnSayHello.setOnClickListener() {
            // makeText(대상, 메세지, 지연시간).show() 잠시동안 메세지를 출력함
            Toast.makeText(applicationContext, "Hello Android!!", Toast.LENGTH_SHORT).show()
        }

        btnGoogle = findViewById(R.id.btnGoogle)
        // btn911 = findViewById(R.id.btn911)
        // btnGallery = findViewById(R.id.btnGallery)
        // btnFinish = findViewById(R.id.btnFinish)

        btnGoogle.setOnClickListener() {
            var gIntent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://google.co.kr"))
            startActivity(gIntent)
        }
    }

    // 특정 위젯을 findViewById 함수로 잡아서 이벤트를 추가할 때마다 코드가 지저분 해짐
    // 따라서, 이벤트 처리를 위한 독립적인 함수를 만들어 처리하는 것이 좋음
    fun call911(v: View) {  // xml 에서 onClick 걸어야 함
        var mIntent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel://911"));
        startActivity(mIntent)
    }

    fun openGallery(v:View) {
        var mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
        // intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content//media/internal/images/media"));
        startActivity(mIntent)
    }

    fun appFinish(v:View) {
        finish();
    }

}

/*
 * # AndroidManifest.xml
 * 안드로이드 앱의 기본 정보를 저장한 파일
 * icon     : 안드로이드 앱 아이콘 지정
 * label    : 안드로이드 앱 제목 지정
 * activity : 안드로이드 앱 메인 액티비티 지정
 * android.intent.action.MAIN       : 메인 액티비티 지정 여부
 * android.intent.category.LAUNCHER : 앱 실행시 맨 처음 화면에 표시할 시작 액티비티 지정
 *
 *
 * # MainActivity.kt
 * 화면에 배치된 위젯에 이벤트를 추가해서 인트랙티브한 작업을 하거나
 * 기타 백앤드 작업을 위한 코드 파일을 의미
 *
 * # activity_main.xml
 * 화면에 보여질 위젯들을 배치하는 레이아웃 파일을 의미
 *
 * # res
 * 앱 개발에 필요한 다양한 리소스들을 저장하는 디렉토리
 * drawable : 앱을 구성하는 각종 시각적 리소스들을 저장
 * layout : 앱 배치에 사용할 레이아웃 파일을 저장
 * values : 앱을 구성하는 각종 문자열을 저장한 파일
 *
 * # findViewById
 * 레이아웃에 선언된 구성요소들을 액티비티에서 접근해서
 * 조작하려면 해당 요소를 id로 지정해서 변수에 할당해야 함
 * findViewById 함수를 이용하면 해당 위젯을 선택한 다음
 * 변수에 할당 할 수 있음
 * 단, 위젯을 지정할 때는 'R.id.위젯아이디' 형태로 사용해야 함
 *
 * # 액티비티 activity
 * 앱을 실행했을 때 보여지는 화면을 구성할 수 있도록 해주는 안드로이드 요소
 * 보통 앱의 종류에 따라 하나 이상의 액티비티로 구성될 수 있음
 * 웹으로 비유하자면 하나의 웹 페이지 개념으로 이해하면 됨
 */
