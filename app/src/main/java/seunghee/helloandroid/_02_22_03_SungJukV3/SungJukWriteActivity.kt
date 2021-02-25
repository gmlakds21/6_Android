package seunghee.helloandroid._02_22_03_SungJukV3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import seunghee.helloandroid.R
import seunghee.helloandroid._02_22_01_Sub1Activity

class SungJukWriteActivity : AppCompatActivity() {

    var sjdata = arrayOfNulls<EditText>(4)
    var sjdataid = arrayOf(R.id.sj3name, R.id.sj3kor, R.id.sj3eng, R.id.sj3mat)

    lateinit var name:EditText
    lateinit var kor:EditText
    lateinit var eng:EditText
    lateinit var mat:EditText

    lateinit var sj:SungJuk_VO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_03__sung_juk_write)

        for( i in sjdata.indices) {
            sjdata[i] = findViewById(sjdataid[i])
        }
    }

    fun saveSungJuk(v: View) {
        sj = SungJuk_VO(sjdata[0]!!.text.toString(), sjdata[1]!!.text.toString(), sjdata[2]!!.text.toString(), sjdata[3]!!.text.toString())
        SungJuk_Service.computeSungJuk(sj)
        SungJuk_Service.writeSungJuk(sj, applicationContext) // applicationContext 대신에 this 로 써도 된다.


    }

    fun goReadAll(v:View) {
        var listsungjuk = Intent(this, ListSungJukActivity::class.java)
        startActivity(listsungjuk)
    }
}