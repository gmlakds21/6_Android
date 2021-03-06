package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import seunghee.helloandroid._02_26_02_JDBC._02_26_03_SelectEmp

class _02_26_01_HelloJDBCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_26_01__hello_jdbc)

        _02_26_03_SelectEmp().execute()
    }
}

/* 안드로이드 JDBC 프로그래밍
 * 자바프로그래밍이나 웹프로그래밍 과는 달리
 * 안드로이드에서 JDBC 프로그래밍할때는 AsyncTask 라는 클래스를 이용해서 구현함
 *
 * gradle 에 의존성 항목 추가
 * mvn repo mariadb 검색
 * https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client 접속
 * 2.7.2 선택
 * Gradle 선택
 * // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
 * implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.7.2'
 * 복사하고, dependencies 에 붙여넣기
 *
 * JDBC 프로그래밍을 위한  Manifest 설정
 * <uses-permission android:name="android.permission.INTERNET"/>
 */


/* EPERM 오류 발생시
 * => java.net.SocketException:socket failed:EPERM(Operation not permitted)
 * 해당 앱을 제거하고 다시 실행해 봄
 *
 * 비동기 vs 동기 프로그래밍
 * 동기 synchronous    : 프로그램의 특정 기능이나 함수를 호출했을 때 호출 받은 측
 *                      코드가 모든 동작을 완료할 때까지 대기하는 방식
 * 비동기 asynchronous : 프로그램의 특정 기능이나 함수를 호출했을 때 호출 받은 측
 *                      코드의 동작이 끝나길 기다리지 않고 호출한 측의 다음 코드를 바로 실행하는 방식
 *
 * readJDBCData()
 * showJDBCData()
 *
 * 동기 방식 에서는 readJDBCData()가 호출되면 이 코드가 끝날때까지
 *                showJDBCData() 코드는 기다려야 함
 * 비동기 방식 에서는 readJDBCData()가 호출되면 이 코드가 완료될때까지
 *                기다리지 않고 바로 showJDBCData() 코드가 호출 됨
 * 만일, readJDBCData()가 미처 다 끝나기도 전에 showJDBCData() 가 실행되어
 * 완료하는 경우 화면에 부분 업로드 된 체로 출력될 수 도 있음
 * 이런 부분을 보완하기 위해 안드로이드에서는 AsyncTask 추상 클래스의
 * onProgressUpdate 메서드를 이용해서 이전에 실행했던 매서드의 상황에 따라
 * 특정 메서드의 결과를 실시간으로 리프레시할 수 있음
 */


/* AsyncTask
 * 안드로이드에서 비동기적으로 실행될 작업을 위해 사용하는 클래스
 * 이전에 사용했던 Thread, Runnable 등을 직접 다루지 않아도
 * 메인 쓰레드와 별개로 비동기 실행을 구현할 수 있음
 *
 * 1. AsyncTask 는 추상클래스로 비동기 처리를 위해서 반드시 상속해야 함
 * 2. 비동기 처리를 위해서 "작업시작", "상태갱신", "결과확인" 이라는 공통된 단계를 거침
 *    만일, 이러한 단계가 필요없는 경우 Void?로 선언함
 *    작업시작 Params   : 비동기 작업에 필요한 정보 (파일경로,url,기타변수)
 *    상태갱신 Progress : 현재 작업 진행정보를 나타내는 상태값
 *    결과확인 Result   : 작업 실행 완료후 최종 결과값
 * 3. 비동기 작업 실행 단계를 정의하기 위해 다음의 메서드를 정의해야 함
 *    onPreExecute      : 작업 실행전에 호출되어야 하는 메서드 정의, 비동기 작업 초기화시 사용
 *    doInBackground    : 비동기 작업 실행 코드 정의, 실제 비동기 작업 처리시 사용 (필수)
 *    onProgressUpdate  : 비동기 작업 실행 중 갱신 상황을 알려주는 코드 정의
 *                        실제 비동기 작업 처리시 사용
 *                        주로, view 와 모델간의 데이터 갱신시 사용
 *    onPostExecute     : 작업 실행후에 호출되어야 하는 메서드 정의, 비동기 작업 종료시 사용
 *    onCancelled       : 비동기 작업 실행 취소 코드 정의
 *
 * ex) 동기 처리 예
 * 특정 파일 다운로드시 다운로드 완료 전까지는 마우스/키보드 입력이 멈춰있음
 * 또한, 파일 다운로드 진행 상황을 알 수 없음 (몇 % 다운로드 완료인지 모름)
 *
 * ex) 비동기 처리 예
 * 특정 파일 다운로드시 다운로드 완료와 상관없이 마우스/키보드 입력 가능
 * 또한, 파일 다운로드 진행 상황을 알 수 있음 (몇 % 다운로드 중인지 표시)
 *
 * 안드로이드 11 부터는 비동기 처리시 AsyncTask 대신에 RxJava 나 coroutine 을
 * 사용할 것을 권장 하지만, 여전히 AsyncTask 로 작성한 코드들이 많기 때문에
 * 당분간은 AsyncTask 로 작성한 코드가 아직도 여전할 것 같음
 * 한편, RxJava 나 coroutine 는 러닝커브가 다소 높은편임
 */