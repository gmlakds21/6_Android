package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class _02_16_03_CalcActivity : AppCompatActivity() {

    lateinit var edit1 : EditText
    lateinit var edit2 : EditText
    lateinit var textResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_16_03__calc)

        edit1 = findViewById(R.id.Edit1)
        edit2 = findViewById(R.id.Edit2)
        textResult = findViewById(R.id.TextResult)

        ///////////////////////////////////////////////////////////

        // var num1 : Int;
        // edit1.addTextChangedListener() {
        //    num1 = fun onTextChange() = edit1.text.toString().toInt()
        // }

        // var num2 : Int;
        // edit2.addTextChangedListener() {
        //     num2 = fun onTextChange() = edit2.text.toString().toInt()
        // }

    }

    fun computeAdd(v: View) { textResult.text = (edit1.text.toString().toInt() + edit2.text.toString().toInt()).toString() }
    fun computeSub(v: View) { textResult.text = (edit1.text.toString().toInt() - edit2.text.toString().toInt()).toString() }
    fun computeMul(v: View) { textResult.text = (edit1.text.toString().toInt() * edit2.text.toString().toInt()).toString() }
    fun computeDiv(v: View) { textResult.text = (edit1.text.toString().toInt() / edit2.text.toString().toInt()).toString() }


    ///////////////////////////////////////////////////////////////////////////////////////


    // fun changeText(textNum : EditText) : Int = textNum.text.toString().toInt()
    // fun changeInt(result : Int) : String = result.toString()
    // fun computeAdd2(v: View) { textResult.text = changeInt(changeText(edit1) + changeText(edit2))}
    
    // fun computeAdd3(v: View) { textResult.text = (num1 + num2).toString() }
}


