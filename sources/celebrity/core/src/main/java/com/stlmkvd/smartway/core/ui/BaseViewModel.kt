package com.stlmkvd.smartway.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
abstract class BaseViewModel<
    E : MviEvent,
    S : MviState,
    A : MviAction
    >(
    private val initState: S
) : ViewModel() {

    protected abstract fun processUiIntent(uiIntent: E): Flow<A>

    protected abstract fun reduceState(state: S, action: A): S

    protected var isInitialized = false
        private set

    private val actions = MutableSharedFlow<A>()

    val uiState: StateFlow<S> = actions
        .scan(initState, ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initState)

    fun acceptUiIntent(uiIntent: E) {
        viewModelScope.launch {
            actions.emitAll(processUiIntent(uiIntent))
        }
    }

    protected fun emitAction(action: A) {
        viewModelScope.launch {
            actions.emit(action)
        }
    }

    open fun bootstrap() {
    }

    fun initialize() {
        if (!isInitialized) {
            isInitialized = true
            bootstrap()
        }
    }
}