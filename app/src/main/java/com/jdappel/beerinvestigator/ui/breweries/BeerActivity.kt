package com.jdappel.beerinvestigator.ui.breweries

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a [com.jdappel.beerinvestigator.ui.viewmodel.BreweryViewModel] that is injected
 * to access beer information.
 */
@AndroidEntryPoint
class BeerActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    @InternalCoroutinesApi
    @ExperimentalFoundationApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BreweryScreen()
        }
    }
}