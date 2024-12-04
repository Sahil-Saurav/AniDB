package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.anidb.viewModels.DetailsViewModel

@Composable
fun Details_Bottom(viewModel:DetailsViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        var selectedIdx by remember {
            mutableStateOf(0)
        }

        val list = listOf(
            DetailsLabel(0,"About"),
            DetailsLabel(1,"Review"),
            DetailsLabel(2,"Characters")
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            list.forEachIndexed { index, detailsLabel ->
                Details_Filter(
                    label=detailsLabel.label,
                    selected = index==selectedIdx,
                    onClick = {
                        viewModel.setToDo(detailsLabel.label)
                        selectedIdx=index
                    }
                )
            }
        }
    }
}

data class DetailsLabel (
        val idx:Int,
        val label:String
        )

//@Composable
//@Preview(showBackground = true)
//fun BottomPreview(){
//    Details_Bottom()
//}
