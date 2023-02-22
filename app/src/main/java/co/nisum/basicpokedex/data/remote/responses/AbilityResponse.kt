package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AbilityResponse(
    val effect_entries: List<EffectEntry>,
): Parcelable

@Parcelize
data class EffectEntry(
    val effect: String
): Parcelable