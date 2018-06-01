package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

@PaperParcel
class Coord(
        @SerializedName("lon") val lon: Double,
        @SerializedName("lat") val lat: Double
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelCoord.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelCoord.CREATOR
    }
}