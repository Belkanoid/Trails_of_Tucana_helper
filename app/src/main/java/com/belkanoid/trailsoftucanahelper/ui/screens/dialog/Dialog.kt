package com.belkanoid.trailsoftucanahelper.ui.screens.dialog


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.belkanoid.trailsoftucanahelper.ui.screens.components.CustomButton


@Composable
fun Dialog(
    title: String,
    onClick: () -> Unit
) {
    Dialog(onDismissRequest = {  }) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            shape = RoundedCornerShape(10)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(32.dp))
                CustomButton(
                    text = "Да",
                    onClick = {onClick()}
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    Dialog(
        title = "Начать следующию партию?",
        onClick = {}
    )
}