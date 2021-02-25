package seunghee.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class _02_22_02_MasterPieceActivity : AppCompatActivity() {

    // 이미지 클릭시 선호도 정보를 저정할 배열 선언
    var vote = IntArray(9)
    var imgName = arrayOf("독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                          "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
                          "피아노 레슨", "피아노 앞의 소녀들", "해변에서")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_02__master_piece)
        title = "영화 선호도 조사"

        var iv = arrayOfNulls<ImageView>(9)
        var ivid = arrayOf(R.id.iv1, R.id.iv2, R.id.iv3,
                           R.id.iv4, R.id.iv5, R.id.iv6,
                           R.id.iv7, R.id.iv8, R.id.iv9)

        // 이미지 클릭시 선택한 이미지의 투표수를 증가 시킴
        // 또한, 해당 이미지의 이름과 누적 투표수를 Toast 로 출력함
        // 따라서, 각 이미지 버튼에 클릭이벤트를 추가함
        // setOnClick 실행되면 for 문 전체를 가는게 아니라, setOnClick 부터 쭉 읽는다.
        for (i in ivid.indices) {
            iv[i] = findViewById<ImageView>(ivid[i])
            Toast.makeText(applicationContext, "매칭",Toast.LENGTH_SHORT).show()

            iv[i]!!.setOnClickListener {
                vote[i]++   // 투표수 증가
                Toast.makeText(applicationContext, "${imgName[i]} : ${vote[i]}표", Toast.LENGTH_SHORT).show();   // 결과 출력
            }
        }
    }

    fun goRating(v: View) {
        var mastersub1 : Intent = Intent(this, _02_22_02_MasterSub1::class.java)
        mastersub1.putExtra("vote", vote)
        mastersub1.putExtra("imgName", imgName)
        startActivity(mastersub1)
    }
}