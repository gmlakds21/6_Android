package seunghee.helloandroid._02_24_02_BooksV1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookHelper(context: Context, version:Int) : SQLiteOpenHelper(context, dbname, null, version) {

    companion object {
        const val dbname: String = "seunghee3";
        const val version: Int = 1
        const val sql : String = "create table if not exists Book (" +
                "bid integer primary key autoincrement," +
                "brand text," +
                "bkname text," +
                "author text," +
                "pubdate text," +
                "price integer," +
                "regdate timestamp default current_timestamp)"
    }

    // onCreate 호출시점 : readableDatabase, writeableDatabase 호출시
    override fun onCreate(sqlite: SQLiteDatabase) {
        sqlite.execSQL(sql)
    }

    // onUpgrade 호출시점 : 기존 버전과 매개변수로 전달받은 버전이 다른 경우 호출
    // 만일, 기존 데이터를 보존하려면 기존 데이터를 백업하는 코드를 추가로 작성한 후 테이블 삭제
    override fun onUpgrade(sqlite: SQLiteDatabase, oldver: Int, newver: Int) {
        sqlite.execSQL("drop table if exists Book")
        // if exists 는 테이블이 존재한다면,
        onCreate(sqlite)
    }


    // 전체 도서 목록
    fun selectBook() : Cursor {
        var sqlite = readableDatabase
        return sqlite.rawQuery("select bid, bkname, author from Book order by bid desc", null)
    }


    // 도서 목록 상세 조회
    fun selectOneBook(bid:String) : Cursor {
        var sqlite = readableDatabase
        return sqlite.rawQuery("select * from Book where bid = ?", arrayOf(bid))
    }

    // 도서정보 입력
    fun insertBook(bk : BookVO) {
        var sqlite = readableDatabase
        sqlite.execSQL("insert into Book(brand, bkname, author, pubdate, price) values (?,?,?,?,?)", arrayOf(bk.brand, bk.bkname, bk.author, bk.pubdate, bk.price))
    }

    fun insertBook2(bk : BookVO) {
        var sqlite = readableDatabase
        var params = ContentValues()
        params.put("brand", bk.brand)
        params.put("bkname", bk.bkname)
        params.put("author", bk.author)
        params.put("pubdate", bk.pubdate)
        params.put("price", bk.price)
        sqlite.insert("Book", null, params)
    }

    fun insertBook3(params : ContentValues) {
        var sqlite = readableDatabase
        sqlite.insert("Book", null, params)
    }
}