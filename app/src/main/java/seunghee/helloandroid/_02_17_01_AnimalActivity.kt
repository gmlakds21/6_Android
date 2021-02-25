package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.Group

class _02_17_01_AnimalActivity : AppCompatActivity() {

    lateinit var text1 : TextView
    lateinit var text2 : TextView

    lateinit var chkAgree : CheckBox
    lateinit var rGroup : RadioGroup
    lateinit var rdoDog : RadioButton
    lateinit var rdoCat : RadioButton
    lateinit var rdoRabbit : RadioButton

    lateinit var btnOk : Button
    lateinit var imgPet : ImageView

    lateinit var otherGroup : Group
    // otherGroup 에 나머지들을 걸어 놓고, 보이고 안보이게 함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_17_01__animal)
        title = "애완동물 보기 앱"

        initvar( )

        // 체크박스 클릭시 이벤트 처리
        /*
        chkAgree.setOnCheckedChangeListener { compoundButton, b ->
            if(chkAgree.isChecked == true) {
                otherGroup.visibility = android.view.View.VISIBLE
            } else {
                otherGroup.visibility = android.view.View.INVISIBLE
            }
        }
        */

        var state = if (chkAgree.isChecked == true)
            View.VISIBLE else View.INVISIBLE
        otherGroup.visibility = state

    }

    fun initvar() {
        text2 = findViewById(R.id.text2)

        chkAgree = findViewById(R.id.chkAgree)
        rGroup = findViewById(R.id.rGroup)
        rdoDog = findViewById(R.id.rdoDog)
        rdoCat = findViewById(R.id.rdoCat)
        rdoRabbit = findViewById(R.id.rdoRabbit)

        btnOk = findViewById(R.id.btnOk)
        imgPet = findViewById(R.id.imgPet)
        otherGroup = findViewById(R.id.otherGroup)
    }

    /* ImageView 에 그림을 표시하려면
     * 먼저, res -> drawable 에 그림파일을 업로드하고
     * 그림파일 호출시 'R.drawable.그림파일명'으로 지정하면 됨
     * 라디오 버튼그룹내 특정 라디오 버튼이 클릭된 것을 알아내려면
     * 라디오 버튼 '그륩명.checkedRadioButtonId' 를 사용하면 됨
     */
    fun showAnimal(v: View) {
        when(rGroup.checkedRadioButtonId) {
            R.id.rdoDog -> imgPet.setImageResource(R.drawable.dog)
            R.id.rdoCat -> imgPet.setImageResource(R.drawable.cat)
            R.id.rdoRabbit -> imgPet.setImageResource(R.drawable.rabbit)
        }
    }
}

/*
 * invisible
 * 요소는 존재하지만, 보이지 않는 형태로 만듬
 *
 */