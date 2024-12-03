package com.example.anidb.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.anidb.R

@Composable
fun Details_Filter(label: String, selected:Boolean, onClick:()->Unit){

    FilterChip(
        selected = selected,
        onClick = {onClick()},
        label = { Text(label) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = colorResource(R.color.card_back),
            labelColor = colorResource(R.color.primary_blue),
            iconColor = colorResource(R.color.primary_blue),
            disabledContainerColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            disabledLeadingIconColor = colorResource(R.color.primary_blue),
            disabledTrailingIconColor = colorResource(R.color.primary_blue),
            selectedContainerColor = colorResource(R.color.card_back),
            disabledSelectedContainerColor = colorResource(R.color.card_back),
            selectedLabelColor = colorResource(R.color.orange),
            selectedLeadingIconColor = colorResource(R.color.orange),
            selectedTrailingIconColor = colorResource(R.color.orange)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if(selected) colorResource(R.color.orange) else colorResource(R.color.primary_blue)
        ),
        shape = RoundedCornerShape(64),
        modifier = Modifier
            .height(32.dp)
            .padding(
                start = 8.dp,
                end = 8.dp)
    )
}