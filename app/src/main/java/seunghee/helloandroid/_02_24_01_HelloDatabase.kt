package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.StringBuilder

class _02_24_01_HelloDatabase : AppCompatActivity() {

    lateinit var dbHelper : _02_24_01_MemberHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_24_01__hello_database)

        // 사용하려면 이 activity 가 main activity가 되어야 한다.
        dbHelper = _02_24_01_MemberHelper(this)

        // 데이터 삽입
        dbHelper.insertMember("abc", "123")
        dbHelper.insertMember("123", "abc")
        Log.d("Helper", "데이터 저장성공")

        // 데이터 조회
        var rs = dbHelper.selectMember()
        // var rs = dbHelper.selectMember2()
        // var rs = dbHelper.selectMember3("123")
        var sb = StringBuilder()
        sb.append("데이터 조회 갯수 : ${rs.count.toString()} \n")
        while ( rs.moveToNext()) {
            var uid = rs.getString(rs.getColumnIndex("uid"))
            var pwd = rs.getString(rs.getColumnIndex("pwd"))
            sb.append("${uid} ${pwd} \n")
        }
        rs.close()
        Log.d("Helper", sb.toString())
    }
}
/* 안드로이드 내장형 데이터 베이스 (data/data/패키지명/activity) 쪽에 생김
 *
 * adb
 * Android Debug Bridge
 * 안드로이드 장치와 통신하여 디버깅 등의 작업을 진행할 수 있는 다목적 CLI 툴
 * 주로 어플리케이션 설치 및 삭제와 장치 정보 추출, 로그 확인, 시스템 앱 비활성화 등
 * 다양한 용도로 사용하는 프로그램
 *
 * adb 관련 파일 위치
 * C:\Users\사용자명\AppData\Local\Android\Sdk
 *
 * 명령 프롬프트 실행
 * cd appdata\local\android\sdk\platform-tools
 * adb
 * adb devices
 *      adb 연결확인
 *      => List of devices attached
 *         emulator-5554 device
 * adb root
 *      adb root 권한 부여
 * adb shell
 *      장치 쉘 접속
 *      => generic_x86_arm:/ #
 * cd /data/data/패키지명.Activity 명
 *      앱 패키지 디렉토리로 이동
 * ls
 *      => cache code_cache
 * mkdir database
 *      sqlite 작업용 디렉토리 생성
 * cd database
 *
 *
 * sqlite
 * 내장형 embeded 데이터베이스 관리 프로그램
 * 클라이언트/서버 구조로 작동하는 시스템이 아닌 응용프로그램에 포함시켜 사용하는
 * 비교적 가벼운 데이터베이스 시스템
 * 단일 파일로 구성되어 사용하기가 편함
 * 표준 SQL 문법을 90% 정도 지원함
 * 반면, 데이터 양이 커지면 성능 저하문제 발생
 *
 * sqlite 데이터 형식
 * Null, INTEGER, REAL(실수), TEXT, BLOB(2진 데이터), NUMERIC
 */



/* 윈도우용 데이터 베이스 (안드로이드 환경과 똑같이 만들어서 쓸 수 있다)
 *
 * www.sqlite.org 접속
 *      하단의 Latest Release Download
 *      밑으로 좀 내려서 Precompiled Binaries for Windows
 *      sqlite-dll-win64-x64-3340100.zip 다운
 *      sqlite-tools-win32-x85-3340100.zip 다운
 *
 *      다운로드한 파일을 c:java 에 sqlite3 이라는 폴더를 새로 만들고 옮김
 *
 * 새로운 명령 프롬프트 실행
 * cd /JAVA/sqlite3
 */



/* sqlite3 아이디.db
 *      데이터 베이스 생성
 *
 * .header on
 * .mode column
 *      sqlite 환경설정 (조회결과가 이쁘게 보이도록 함
 *
 * create table member (userid varchar(16), passwd varchar(16));
 *      member 테이블 생성
 *
 * .table
 *      생성 테이블 확인
 *
 * insert into member values('abc', '123');
 * insert into member values('123', 'abc');
 *      데이터 생성
 *
 * select * from member;
 *      데이터 조회
 *      => abc|123
 *         123|abc
 * .schema 테이블명
 * pragma table_info(테이블명);
 *      테이블 구조 확인
 */

/* DBeaver 로 실행하기
 * DBeaver 실행한 후
 * Connect to database
 * sqlite 선택
 * Path: Browse 해서 생성한 아이디.db를 선택하면됨
 *      윈도우 버전이라면, c/JAVA/sqlite3 에서 아이디.db 선택
 */

/* sqlite 프로그래밍
 * 안드로이드에서 적용할 수 있는 sqlite 프로그래밍 방식은 크게 3가지
 *
 * 1. SQLiteDatabase 클래스 사용 (옛날 방식)
 *      openOrCreateDatabase()  : 생성하거나 이미 존재하는 데이터베이스 객체를 가져옴
 *      execSQL()               : insert, update, delete 질의문을 실행
 *      rawQuery()              : select 질의문 실행
 *
 * 2. SQLiteHelper
 *      SQLiteDatabase 클래스를 사용하는 경우 기존 테이블의 스키마나 구조가 변경되면
 *      유지보수의 범위가 넓어져서 비효율적임 - Helper 클래스를 사용할 것을 추천
 *      onCreate()  : 데이터베이스 생성시 실행
 *      onOpen()    : 생성한 데이터베이스를 열었을 때
 *      onUpgrade() : 데이터베이스를 수정했을 때
 *
 *
 *
 * 3. Room 라이브러리 클래스사용
 */