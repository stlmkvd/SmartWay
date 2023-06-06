package com.stlmkvd.smartway.core.ui

import com.stlmkvd.smartway.core.MultiViewModelFactory
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class MviFragment<
    I : MviEvent,
    S : MviState,
    VM : BaseViewModel<I, S, *>
    > : Fragment() {

    abstract fun injectSelf()

    abstract fun provideViewModelClass(): Class<VM>

    abstract fun render(uiState: S)

    @Inject
    protected lateinit var vmFactory: MultiViewModelFactory

    lateinit var viewModel: VM
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectSelf()
        viewModel = ViewModelProvider(this, vmFactory)[provideViewModelClass()]
        viewModel.initialize()
    }
}