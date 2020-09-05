package com.squaredcandy.asteroid.composition

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.squaredcandy.asteroid.R

@Composable
fun topBar(
    name: String = stringResource(R.string.app_name),
) {
    TopAppBar {
        Text(
            modifier = Modifier.gravity(Alignment.CenterVertically)
                .offset(8.dp),
            text = name,
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
@Preview
fun topBarPreview() {
    AsteroidTheme(darkMode = false) {
        topBar()
    }
}

@Composable
@Preview
fun topBarPreviewDark() {
    AsteroidTheme(darkMode = true) {
        topBar()
    }
}