package co.nisum.basicpokedex.base

abstract class BaseUseCase<params, T> {

    abstract suspend fun execute(params: params): T

}