package com.squaredcandy.asteroid.composition

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import com.squaredcandy.asteroid.R

private val lightPalette = lightColors(
    primary = Color.amber_700,
    primaryVariant = Color.amber_800,
    secondary = Color.grey_800,
    secondaryVariant = Color.grey_900,
    background = Color.white_50,
    surface = Color.white_50,
    error = Color.red_600,
    onPrimary = Color.white_50,
    onSecondary = Color.white_50,
    onBackground = Color.black_900,
    onSurface = Color.black_900,
    onError = Color.white_50,
)

private val darkPalette = darkColors(
    primary = Color.amber_600,
    primaryVariant = Color.amber_400,
    secondary = Color.grey_600,
    background = Color.black_900,
    surface = Color.black_800,
    error = Color.red_200,
    onPrimary = Color.white_50,
    onSecondary = Color.white_50,
    onBackground = Color.white_50,
    onSurface = Color.white_50,
    onError = Color.black_900,
)

private val roboto = fontFamily(
    font(
        R.font.roboto_slab_thin,
        FontWeight.Thin,
    ),
    font(
        R.font.roboto_slab_extralight,
        FontWeight.ExtraLight,
    ),
    font(
        R.font.roboto_slab_light,
        FontWeight.Light,
    ),
    font(
        R.font.roboto_slab_regular,
        FontWeight.Normal,
    ),
    font(
        R.font.roboto_slab_medium,
        FontWeight.Medium,
    ),
    font(
        R.font.roboto_slab_semibold,
        FontWeight.SemiBold,
    ),
    font(
        R.font.roboto_slab_bold,
        FontWeight.Bold,
    ),
    font(
        R.font.roboto_slab_extrabold,
        FontWeight.ExtraBold,
    ),
    font(
        R.font.roboto_slab_black,
        FontWeight.Black,
    ),
)

private val typography = Typography(
    defaultFontFamily = roboto
)

private val shapes = Shapes(

)

@Composable
fun AsteroidTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if(darkMode) darkPalette else lightPalette,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}