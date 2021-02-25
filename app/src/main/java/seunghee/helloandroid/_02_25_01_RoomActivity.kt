package seunghee.helloandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import seunghee.helloandroid._02_25_02_room.BookDatabase
import seunghee.helloandroid._02_25_02_room.BookEntity

class _02_25_01_RoomActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_25_01__room)


        addBook(applicationContext).start()
        Log.d("Room1","도서정보 입력")

        // add 랑 밑에 3개랑 충돌남

        modifyBook(applicationContext).start()
        Log.d("Room1","102 도서정보 수정")

        removeBook(applicationContext).start()
        Log.d("Room1","103 도서정보 삭제")

        getBook(applicationContext, "101").start()
        Log.d("Room1","101 도서 조회")

        getAllBook(applicationContext).start()
        Log.d("Room1","전체 도서 조회")

    }

    class addBook(var context: Context) : Thread(){
        override fun run() {
            var bk : BookEntity

            bk = BookEntity(101,"도서제목1", "저자1", "2021-03-01", "15000", "2021-02-25")
            BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)
            // bk = BookEntity(102,"도서제목2", "저자2", "2021-03-01", "15000", "2021-02-25")
            // BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)
            bk = BookEntity(103,"도서제목3", "저자3", "2021-03-01", "15000", "2021-02-25")
            BookDatabase.getDatabase(context)!!.bookDao().insertBook(bk)
        }
    }

    class modifyBook(var context: Context) : Thread(){
        override fun run() {
            var bk = BookEntity(102,"도서제목2b", "저자2b", "2021-03-05", "35000", "2021-03-25")
            BookDatabase.getDatabase(context)!!.bookDao().updateBook(bk)
        }
    }

    class removeBook(var context: Context) : Thread(){
        override fun run() {
            var bk = BookEntity(103,"", "", null, null, null)
            BookDatabase.getDatabase(context)!!.bookDao().deleteBook(bk)
        }
    }

    class getAllBook(var context: Context) : Thread() {
        override fun run() {
            var bks = BookDatabase.getDatabase(context)!!.bookDao().readBookAll()

            for (bk in bks)
                Log.d("Room1", "${bk.bid}, ${bk.bkname}")
        }

    }

    class getBook(var context: Context, var bid:String) : Thread() {
        override  fun run() {
            var bk = BookDatabase.getDatabase(context)!!.bookDao().readOneBook(bid)
            Log.d("Room1", "${bk.bkname} ${bk.author} ${bk.price}")
        }

    }
}
/*
 * Room 라이브러리 클래스 사용
 * 안드리오드용 ORM 데이터베이스 아키텍처 라이브러리
 * 데이터베이스 객체를 자바 또는 코틀린 객체로 매핑해주는 라이브러리
 * SQLite 를 완벽하게 지원하면서 데이터베이스 접근을 강력하게 지원하는 추상화 라이브러리
 * 사용하기 편리하다는 장점으로 인해 구글에서 적극 사용을 추천함
 *
 * Room 3가지 주요 구성요소
 * database : entity 와 dao 를 연계해서 crud 작업을 지원하는 추상 클래스
 *              RoomDatabase 를 상속받아 작성
 * entity   : 데이터베이스의 테이블을 객체화할 청사진 클래스
 * dao      : 데이터베이스에 접근시 필요한 메서드 정의한 클래스
 *              SQL 관련 코드 없이 CRUD 작업을 할 수 있도록 관련 코드는 자동 생성됨됨 *
 *
 * Room 라이브러리를 사용하기 위한 준비작업
 * build.gradle (app) 파일에 다음내용 추가
 *
 * plugins 속성 :
 * id 'kotlin-kapt'
 *
 * dependencies 속성 :
 * def room_version = "2.2.6"
 *
 * implementation "androidx.room:room-runtime:$room_version"
 * kapt "androidx.room:room-compiler:$room_version"
 * // optional - Kotlin Extensions and Coroutines support for Room
 * implementation "androidx.room:room-ktx:$room_version"
 * // optional - Test helpers
 * testImplementation "androidx.room:room-testing:$room_version"
 *
 *
 * entity 정의하기
 * entity 는 데이터베이스 컬럼명과 1:1 대응되는 자바/코틀린 객체를 의미함
 *
 * @Entity      : 자바/코틀린 객체 정의, table_name 속성으로 1:1 대응할 테이블명 지정
 * @PrimaryKey  : 테이블의 기본키로 지정된 컬럼과 동일한 멤버변수 정의시 사용
 * @ColumnInfo  : 테이블의 컬럼명과 멤버변수를 다르게 지정할때 사용
 *                  @ColumnInfo(name="name") var bkname:String
 *                  테이블 컬럼명              멤버 변수명
 *
 * dao 정의하기
 * dao 는 데이터베이스에 접근해서 CRUD 관련 작업을 실행하는 코드를 인터페이스/추상클래스로 정의
 * @Insert, @Update, @Delete 와 @Query 등을 이용해서 관련 코드를 작성해 둠
 *
 * database 정의하기
 *
 */