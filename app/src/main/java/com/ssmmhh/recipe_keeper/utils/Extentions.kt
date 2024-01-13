package com.ssmmhh.recipe_keeper.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> ioThread(action: suspend () -> T): T = withContext(Dispatchers.IO) {
    return@withContext action()
}