package seunghee.helloandroid._02_22_03_SungJukV3

import android.os.Parcel
import android.os.Parcelable


// 다른 액티비티에 객체형태로 전달하기 위해
// parcelable 인터페이스를 구현해서 재 작성함
// add implement 함
data class SungJuk_VO(var name:String, var kor:String, var eng:String, var mat:String) :
    Parcelable {

    var tot : String = "0";
    var avg : String = "0.0";
    var grd : String = "가"

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        tot = parcel.readString().toString()
        avg = parcel.readString().toString()
        grd = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(kor)
        parcel.writeString(eng)
        parcel.writeString(mat)
        parcel.writeString(tot)
        parcel.writeString(avg)
        parcel.writeString(grd)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SungJuk_VO> {
        override fun createFromParcel(parcel: Parcel): SungJuk_VO {
            return SungJuk_VO(parcel)
        }

        override fun newArray(size: Int): Array<SungJuk_VO?> {
            return arrayOfNulls(size)
        }
    }
}
