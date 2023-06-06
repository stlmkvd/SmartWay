package com.stlmkvd.smartway.core

interface MediatorsProvider {

    @MediatorsQualifier
    fun provideMediatorsMap(): MediatorsMap
}