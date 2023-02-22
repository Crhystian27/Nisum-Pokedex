package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.LocationArea
import kotlinx.parcelize.Parcelize

@Parcelize
data class EncountersListPresentation(
    val location_area: LocationArea
): Parcelable
