package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationResponse(
    val location_area: LocationArea
): Parcelable

@Parcelize
data class LocationArea(
    val name: String
): Parcelable