package com.stlmkvd.smartway.entrypoint

import com.stlmkvd.smartway.ApplicationProvider
import com.stlmkvd.smartway.core.di.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class
    ]
)
interface EntryPointComponent {

    fun inject(act: MainActivity)

    companion object {

        fun init(
            applicationProvider: ApplicationProvider,
        ): EntryPointComponent {
            return DaggerEntryPointComponent.builder()
                .applicationProvider(applicationProvider)
                .build()
        }
    }
}