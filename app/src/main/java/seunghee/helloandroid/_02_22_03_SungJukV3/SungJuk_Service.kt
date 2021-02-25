package seunghee.helloandroid._02_22_03_SungJukV3

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

object SungJuk_Service {

    var fname = "sungjuk3.txt"

    fun computeSungJuk(sj:SungJuk_VO) {

        sj.tot = (sj.kor.toInt() + sj.eng.toInt() + sj.mat.toInt()).toString()
        sj.avg = (sj.tot.toDouble()/3).toString()
        sj.grd = when((sj.avg.toDouble()/10).toInt()) {
            9, 10 -> "수"
            8 -> "우"
            7 -> "미"
            6 -> "양"
            else -> "가"
        }
        Log.d("sjsrv", "${sj.tot} ${sj.grd}")
    }

    fun writeSungJuk(sj:SungJuk_VO, context:Context) {

        var sjdata = "${sj.name}|${sj.kor}|${sj.eng}|${sj.mat}|${sj.tot}|${sj.avg}|${sj.grd}\n"

        // 람다식으로 사용한 예
        context.openFileOutput(fname, Context.MODE_PRIVATE or Context.MODE_APPEND).use {
            fos -> fos.write(sjdata.toByteArray())
        }

        /* 기존 작성 방식, openFileOutput 을 할수 없어서 람다식으로 교체
            var fos:FileOutputStream = openFileOutput(fname, Context.MODE_PRIVATE or Context.MODE_APPEND)
            fos.write(sjdata.toByteArray())
            fos.close()
         */

        Toast.makeText(context, "성적 데이터 저장완료!!", Toast.LENGTH_SHORT).show()
    }


    fun readSungJuk(context: Context) : MutableList<SungJuk_VO> {

        var sjlist = arrayListOf<SungJuk_VO>()

        var fis = context.openFileInput(fname)
        var br = BufferedReader(InputStreamReader(fis))

        while (br.ready()) {
            var student = br.readLine().split("|")
            sjlist.add(SungJuk_VO(student[0].toString(), student[1], student[2], student[3]))
        }


        br.close()
        fis.close()

        return sjlist

    }


}