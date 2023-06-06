package com.stlmkvd.smartway.entrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stlmkvd.imagegalleryapi.ImageGalleryMediator
import com.stlmkvd.smartway.BaseApp
import com.stlmkvd.smartway.core.MediatorsMap
import com.stlmkvd.smartway.core.MediatorsQualifier
import com.stlmkvd.smartway.core.getMediator
import com.stlmkvd.smartway.entrypoint.theme.SmartWayTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    lateinit var component: EntryPointComponent

    @Inject
    @MediatorsQualifier
    lateinit var mediator: MediatorsMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = EntryPointComponent.init(
            (application as BaseApp).getApplicationProvider()
        )
        component.inject(this)
        setContent {
            SmartWayTheme {
                mediator.getMediator<ImageGalleryMediator>().Entry()
            }
        }
    }
}