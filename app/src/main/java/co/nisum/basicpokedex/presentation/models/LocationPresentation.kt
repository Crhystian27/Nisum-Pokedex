package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.LocationArea
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationPresentation(
    val location_area: LocationArea
): Parcelable

fun equals(oldItem: LocationPresentation, newItem: LocationPresentation): Boolean =
    oldItem.location_area == newItem.location_area
