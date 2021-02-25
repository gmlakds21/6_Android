package seunghee.helloandroid._02_24_02_BooksV1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import seunghee.helloandroid.R
import seunghee.helloandroid._02_22_03_SungJukV3.SungJukList2Activity
import java.lang.StringBuilder

class BookListActivity : AppCompatActivity() {

    lateinit var dbHelper : BookHelper
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_24_02__book_list)

        rv = findViewById(R.id.recyclerView2)

        var books : MutableList<BookVO> = readBookAll()
        Log.d("listbook", "${books.get(0).bkname}")

        var rvAdapter = RVAdapter(this, books) { bk -> goView(bk, this)}
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        rv.adapter = rvAdapter
    }

    class RVAdapter(var context: Context, var books:MutableList<BookVO>, var itemClick: (BookVO) -> Unit)
        : RecyclerView.Adapter<RVAdapter.Holder>() {

        // create class
        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

            var tvbid = itemView?.findViewById<TextView>(R.id.tvbid)
            var tvbkname = itemView?.findViewById<TextView>(R.id.tvbkname)
            var tvauthor = itemView?.findViewById<TextView>(R.id.tvauthor)

            fun bind(bk:BookVO, context: Context) {
                tvbid.text = bk.bid
                tvbkname.text = bk.bkname
                tvauthor.text = bk.author

                itemView.setOnClickListener { itemClick(bk) }
            }
        }

        // implement
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            var view = LayoutInflater.from(context).inflate(R.layout.activity__02_25_01__bk_recyclerview, parent, false)
            return Holder(view)
        }

        // implement
        override fun onBindViewHolder(holder: Holder, position: Int) = holder?.bind(books[position], context)

        // implement
        override fun getItemCount(): Int = books.size

    }



    // BookVO 테이블에서 도서목록을 조회해서 동적배열로 넘김
    fun readBookAll():MutableList<BookVO> {
        var books :MutableList<BookVO> = arrayListOf()
        var dbHelper = BookHelper(this, 2)

        var rs = dbHelper.selectBook()
        while(rs.moveToNext()) {
            var bid = rs.getString(rs.getColumnIndex("bid"))
            var bkname = rs.getString(rs.getColumnIndex("bkname"))
            var author = rs.getString(rs.getColumnIndex("author"))
            var bk = BookVO(bid,bkname, author)
            books.add(bk)
        }
        rs.close()
        dbHelper.close()
        return books
    }

    private fun goView(bk:BookVO, context: Context) {
        var view = Intent(this, BookViewActivity::class.java)
        view.putExtra("bkid",bk.bid)
        startActivity(view)
    }

    fun goWrite(v: View) {
        var write = Intent(this, BookWriteActivity::class.java)
        startActivity(write)
    }

    fun sqlitetest (context: Context) {

        dbHelper = BookHelper(context, 1)
        dbHelper.insertBook(BookVO("brand1","bkname1","author1","pubdate1","price1"))
        dbHelper.insertBook2(BookVO("brand2","bkname2","author2","pubdate2","price2"))
        /*
        var params : ContentValues
        params.put("brand", "brand3")   // brand3 <- EditText1.text()
        params.put("bkname", "bkname3")
        params.put("author", "author3")
        params.put("pubdate", "pubdate3")
        params.put("price", "price3")sdasd
        dbhelper.insertBook3(params)
         */

        var rs = dbHelper.selectBook()
        var sb = StringBuilder()
        while (rs.moveToNext()) {
            sb.append(rs.getString(rs.getColumnIndex("bid")))
            sb.append(rs.getString(rs.getColumnIndex("bkname")))
            sb.append(rs.getString(rs.getColumnIndex("author")))
            sb.append("\n")
        }
        rs.close()
        Log.d("Book", sb.toString())

        rs = dbHelper.selectOneBook("1")
        sb = StringBuilder()
        while (rs.moveToNext()) {
            sb.append(rs.getString(rs.getColumnIndex("bid")))
            sb.append(rs.getString(rs.getColumnIndex("bkname")))
            sb.append(rs.getString(rs.getColumnIndex("author")))
            sb.append(rs.getString(rs.getColumnIndex("pubdate")))
            sb.append(rs.getString(rs.getColumnIndex("price")))
            sb.append(rs.getString(rs.getColumnIndex("regdate")))
            sb.append("\n")
        }
        rs.close()
        Log.d("Book", sb.toString())

    }

}