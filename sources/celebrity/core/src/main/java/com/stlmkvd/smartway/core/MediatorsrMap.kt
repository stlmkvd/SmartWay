package com.stlmkvd.smartway.core

import javax.inject.Provider

typealias MediatorsMap = Map<Class<*>, @JvmSuppressWildcards Provider<Any>>

inline fun <reified T : Any> MediatorsMap.getMediator(): T {
    return getValue(T::class.java).get() as T
}