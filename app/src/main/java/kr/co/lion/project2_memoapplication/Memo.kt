package kr.co.lion.project2_memoapplication

import android.os.Parcel
import android.os.Parcelable

class Memo(var title: String?, var text: String?, var date: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Memo> {
        override fun createFromParcel(parcel: Parcel): Memo {
            return Memo(parcel)
        }

        override fun newArray(size: Int): Array<Memo?> {
            return arrayOfNulls(size)
        }
    }
}