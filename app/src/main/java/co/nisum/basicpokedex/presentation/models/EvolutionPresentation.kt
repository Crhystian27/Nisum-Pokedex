package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.Chain
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolutionPresentation(
    val chain: Chain
): Parcelable