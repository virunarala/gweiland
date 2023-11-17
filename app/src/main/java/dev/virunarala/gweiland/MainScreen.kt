package dev.virunarala.gweiland

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.virunarala.gweiland.home.ui.HomeScreen
import dev.virunarala.gweiland.home.ui.HomeViewModel

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomNavBar(modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium)))
        }
    ) { padding ->
        val homeViewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(Modifier.padding(padding),homeViewModel)
    }
}