package com.example.qiitaclient.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val id: String,
    val name: String,
    val profileImageUrl: String
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = source.run {
                // readString が @Nullable のため、エルビス演算子を使用する
                User(readString() ?: "", readString() ?: "", readString() ?: "")
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(id)
            writeString(name)
            writeString(profileImageUrl)
        }
    }
}
