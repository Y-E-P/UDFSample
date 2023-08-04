package com.pou.udfsample.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pou.udfsample.domain.FruitModel
import com.pou.udfsample.domain.NutritionModel

@Composable
fun FruitsListScreen(navigation: NavHostController, viewModel: MainViewModel) {
    val fruits = viewModel.getAllFruitsList().observeAsState(MainViewModel.State())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            items(fruits.value.data) {
                when (it) {
                    is FruitsAdapterData.Data -> FruitItemView(it)
                    FruitsAdapterData.Error -> ErrorListView("Error")
                    is FruitsAdapterData.GenusItems -> GenusItemsList(it)
                    FruitsAdapterData.Loading -> LoadingSpinner()
                }
            }
        }
    }
}

@Composable
fun FruitItemView(item: FruitsAdapterData.Data) {
    Card(
        modifier = Modifier
            .padding(top = 3.dp, start = 6.dp, end = 6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.name, fontSize = TextUnit(16f, TextUnitType.Sp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(item.family)
            Text(item.genus)
        }
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(20.dp))
            Column(Modifier.padding(6.dp)) {
                Text(item.family)
                Text(item.genus)
            }
        }

    }
}

@Preview
@Composable
fun FruitItemViewPreview() {
    FruitItemView(
        FruitsAdapterData.Data(
            FruitModel(
                1,
                "Genus",
                "Orange",
                "ORDER",
                "Citrus",
                NutritionModel(0.1f, 1.1f, 1.3f, 1.5f, 3.3f)
            )
        )
    )
}

@Composable
fun ErrorListView(errorText: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Text(
            textAlign = TextAlign.Center, text = errorText
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun ErrorListViewPreview() {
    ErrorListView("Error")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenusItemsList(item: FruitsAdapterData.GenusItems) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(item.items) { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp), // gap between items
                selected = (item.isSelected),
                onClick = {},
                label = {
                    when (item) {
                        is GenusPickerData.All -> Text(text = "All")
                        is GenusPickerData.Genus -> Text(text = item.name)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun GenusItemsListPreview() {
    GenusItemsList(FruitsAdapterData.GenusItems(emptyList()))
}

@Composable
fun LoadingSpinner() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator()
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LoadingSpinnerPreview() {
    LoadingSpinner()
}