package com.dylan.recipemeal.ui.components

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dylan.recipemeal.data.dummy.getDummyMealsItemResponse
import com.dylan.recipemeal.data.model.MealEntity
import com.dylan.recipemeal.data.model.MealsItem

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    data: MealsItem,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.idMeal)
            }
    ){
        Row(
           verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            AsyncImage(
                model = data.strMealThumb,
                contentDescription = data.strMeal + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = data.strMeal ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = data.strCategory ?: "",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = data.strTags ?: "",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    data: MealEntity,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.idMeal)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = data.strMealThumb,
                contentDescription = data.strMeal + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = data.strMeal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier
                )
                Text(
                    text = data.strCategory,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier
                )
                Text(
                    text = data.strTags ?: "",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = modifier,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MealItemPreview(){
    MealItem(
        data = getDummyMealsItemResponse().meals!![0],
        onNavigateToDetailScreen = {}
    )
}