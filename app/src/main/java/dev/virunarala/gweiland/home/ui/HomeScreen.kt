package dev.virunarala.gweiland.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.virunarala.gweiland.R
import dev.virunarala.gweiland.common.model.NetworkResult
import dev.virunarala.gweiland.home.data.model.ListingUiModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val context = LocalContext.current
    val listingsState = viewModel.latestListings.collectAsStateWithLifecycle()
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName = "loading_anim.json"))
    val animationProgress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LaunchedEffect(listingsState.value) {
        when(listingsState.value) {
            is NetworkResult.NotInitialized -> {
                // Do nothing
            }

            is NetworkResult.Error -> {
                val errorMessage = listingsState.value.getErrorMessage()
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
            }

            is NetworkResult.NoInternet -> {
                val errorMessage = context.getString(R.string.no_internet)
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {
                //Do nothing
            }
        }
    }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(
            start = dimensionResource(id = R.dimen.padding_medium),
            end = dimensionResource(id = R.dimen.padding_medium)
        )
    ) {

        Column {
                TopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                FilterItems(modifier = Modifier
                    .fillMaxWidth(),
                    onSortClick = { sortOption ->
                        viewModel.getLatestListings(sortOption)
                    }
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                Row {
                    Text(
                        text = stringResource(id = R.string.cryptocurrency),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier
                        .width(dimensionResource(id = R.dimen.padding_medium)))

                    Text(
                        text = stringResource(id = R.string.nft),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }



                if(listingsState.value is NetworkResult.Success<*>) {
                    val latestListings = ((listingsState.value) as NetworkResult.Success<*>).data as List<ListingUiModel>

                    TopListingBanner(
                        listing = latestListings[0],
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_medium),
                                bottom = dimensionResource(id = R.dimen.padding_medium)
                            )
                    )

                    LazyColumn {

                        item {

                        }

                        items(latestListings) {
                            ListingItem(listing = it)
                        }
                    }
                }


            }

        if(listingsState.value is NetworkResult.Loading) {
            LottieAnimation(
                composition = composition,
                progress = { animationProgress },
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .align(Alignment.Center)
            )
        }
    }


}