package com.example.anidb.fragments

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Topbar
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.SearchViewModel

@Composable
fun Search_Top(viewModel:ApiViewModel,navController: NavHostController){
    val searchViewModel = viewModel<SearchViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    //val context = LocalContext.current
    Topbar("Search", onBackClick = {navController.navigateUp()})
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.pageBack))
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        TextField(
            value = searchViewModel.search.value,
            onValueChange = {searchViewModel.setSearch(it)},
            label = {Text("Search", color = colorResource(R.color.search_icon))},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Log.i("searchclick",searchViewModel.getSearch())
                    viewModel.getAnimeSearch(searchViewModel.getSearch(),searchViewModel.getType())
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = colorResource(R.color.search_icon))
                           },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = TextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.Unspecified,
                errorTextColor = Color.Unspecified,
                focusedContainerColor = colorResource(R.color.search_back),
                unfocusedContainerColor = colorResource(R.color.search_back),
                disabledContainerColor = Color.Unspecified,
                errorContainerColor = Color.Unspecified,
                cursorColor = colorResource(R.color.primary_blue),
                errorCursorColor = Color.Unspecified,
                textSelectionColors = TextSelectionColors(
                    handleColor = colorResource(R.color.primary_blue),
                    backgroundColor = colorResource(R.color.primary_blue)),
                focusedIndicatorColor = colorResource(R.color.primary_blue),
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Unspecified,
                errorIndicatorColor = Color.Unspecified,
                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Unspecified,
                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Unspecified,
                focusedLabelColor = Color.Unspecified,
                unfocusedLabelColor = Color.Unspecified,
                disabledLabelColor = Color.Unspecified,
                errorLabelColor = Color.Unspecified,
                focusedPlaceholderColor = Color.Unspecified,
                unfocusedPlaceholderColor = Color.Unspecified,
                disabledPlaceholderColor = Color.Unspecified,
                errorPlaceholderColor = Color.Unspecified,
                focusedSupportingTextColor = Color.Unspecified,
                unfocusedSupportingTextColor = Color.Unspecified,
                disabledSupportingTextColor = Color.Unspecified,
                errorSupportingTextColor = Color.Unspecified,
                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Unspecified,
                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Unspecified
            )
        )
        Type_Select(viewModel,searchViewModel)
    }
}

//@Composable
//@Preview(showBackground = true)
//fun TOPpreview(){
//    Search_Top()
//}