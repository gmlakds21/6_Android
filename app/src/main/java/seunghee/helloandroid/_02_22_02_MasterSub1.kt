package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.Toast

class _02_22_02_MasterSub1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_02__master_sub1)
        title = "선호도 조사 결과"

        // MasterPiece Activity 에서 넘겨준 id를 담아둘 배열 변수 선언
        // 만약 하지 않으면 rbar[1] = findViewById(R.id.rb1), ...;
        var rbid = arrayOf(R.id.rb1, R.id.rb2, R.id.rb3,
                          R.id.rb4, R.id.rb5, R.id.rb6,
                          R.id.rb7, R.id.rb8, R.id.rb9)

        // MasterPiece Activity 에서 넘겨준 투표수를 반영할 레이팅 바 객체를 저장할 배열 변수 선언
        var rbar = arrayOfNulls<RatingBar>(9)
        for (i in rbid.indices) {
            rbar[i] = findViewById(rbid[i])
            // 손으로 별 조작이 되지 않도록
            rbar[i]!!.isEnabled = false
        }

        // MasterPiece Activity 에서 넘겨준 객체를 받아서 적절한 변수에 대입
        var intent = intent
        var vote = intent.getIntArrayExtra("vote")
        var imgName = intent.getStringArrayExtra("imgName")

        // 찍은 만큼 점수가 나오게 하기
        for (i in rbid.indices) {
            rbar[i]!!.setRating(vote[i].toFloat())
        }

        // 찍은 수 / 총 투표 수
        var totvote:Float = 0F;
        for(i in vote.indices) {
            totvote += vote[i].toFloat()
        }
        Toast.makeText(applicationContext, "${totvote}", Toast.LENGTH_SHORT).show()
        for (i in rbid.indices) {
            rbar[i]!!.setRating(vote[i].toFloat()*5/totvote)
        }
    }

    fun goBack(v: View) {
        finish()
    }
}