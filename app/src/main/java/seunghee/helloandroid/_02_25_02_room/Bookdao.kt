package seunghee.helloandroid._02_25_02_room

import androidx.room.*
import seunghee.helloandroid._02_24_02_BooksV1.BookVO

@Dao
interface Bookdao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    // vararg : 매개변수의 갯수가 달라질 수 있을 때, 가변인자
    // bid 만 보낼지, 매개변수를 3개 보낼지
    @Update
    fun updateBook(vararg book: BookEntity)

    @Delete
    fun deleteBook(vararg book: BookEntity)

    // Select
    @Query("select bid, bkname, author from Book")
    fun readBookAll() : List<BookEntity>

    @Query("select * from Book where bid = :bid")
    fun readOneBook(bid: String) : BookEntity
}