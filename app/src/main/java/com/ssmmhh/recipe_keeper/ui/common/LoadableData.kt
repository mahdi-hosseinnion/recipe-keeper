package com.ssmmhh.recipe_keeper.ui.common

sealed interface LoadableData<out T>{
    data class Success<out T>(val data: T) : LoadableData<T>

    data class Error(val errorMessage: String) : LoadableData<Nothing>

    data object Loading : LoadableData<Nothing>
}