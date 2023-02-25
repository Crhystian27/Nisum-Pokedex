package co.nisum.basicpokedex.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import co.nisum.basicpokedex.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import java.util.*


fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    return networkCapabilities?.run {
        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && hasTransport(
                    NetworkCapabilities.TRANSPORT_ETHERNET
                ))
    } ?: false
}

fun Context.save(key: String, value: String) {
    val sharedPreferences =
        this.getSharedPreferences(this.getString(R.string.next_list), Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}

fun Context.load(key: String): String {
    val sharedPreferences =
        this.getSharedPreferences(this.getString(R.string.next_list), Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, "") ?: ""
}

fun String.toCapitalize(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.ROOT)
        else it.toString()
    }
}

fun String.toColorResourceId(): Int {
    return when (this) {
        "normal" -> R.color.typeNormal
        "fighting" -> R.color.typeFighting
        "water" -> R.color.typeWater
        "flying" -> R.color.typeFlying
        "poison" -> R.color.typePoison
        "ground" -> R.color.typeGround
        "rock" -> R.color.typeRock
        "bug" -> R.color.typeBug
        "ghost" -> R.color.typeGhost
        "steel" -> R.color.typeSteel
        "fire" -> R.color.typeFire
        "grass" -> R.color.typeGrass
        "electric" -> R.color.typeElectric
        "psychic" -> R.color.typePsychic
        "ice" -> R.color.typeIce
        "dragon" -> R.color.typeDragon
        "dark" -> R.color.typeDark
        "fairy" -> R.color.typeFairy
        "unknown" -> R.color.typeUnknown
        "shadow" -> R.color.typeShadow
        else -> {R.color.LightBlue}
    }
}

fun TabLayout.addTabs(tabs: List<String>) {
    tabs.forEach { tab ->
        addTab(newTab().setText(tab))
    }
}

fun String.extractNumberFromUrl(): String {
    val url = this.dropLast(1)
    return url.substring(url.lastIndexOf('/') + 1)
}



fun ImageView.loadImage(
    progressBar: ProgressBar? = null,
    view: View? = null,
    orientation: GradientDrawable.Orientation,
    imageUrl: String,
    placeholder: Int = R.drawable.egg_pokemon,
    error: Int = R.drawable.egg_pokemon,
) {
    val requestOptions = RequestOptions()
        .placeholder(placeholder)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(this)
        .asBitmap()
        .load(imageUrl)
        .apply(requestOptions)
        .into(object : CustomViewTarget<ImageView, Bitmap>(this) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                setImageBitmap(resource)


                Palette.from(resource).generate { palette ->
                    val defaultColor = ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                    val dominantColor = palette?.getDominantColor(defaultColor) ?: defaultColor
                    val mutedColor = palette?.getMutedColor(defaultColor) ?: defaultColor
                    val blendedColor = ColorUtils.blendARGB(dominantColor, mutedColor, 0.2f)

                    view?.background = createGradientDrawable(orientation,intArrayOf(Color.BLACK, blendedColor))

                    progressBar?.visibility = View.GONE
                }

            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                setImageResource(error)
                progressBar?.visibility = View.VISIBLE
                view?.background = createGradientDrawable(orientation,intArrayOf(Color.BLACK, Color.GRAY))
            }

            override fun onResourceCleared(placeholder: Drawable?) {}
        })
}


fun createGradientDrawable(orientation: GradientDrawable.Orientation, colors: IntArray): GradientDrawable {
    return GradientDrawable(orientation, colors)
}
