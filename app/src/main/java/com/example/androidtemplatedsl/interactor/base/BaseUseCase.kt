package com.example.androidtemplatedsl.interactor.base

abstract class BaseUseCase<in Params, Response> {
    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    abstract suspend fun invoke(parameters: Params? = null): Response
}
