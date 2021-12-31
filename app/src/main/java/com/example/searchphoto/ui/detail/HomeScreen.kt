package com.example.searchphoto.ui.detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.searchphoto.R
import com.example.searchphoto.ui.main.MainViewModel
import com.example.searchphoto.ui.theme.*
import data.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import main.PhotosItem


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(context: Context, mainViewModel: MainViewModel) {
    Box(
        Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipsSection(
                chips = listOf(
                    "Element 1",
                    "Element 2",
                    " Element 3",
                    " Element 4",
                    " Element 5",
                    " Element 6"
                )
            )
            DailyThought(context)

            val result = mainViewModel.picturesState.collectAsState(initial = DataState.Empty)
            when (result.value) {
                DataState.Empty -> Unit
                is DataState.Error -> {

                    Toast.makeText(
                        context,
                        (result.value as DataState.Error).exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                DataState.Loading -> Unit
                is DataState.Success -> {
                    MainContent((result.value as DataState.Success<List<PhotosItem>>).data)
                }
            }

        }

    }
}


@Composable
fun GreetingSection(
    name: String = "Emine"
) {
    var clickCount by remember {
        mutableStateOf(0)
    }
    var myself by remember {
        mutableStateOf(name)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            Text(text = "Good Morning $myself", style = MaterialTheme.typography.h2)
            Text(text = "We Wish you have a Good Day !", style = MaterialTheme.typography.body1)

        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = " recherche icone",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    clickCount++
                    myself = " Emine Just Clicked $clickCount"
                }
        )
    }

}

@Composable
fun ChipsSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow(Modifier.fillMaxWidth()) {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        top = 10.dp,
                        end = 0.dp,
                        bottom = 10.dp
                    )
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        if (it == selectedChipIndex) {
                            ButtonBlue
                        } else {
                            DarkerButtonBlue
                        }
                    )
                    .padding(5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedChipIndex = it
                    }

            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }

}

@Composable
fun DailyThought(
    context: Context,
    color: Color = LightRed
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)

    ) {
        Column(verticalArrangement = Arrangement.Center) {

            Text(text = "Daily Thought", style = MaterialTheme.typography.h2)
            Text(
                text = "Mediation 3-10min",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
                .clickable {
                    Toast
                        .makeText(context, "Tadam !", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = " Play icon",
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)

            )
        }
    }
}


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainContent(
    cards: List<PhotosItem>
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            Text(text = "This is your list", style = MaterialTheme.typography.h1)

            LazyVerticalGrid(
                cells = GridCells.Adaptive(128.dp),

                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(cards.size) { index ->
                        PhotosItemView(item = cards[index], click = { click ->
                            Log.e("/D", " Row n° $click clicked")
                        })
                    }
                })
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PhotosItemView(
    item: PhotosItem,
    click: (Int) -> Unit
) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                isClicked = !isClicked
                click(item.id)
            },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .background(
                    if (isClicked) {
                        ButtonBlue
                    } else {
                        DarkerButtonBlue
                    }
                )
                .padding(15.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.url),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(text = item.title, style = MaterialTheme.typography.body2, color = TextWhite)
        }
    }
}

