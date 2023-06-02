package com.stlmkvd.smartway.domain.usecase

interface UseCase<I, O> {

    fun execute(input: I): O
}