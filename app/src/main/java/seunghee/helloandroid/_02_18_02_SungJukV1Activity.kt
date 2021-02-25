package seunghee.helloandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.FileOutputStream

class _02_18_02_SungJukV1Activity : AppCompatActivity() {

    var TAG = "SungJukActivity"
    var fname = "sungjuk.dat"

    lateinit var sjname : EditText
    lateinit var sjkor : EditText
    lateinit var sjeng : EditText
    lateinit var sjmat : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_18_02__sung_juk_v1)

        sjname = findViewById(R.id.sj3name)
        sjkor = findViewById(R.id.sj3kor)
        sjeng = findViewById(R.id.sj3eng)
        sjmat = findViewById(R.id.sj3mat)

    }

    fun saveSungJuk(v: View) {

        var sjdata = computeSungJuk()
        writeSungJuk(sjdata)

    }

    private fun writeSungJuk(sjdata : String) {

        var ofs : FileOutputStream = openFileOutput(fname, Context.MODE_APPEND or Context.MODE_PRIVATE)
        ofs.write(sjdata.toByteArray())
        ofs.close()

        Toast.makeText(applicationContext, "내부저장소에 파일 쓰기 완료!!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "내부저장소에 파일 쓰기 완료~")
    }

    private fun computeSungJuk( ) : String {
        var sjsum = (sjkor.text.toString().toInt()) + (sjeng.text.toString().toInt()) + (sjmat.text.toString().toInt())
        var sjmean = sjsum.toDouble()/3
        var sjgrd :String = when((sjmean/10).toInt()) {
            9,10 -> "수"
            8 -> "우"
            7 -> "미"
            6 -> "양"
            else -> "가"
        }

        var sjdata = "이름 : ${sjname.text}\n국어 : ${sjkor.text}\n영어 : ${sjeng.text}\n수학 : ${sjmat.text}\n\n" +
                "총점 : ${sjsum}\n평균 : ${sjmean}\n학점 : ${sjgrd}\n\n"

        return sjdata
    }


    fun resetSungJuk(v:View) {
        sjname.setText("")
        sjkor.setText("")
        sjeng.setText("")
        sjmat.setText("")
    }
}