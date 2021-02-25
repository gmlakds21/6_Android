package seunghee.helloandroid._02_24_02_BooksV1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import seunghee.helloandroid.R

class BookViewActivity : AppCompatActivity() {

    var bvet = arrayOfNulls<TextView>(6)
    var etid = arrayOf(R.id.bvbrand, R.id.bvbkname, R.id.bvauthor, R.id.bvpubdate, R.id.bvprice, R.id.bvregdate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_24_03__book_view)

        title = "View Book"


        var intent = intent
        var bkid = intent.getStringExtra("bkid")
        var bk = readOneBook(bkid)
        for(i in etid.indices) {
            bvet[i] = findViewById(etid[i])
        }

        bvet[0]?.setText(bk!!.brand)
        bvet[1]?.setText(bk!!.bkname)
        bvet[2]?.setText(bk!!.author)
        bvet[3]?.setText(bk!!.pubdate)
        bvet[4]?.setText(bk!!.price)
        bvet[5]?.setText(bk!!.regdate)

    }

    fun readOneBook(bid:String): BookVO? {
        var book : BookVO? = null
        var dbHelper = BookHelper(this, 2)
        var rs = dbHelper.selectOneBook(bid)

        while(rs.moveToNext()) {
            var bid = rs.getString(rs.getColumnIndex("bid"))
            var brand = rs.getString(rs.getColumnIndex("brand"))
            var bkname = rs.getString(rs.getColumnIndex("bkname"))
            var author = rs.getString(rs.getColumnIndex("author"))
            var pubdate = rs.getString(rs.getColumnIndex("pubdate"))
            var price = rs.getString(rs.getColumnIndex("price"))
            var regdate = rs.getString(rs.getColumnIndex("regdate"))
            book = BookVO(bid, brand, bkname, author, pubdate, price, regdate)
        }
        rs.close()
        dbHelper.close()

        return book
    }
}