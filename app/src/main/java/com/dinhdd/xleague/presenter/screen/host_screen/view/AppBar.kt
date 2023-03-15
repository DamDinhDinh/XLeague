package com.dinhdd.xleague.presenter.screen.host_screen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(title: String, modifier: Modifier = Modifier, onBackClick: () -> Unit = {}, isShowIcon: Boolean = true) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        if (isShowIcon) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", modifier = Modifier
                .size(24.dp)
                .clickable {
                    onBackClick()
                })
            Spacer(modifier = Modifier.size(8.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            modifier = modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        if (isShowIcon) Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
@Preview
fun AppBarPreview() {
    AppBar("X League")
}