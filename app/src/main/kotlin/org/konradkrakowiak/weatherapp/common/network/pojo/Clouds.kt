package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

@PaperParcel
class Clouds(@SerializedName("all") val all: Int) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelClouds.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelClouds.CREATOR
    }
}