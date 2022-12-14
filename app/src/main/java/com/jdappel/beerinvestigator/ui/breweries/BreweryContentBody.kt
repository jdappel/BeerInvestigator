package com.jdappel.beerinvestigator.ui.breweries

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jdappel.beerinvestigator.data.model.Brewery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun BreweryContentBody(
    breweries: List<Brewery>,
    paddingValues: PaddingValues
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        contentPadding = paddingValues,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        itemsIndexed(breweries) { index, brewery ->
            BreweryCard(
                brewery,
                modifier = Modifier.padding(4.dp)
            )

/*            val showLoadingSpinner = breweries.lastIndex == index &&
                (uiState is BreweryState.Loaded) && !(uiState as BreweryState.Loaded).reachedEnd
            AnimatedVisibility(visible = showLoadingSpinner) {
                BreweryLoadingBody(toEndOfList = true)
            }*/
        }
    }

/*    state.OnBottomReached {
        viewModel.onIntent(BreweryIntent.OnScrolledDown(uiState.selectedFilter, uiState.searchTerm))
    }*/
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = shouldLoadMore()

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) {
                    loadMore()
                }
            }
    }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Composable
fun LazyListState.shouldLoadMore(): State<Boolean> {
    return remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf false

            return@derivedStateOf lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }
}
