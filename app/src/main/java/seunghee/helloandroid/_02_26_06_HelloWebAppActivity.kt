package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class _02_26_06_HelloWebAppActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_26_06__hello_web_app)
    }



}

/* 모바일 장치를 위한 앱
 *
 * 1. 반응형 앱
 * 웹 표준 기술로 모바일 브라우져에서 볼 수 있도록 구축한 웹 사이트
 * 핸드폰, 태블릿등에서도 웹 사이트를 잘 볼 수 있도록 가시영역을 적절히 조절함
 * 즉, 접속한 장치별에 따라 웹 사이트 영역이 자동으로 바뀜
 * 단점으로 핸드폰에 장착된 각종 하드웨어들을 다룰 수 없음
 *
 * 2. 네이티브 앱
 * 폰에 설치해서 사용하는 앱
 * 기능과 성능이 다른 앱에 비해 압도적임
 * 즉, 카메라, 자이로 센서, 그래픽 기능 등 핸드폰에 내장된 각종 하드웨어를
 * 최대한 활용할 수 있음
 * 앱을 구축하고 유지하는데 비교적 높은 수준의 개발기술이 필요
 * 또한, 플랫폼별로 따로 개발해서 배포해야하는 점도 단점으로 작용함
 * 대부분의 사용자가 앱이라고 말한다면 일반적으로 네이티브 앱을 의미함
 *
 * 3. 하이브리드 앱
 * 네이티브앱 + 웹앱 형태를 띄는 앱 (대부분 웹을 기반으로 함)
 * 즉, 네이티브앱의 웹뷰라는 컴퍼넌트를 통해 모바일 웹을 띄우는 형태로 구동되는 앱
 * 웹 개발기술을 사용해서 앱을 만들 수 있음
 * 한번의 개발로 다수의 플랫폼에 대응 가능
 * 네이티브앱의 장점은 살릴 수 없음
 * 개발한 앱은 앱스토어에 배포가능
 */

/* 안드로이드 플레이스토어 가입하기
 * play.google.com/apps
 * https://play.google.com/console/signup
 */