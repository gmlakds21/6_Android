package seunghee.helloandroid

import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.TextView

// AsyncTask<Int, Int, Int>는
// doInBackground, onProgressUpdate, onPostExecute 의 매개변수를 정의한 것
class _02_26_05_ProgressTask2 : AsyncTask<Any, Int, Void?>() {

    var v:Int = 99999
    var limit: Int = 99999
    var pgb: ProgressBar? = null
    var tv: TextView? = null

    override fun onPreExecute() {
        v = 0
    }

    // 비동기 실제 작업
    override fun doInBackground(vararg vals: Any?): Void? {
        var params = vals[0] as ArrayList<Any>
        limit = params.get(0).toString().toInt()
        pgb = params.get(1) as ProgressBar
        tv = params.get(2) as TextView
        pgb!!.setProgress(0)

        // 취소버튼이 눌러 task가 취소되기 전까지 계속 실행
        while(!isCancelled()) {
            v++
            tv?.setText("AsyncTask를 이용한 백그라운드 작업2 : ${v}%")

            // 상태진행수치가 100이면 task실행을 중단함
            if (v > limit) {
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

        return null
    }

    // 비동기 작업중 처리
    override fun onProgressUpdate(vararg vals: Int?) {
        pgb!!.setProgress(v)
    }

    // 비동기 작업 종료
    override fun onPostExecute(result: Void?) {
        tv!!.setText("100% 완료되었습니다!!")
    }

    // 비동기 작업 취소
    override fun onCancelled(result: Void?) {
        pgb?.setProgress(v)
        tv?.setText("${v}%에서 취소되었습니다!!")
    }



    /*
    // 초기화 작업
    override fun onPreExecute() {
        super.onPreExecute()
    }

    // 비동기 실제 작업
    override fun doInBackground(vararg vals : Int?): Int {
        return 0
    }

    // 비동기 작업중 처리
    override fun onProgressUpdate(vararg vals: Int?) {
        super.onProgressUpdate(*vals)
    }

    // 비동기 작업 종료
    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
    }

    // 비동기 작업 취소
    override fun onCancelled(result: Int?) {
        super.onCancelled(result)
    }
     */
}