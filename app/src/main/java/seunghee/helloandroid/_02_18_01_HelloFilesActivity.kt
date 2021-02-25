package seunghee.helloandroid

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class _02_18_01_HelloFilesActivity : AppCompatActivity() {

    var TAG : String = "HelloFilesActivity" // 현재 실행중인 액티비티이름 선언
    lateinit var newWrite : EditText

    // 저장소 경로
    var fname : String = "sayHello.txt"
    // 외부 저장소 절대 경로 (/storage/emulated/0)
    var fpath = Environment.getExternalStorageDirectory().getAbsolutePath()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__02_18_01__hello_files)

        // 파일에 쓸 내용을 정의
        newWrite = findViewById(R.id.newWrite)

        // 외부 저장소에 뭔가를 기록하기 위해 접근권한 추가
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

    }

    // 내부 저장소 파일 쓰기
    fun internalFileWrite(v:View) {
        var fos : FileOutputStream = openFileOutput(fname, Context.MODE_PRIVATE)
        // 앱 전용모드로 지정한 경로의 파일을 쓰기모드로 염

        fos.write(newWrite.text.toString().toByteArray())
        fos.close()
        // 파일에 내용을 바이트형태로 기록하고 닫음

        Toast.makeText(applicationContext, "내부저장소에 파일 쓰기 완료!!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "내부저장소에 파일 쓰기 완료~")
    }

    // 내부 저장소 파일 읽기
    fun internalFileRead(v:View) {
        var fis : FileInputStream = openFileInput(fname)
        // 앱 전용 모드로 지정한 경로의 파일을 읽기모드로 염
        var buffer = ByteArray(30)
        // 파일로부터 30byte 정도 읽어오기 위해 버퍼 설정

        fis.read(buffer)
        fis.close()
        // 지정한 크기만큼 문자를 읽어오고 닫음

        var result = buffer.toString(Charsets.UTF_8)
        // 일겅온 문자에 대해 UTF-8 인코딩 변환 실시

        Toast.makeText(applicationContext, "파일 내용 : ${result}", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "파일 내용 : ${result}")

    }

    // 외부 저장소 상태
    @RequiresApi(Build.VERSION_CODES.Q)
    fun externalStorageInfo(v: View) {
        var state = "읽기 쓰기 둘다 안됨"
        // 외부 저장소 읽기 쓰기 상태 확인용
        var state1 = Environment.getExternalStorageState()
        // 외부저장소 다루는 방법 지원 여부
        var state2 = Environment.isExternalStorageLegacy()
        // android <= 10 : Legacy Storage 방식 <- true
        // android >= 11 : Scoped Storage 방식 <- false

        // 외부 저장소 마운트 여부
        if (state1.equals(Environment.MEDIA_MOUNTED)) { // 저장소가 있는지
            if (state1.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) { // 저장소가 읽기 전용인지
                state = "읽기는 되지만, 쓰기는 안됨"
            } else {
                state = "읽기 쓰기 모두 가능"
            }
        }

        Toast.makeText(applicationContext, "${state1} ${state2} ${state}", Toast.LENGTH_SHORT).show()
    }

    // 외부 저장소 파일 쓰기
    fun externalFileWrite(v:View) {
        // fpath 위치에 fname(sayHello.txt)를 만듬
        var file = File(fpath, fname)
        var fos = FileOutputStream(file)
        
        fos.write(newWrite.text.toString().toByteArray())
        fos.close()

        Toast.makeText(applicationContext, "외부저장소에 파일 쓰기 완료!!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "외부저장소에 파일 쓰기 완료~")
    }

    // 외부 저장소 파일 읽기
    fun externalFileRead(v:View) {

        var file = File(fpath, fname)
        var fis = FileInputStream(file)

        // 30 씩 말고 전부 다 읽어오기
        var buffer  = ByteArray(fis.available())
        fis.read(buffer)
        var result = buffer.toString(Charsets.UTF_8)

        Toast.makeText(applicationContext, "외부저장소에서 읽은 내용 ${result}", Toast.LENGTH_SHORT).show()
    }

}
/* 안드로이드 저장 공간
 * View - tool window - device file explorer
 *
 * 내부 저장 공간 : 앱을 설치할때 앱만을 위한 전용공간이 할당됨
 * 해당 앱에서만 접근 가능
 * 앱을 삭제하면 해당 공간도 삭제됨
 * 디렉토리 위치 : /data/data/패키지이름
 *
 * 외부 저장 공간
 * sd 카드에 할당된 공간
 * 모든 앱들이 사용할 수 있는 공용 공간
 * 단, 사용자가 임의로 장착 제거할 수 있기 때문에 사용여부를 먼저 확인해야 함
 * 또한, 이 영역에 접근하려면 READ/WRITE_External_Storage 등의 퍼미션을 받아야 함
 *
 * 내부 저장공간 데이터 쓰기/읽기
 * FileInputStream, FileOutputStream 클래스의
 * openFileInput, openFileOutput 함수를 이용해서
 * 내부 저장소에 데이터를 저장하거나 읽어 올 수 있음
 *
 * 외부 저장공간 데이터 쓰기/ 읽기
 * 안드로이드 11에서 SD 카드에 데이터 입출력에 제한이 가해져서
 * 일반적인 방법으로는 접근이 불가
 * 간단한 해결책은 안드로이드 10버전으로 낮춰 개발하는 것임
 *
 * gradle 설정파일의 targetSdkVersion 을 30 -> 29로 재설정함
 * 또한, AndroidManifest.xml 에서 WRITE_EXTERNAL_STORAGE 권한 설정함
 *
 */