package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel


@PaperParcel
class City(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("coord") val coord: Coord,
        @SerializedName("main") val main: Main,
        @SerializedName("dt") val dt: Long,
        @SerializedName("wind") val wind: Wind,
        @SerializedName("clouds") val clouds: Clouds,
        @SerializedName("weather") val weatherList: List<Weather>
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelCity.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelCity.CREATOR
    }
}