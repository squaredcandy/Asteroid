package com.squaredcandy.asteroid.composition

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.squaredcandy.asteroid.R

@Composable
fun bottomBar() {
    BottomNavigation {
        bottomBarItem(R.drawable.ic_round_home_24, "Home")
//        bottomBarItem(R.drawable.ic_round_add_24, "Add")
        bottomBarItem(R.drawable.ic_round_settings_24, "Settings")
    }
}

@Composable
fun bottomBarItem(
    @DrawableRes iconDrawable: Int,
    label: String = "",
    selected: Boolean = false,
    onSelect: () -> Unit = {},
) {
    BottomNavigationItem(
        icon = {
            Image(asset = vectorResource(id = iconDrawable))
        },
        selected = selected,
        onSelect = onSelect,
        label = {
            Text(text = label)
        }
    )
}

@Composable
@Preview
fun bottomBarPreview() {
    AsteroidTheme(darkMode = false) {
        bottomBar()
    }
}

@Composable
@Preview
fun bottomBarPreviewDark() {
    AsteroidTheme(darkMode = true) {
        bottomBar()
    }
}