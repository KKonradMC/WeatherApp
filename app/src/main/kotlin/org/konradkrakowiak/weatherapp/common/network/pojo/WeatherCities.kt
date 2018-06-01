package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

@PaperParcel
open class WeatherCities(
        @SerializedName("cod") var cod: String,
        @SerializedName("calctime") var calctime: Float,
        @SerializedName("cnt") var cnt: Int,
        @SerializedName("list") var cities: List<City>
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelWeatherCities.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelWeatherCities.CREATOR
    }
}
