package com.squaredcandy.asteroid.composition

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview

@Composable
fun MainCompose() {
    Scaffold(
        topBar = { topBar() },
        bottomBar = { bottomBar() },
    ) {

    }
}

@Preview
@Composable
fun mainComposePreview() {
    AsteroidTheme(darkMode = false) {
        MainCompose()
    }
}

@Preview
@Composable
fun mainComposePreviewDark() {
    AsteroidTheme(darkMode = true) {
        MainCompose()
    }
}