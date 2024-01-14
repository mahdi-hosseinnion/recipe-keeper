@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ssmmhh.recipe_keeper.ui.home

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.ssmmhh.recipe_keeper.data.recipe.Image
import com.ssmmhh.recipe_keeper.data.recipe.MockRecipeRepository
import com.ssmmhh.recipe_keeper.data.recipe.Recipe
import com.ssmmhh.recipe_keeper.ui.common.LoadableData
import com.ssmmhh.recipe_keeper.ui.theme.RecipeKeeperTheme

@Composable
fun HomeScreen() {
    val viewModel = viewModel<HomeScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState.value)
}

@Composable
fun HomeScreen(uiState: HomeScreenUiState) {
    RecipesList(uiState.recipes)
}

@Composable
fun RecipesList(loadableData: LoadableData<List<Recipe>>) {
    when (loadableData) {
        is LoadableData.Error -> Text("Error with message ${loadableData.errorMessage}")
        LoadableData.Loading -> CircularProgressIndicator()
        is LoadableData.Success -> {
            val pad = 16.dp
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = pad, vertical = pad),
                verticalArrangement = Arrangement.spacedBy(pad),
                horizontalArrangement = Arrangement.spacedBy(pad)
            ) {
                val recipes = loadableData.data
                items(recipes, { it.id }) {
                    RecipeItem(recipe = it)
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(),
        onClick ={}
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium),
            model = recipe.image.url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Column(Modifier.padding(8.dp)) {
            Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = recipe.ingredients.joinToString { "$it" },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewLoading() {
    RecipeKeeperTheme {
        HomeScreen(
            uiState = HomeScreenUiState(
                recipes = LoadableData.Loading
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewLoaded() {
    RecipeKeeperTheme {
        HomeScreen(
            uiState = HomeScreenUiState(
                recipes = LoadableData.Success(MockRecipeRepository.fakeRecipes)
            )
        )
    }
}