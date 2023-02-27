package pl.artushock.app.necoruweatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import pl.artushock.app.necoruweatherapp.R
import pl.artushock.app.necoruweatherapp.ui.theme.BlueLight

@Composable
@Preview(showBackground = true)
fun MainScreen() {
    Image(
        painter = painterResource(id = R.drawable.starsky),
        contentDescription = "background image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 6.dp, horizontal = 10.dp)
    ) {
        MainCard()
        TabLayout()
    }
}

@Composable
private fun MainCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = BlueLight,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "20 Jun 2023 13:00",
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.White
                )

                AsyncImage(
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdAT4AFIvGBd96752ySY9F9CZL-BGJVYf90Q&usqp=CAU",
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            }

            Text(
                text = "Warsaw, Poland",
                style = TextStyle(fontSize = 24.sp),
                color = Color.White
            )

            Text(
                text = "16°C",
                style = TextStyle(fontSize = 65.sp),
                color = Color.White
            )

            Text(
                text = "Sunny",
                style = TextStyle(fontSize = 16.sp),
                color = Color.White
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = "Search button",
                        tint = Color.White
                    )
                }

                Text(
                    text = "20°C / 12°C",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )


                IconButton(
                    onClick = {

                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_cloud_sync_24),
                        contentDescription = "Search button",
                        tint = Color.White
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(top = 6.dp)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = BlueLight,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text
                        )
                    }
                )
            }
        }

        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
        }
    }
}
