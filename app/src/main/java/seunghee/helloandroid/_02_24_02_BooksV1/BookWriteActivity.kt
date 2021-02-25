package seunghee.helloandroid._02_24_02_BooksV1

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import seunghee.helloandroid.R
import java.lang.StringBuilder

class BookWriteActivity : AppCompatActivity() {

    lateinit var dbhelper : BookHelper
    var bwet = arrayOfNulls<EditText>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_24_04__book_write)
        title = "도서등록"
        var etid = arrayOf(R.id.brand, R.id.bkname, R.id.author, R.id.pubdate, R.id.price)
        for(i in etid.indices) {
            bwet[i] = findViewById(etid[i])
        }
    }

    fun goWriteOk(v: View) {
        for (i in bwet.indices)
        if ( bwet[i]!!.text.toString() == "" ) {
            Toast.makeText(this, "도서정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        var bk = BookVO(
                bwet[0]!!.text.toString(),
                bwet[1]!!.text.toString(),
                bwet[2]!!.text.toString(),
                bwet[3]!!.text.toString(),
                bwet[4]!!.text.toString())

        dbhelper = BookHelper(this, 2)
        dbhelper.insertBook(bk)
        dbhelper.close()
        Toast.makeText(this, "도서등록 완료!", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun goReset(v:View) {
        for(i in bwet.indices) {
            bwet[i]?.setText("")
        }
    }
}