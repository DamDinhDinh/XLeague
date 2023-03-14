package com.dinhdd.xleague.presenter.screen.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColors = lightColors(
    primary = primaryLight,
    secondary = secondaryLight,
    background = backgroundLight,
    surface = surfaceLight,
    error = errorLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    onBackground = onBackgroundLight,
    onSurface = onSurfaceLight,
    onError = onErrorLight
)

private val DarkColors = darkColors(
    primary = primaryDark,
    secondary = secondaryDark,
    background = backgroundDark,
    surface = surfaceDark,
    error = errorDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    onError = onErrorDark
)

@Composable
fun XLeagueTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) DarkColors else LightColors,
        typography = XLeagueTypography,
        shapes = XLeagueShape,
        content = content
    )
}

