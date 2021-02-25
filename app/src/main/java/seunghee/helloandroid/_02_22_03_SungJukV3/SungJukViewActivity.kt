package seunghee.helloandroid._02_22_03_SungJukV3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import seunghee.helloandroid.R

class SungJukViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_23_02__sung_juk_view)


        var result = arrayOfNulls<TextView>(7)
        var resultid = arrayOf(R.id.tvname1, R.id.tvkor1, R.id.tveng1,
            R.id.tvmat1, R.id.tvtot1, R.id.tvavg1, R.id.tvgrd1)

        for (i in result.indices) {
            result[i] = findViewById(resultid[i])
        }

        var intent = intent
        var student = intent.getParcelableExtra<SungJuk_VO>("student")

            SungJuk_Service.computeSungJuk(student)

        var resulttv = arrayOf(student.name, student.kor, student.eng,
                            student.mat, student.tot, student.avg, student.grd)
        for (i in resulttv.indices) {
            result[i]!!.setText(resulttv[i])
        }

        Log.d("view", "${student.name}")

    }

    fun readSungJuk(v: View) {
//        SungJuk_Service.readSungJuk()
    }

    fun goBack(v: View) {
        finish()
    }
}