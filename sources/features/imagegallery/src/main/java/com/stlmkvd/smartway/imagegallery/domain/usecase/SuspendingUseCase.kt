package com.stlmkvd.smartway.imagegallery.domain.usecase

interface SuspendingUseCase<I, O> {

    suspend fun execute(input: I): O
}