package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.EffectEntry
import kotlinx.parcelize.Parcelize


@Parcelize
data class AbilityPresentation(
    val effect_entries: List<EffectEntry>,
): Parcelable
