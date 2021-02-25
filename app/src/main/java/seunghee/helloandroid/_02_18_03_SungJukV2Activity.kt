package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class _02_18_03_SungJukV2Activity : AppCompatActivity() {

    lateinit var sj2list : TextView
    lateinit var sj2list2 : TextView
    var fname : String = "sungjuk.dat"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_18_03__sung_juk_v2)

        sj2list = findViewById(R.id.sj2list)
        sj2list2 = findViewById(R.id.sj2list2)
        listSungJuk()

    }

    private fun listSungJuk() {
        var ifs :FileInputStream = openFileInput(fname)
        var buffer = ByteArray(ifs.available())
        ifs.read(buffer)
        ifs.close()
        var sjdata : String = buffer.toString(Charsets.UTF_8)

        sj2list.setText(sjdata)
    }

    private fun listSungJuk2() {
        var sjdata = StringBuffer()
        var ifs = openFileInput(fname)
        var br = BufferedReader(InputStreamReader(ifs))

        while (br.ready()) {
            sjdata.append(br.readLine()+"\n")
        }

        br.close()
        ifs.close()

        sj2list2.setText(sjdata)
    }
}