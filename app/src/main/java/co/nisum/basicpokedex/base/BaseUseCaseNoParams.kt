package co.nisum.basicpokedex.base

abstract class BaseUseCaseNoParams<T> {
    abstract suspend fun execute(): T
}