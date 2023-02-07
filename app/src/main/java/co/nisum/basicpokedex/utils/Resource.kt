package co.nisum.basicpokedex.utils

sealed class Resource<out T: Any>{
    data class Success<out T : Any>(val data: T) : Resource<T>()
}