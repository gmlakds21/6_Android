package seunghee.helloandroid._02_25_02_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
class BookEntity (
    @PrimaryKey var bid : Int,
    var bkname:String?,
    var author:String?,
    var pubdate:String?,
    var price:String?,
    var regdate:String?
)