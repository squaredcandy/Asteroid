package com.squaredcandy.asteroid.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.squaredcandy.asteroid.composition.AsteroidTheme

import com.squaredcandy.asteroid.composition.MainCompose

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AsteroidTheme {
                MainCompose()
            }
        }
    }
}