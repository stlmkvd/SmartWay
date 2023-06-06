package com.stlmkvd.smartway.core.domain

interface SuspendingUseCase<I, O> {

    suspend fun execute(input: I): O
}