package co.nisum.basicpokedex.utils

import timber.log.Timber

fun String.LogWarning() {
    Timber.tag("Warning: 🚫🚫🚫🚫🚫🚫").w(this)
}

fun String.LogDebug() {
    Timber.tag("Debug: 😎😎😎😎😎😎").d( this)
}

fun String.LogVerbose() {
    Timber.tag("Verbose: 👀👀👀👀👀👀").v(this)
}

fun String.LogInfo() {
    Timber.tag("Info: 😋😋😋😋😋😋").i(this)
}

fun String.LogError() {
    Timber.tag("Error: ❌❌❌❌❌❌").e(this)
}