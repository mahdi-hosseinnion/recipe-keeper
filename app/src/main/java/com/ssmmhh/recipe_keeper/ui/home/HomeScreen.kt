package com.ssmmhh.recipe_keeper.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ssmmhh.recipe_keeper.ui.theme.RecipeKeeperTheme

@Composable
fun MainScreen(viewModel: HomeScreenViewModel) {

}

@Composable
fun MainScreen(uiState: HomeScreenUiState) {
    Text(
        text = uiState.text
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RecipeKeeperTheme {
        MainScreen(
            uiState = HomeScreenUiState(
                text = "Welcome to the recipe keeper app this is just simple app to store your faviarte recipes"
            )
        )
    }
}