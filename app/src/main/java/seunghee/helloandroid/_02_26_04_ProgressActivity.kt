package seunghee.helloandroid

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.Exception

class _02_26_04_ProgressActivity : AppCompatActivity() {

    lateinit var tv : TextView
    lateinit var pgb : ProgressBar
    //lateinit var at : ProgressTask
    lateinit var at : _02_26_05_ProgressTask2
    var v : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_26_04__progress)

        tv = findViewById(R.id.protv)
        pgb = findViewById(R.id.propgb)

    }

    // 실행버튼
    fun execbtn(v: View) {
        //at = ProgressTask()
        //at.execute(100)

        at = _02_26_05_ProgressTask2()
        var params = arrayListOf<Any>()
        params.add(100)
        params.add(pgb)
        params.add(tv)

        at.execute(params)
    }

    // 취소버튼
    // 추가 조치 사항 : 실행버튼 누르기 전까진 취소버튼을 누를수 없도록 함
    fun ccbtn(v: View) {
        // 비동기 작업 취소시 cancel메서드에 true를 넘김
        at.cancel(true)
    }

    // 비동기 처리 클래스 정의
    inner class ProgressTask : AsyncTask<Int, Int, Int>() {

        // 초기화 작업
        override fun onPreExecute() {
            v = 0
            pgb.setProgress(v)
            // 상태진행막대의 초기값을 0으로 설정
        }

        // 비동기 실제 작업
        override fun doInBackground(vararg vals: Int?): Int {

            // 취소버튼이 눌러 task가 취소되기 전까지 계속 실행
            while(!isCancelled()) {
                v++
                tv.setText("AsyncTask를 이용한 백그라운드 작업 : ${v}%")

                // 상태진행수치가 100이면 task실행을 중단함
                if (v >= vals[0]!!.toInt()) {
                    break
                } else {
                    // 상태진행수치가 100보다 작으면 onProgressUpdate메서드를 호출함
                    // 비동기 작업진행 중에는 UI변경 불가
                    // 따라서 onProgressUpdate 호출해서 UI를 변경함
                    publishProgress(v)
                }

                // 0.1초 정도 cpu 실행 중지함
                try { Thread.sleep(100) } catch (e: Exception) {}
            }

            return vals[0]!!.toInt()
        }

        // 비동기 작업중 처리
        override fun onProgressUpdate(vararg vals: Int?) {
            pgb.setProgress(vals[0]!!.toInt())
        }

        // 비동기 작업 종료
        override fun onPostExecute(result: Int?) {
            tv.setText("완료되었습니다!!")
        }

        // 비동기 작업 취소
        override fun onCancelled(result: Int?) {
            pgb.setProgress(v)
            tv.setText("취소되었습니다!!")
        }

    }
    /*

    // 비동기 처리 클래스 정의
    inner class ProgressTask : AsyncTask<Int, Int, Int>() {

        // 초기화 작업
        override fun onPreExecute() {
            v = 0
            pgb.setProgress(v)
            // 상태진행 막대의 초기값을 0으로 설정
        }

        // 비동기 실제 작업
        override fun doInBackground(vararg vals: Int?): Int {
            // 취소버튼을 누르기 전까지 계속 실행
            while (!isCancelled()) {
                v++

                // 상태 진행수치가 100이면 task 실행을 중단함
                if (v > vals[0]!!.toInt()) {
                    break;
                } else {
                    // 상태 진행 수치가 100보다 작으면 onProgressUpdate 메서드를 호출함
                    // 비동기 작업진행 중에는 UI 변경 불가
                    // 따라서 onProgressUpdate 호출해서 UI를 변경함
                    publishProgress(v)
                }

                // 0.1초 정도 cpu 실행 중지함
                try { Thread.sleep(100)} catch(e:Exception) { }
            }
            return vals[0]!!.toInt()
        }

     */
}