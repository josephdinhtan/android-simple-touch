package com.jddev.simpletouch.domain.usecase

interface BaseUseCase<in Params, out Type> {
    suspend fun execute(params: Params): Type
}