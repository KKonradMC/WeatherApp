package org.konradkrakowiak.weatherapp.common.network.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

@PaperParcel
class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("pressure") val pressure: Double,
        @SerializedName("sea_level") val seaLevel: Double,
        @SerializedName("grond_level") val grondLevel: Double,
        @SerializedName("humidity") val humidity: Double
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) = PaperParcelMain.writeToParcel(this, dest, flags)

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelMain.CREATOR
    }
}