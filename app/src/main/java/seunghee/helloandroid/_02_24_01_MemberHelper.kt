package seunghee.helloandroid

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// SQLiteOpenHelper( , , , ) 작성하고 난다음에 함수명에서 implement 를 해주면 Override 함수들이 생성된다.
class _02_24_01_MemberHelper(context: Context?) : SQLiteOpenHelper(context, dbname, null, dbversion) {

    // SQLiteHelper 클래스 관련 상수
    companion object {
        const val dbname = "seunghee2"
        const val dbversion = 1
    }

    // 테이블 생성관련 함수 (p0 -> sqlite)
    override fun onCreate(sqlite: SQLiteDatabase?) {
        sqlite?.execSQL("create table if not exists member2 (uid text, pwd text)")
        // if not exists 는 테이블이 존재하지 않는다면,
    }

    // 테이블을 삭제하고 다시 테이블을 만드는 함수 (p0 -> sqlite)
    override fun onUpgrade(sqlite: SQLiteDatabase?, oldver: Int, newver: Int) {
        sqlite?.execSQL("drop table if exists member2")
        // if exists 는 테이블이 존재한다면,
        onCreate(sqlite)
        sqlite?.execSQL("drop table member2")
    }

    // member 테이블에 정보 저장하는 함수
    fun insertMember(uid:String, pwd:String) {
        val sqlite = readableDatabase
        val params = ContentValues()
        params.put("uid", uid)
        params.put("pwd", pwd)
        sqlite.insert("member2", null, params)

    }

    // member 테이블 조회
    fun selectMember( ) : Cursor {
        var sqlite = readableDatabase

        // select 할 컬럼 지정
        var resultset = arrayOf("uid","pwd")
        // 정렬 기준
        var sortOrder = "uid"


        return sqlite.query("member2", resultset, null, null, null, null, sortOrder)
    }

    fun selectMember2() : Cursor {
        var sqlite = readableDatabase
        return sqlite.rawQuery("select uid, pwd from member2 order by uid", null)
    }

    fun selectMember3(uid: String)  : Cursor {
        var sqlite = readableDatabase
        return sqlite.rawQuery("select count(uid) as cnt from member2 where uid = ?", arrayOf(uid))
    }

}