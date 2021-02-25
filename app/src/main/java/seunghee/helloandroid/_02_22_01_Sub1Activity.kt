package seunghee.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class _02_22_01_Sub1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_22_01__sub1)
        title = "Sub1Activity"
    }

    fun goBack(v: View) {
        finish()
    }
}