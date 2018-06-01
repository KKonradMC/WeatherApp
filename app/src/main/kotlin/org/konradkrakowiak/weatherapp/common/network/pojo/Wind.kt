package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

@PaperParcel
class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Double
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelWind.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelWind.CREATOR
    }
}