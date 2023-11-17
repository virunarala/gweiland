package dev.virunarala.gweiland.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.virunarala.gweiland.common.model.NetworkResult
import dev.virunarala.gweiland.common.model.SortOptions
import dev.virunarala.gweiland.home.data.ListingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val listingRepository: ListingRepository): ViewModel() {

    private val _latestListings = MutableStateFlow<NetworkResult>(NetworkResult.NotInitialized)
    val latestListings: StateFlow<NetworkResult>
        get() = _latestListings

    init {
        getLatestListings(SortOptions.MarketCap)
    }

    fun getLatestListings(sortOption: SortOptions) {
        _latestListings.value = NetworkResult.Loading
        viewModelScope.launch {
            _latestListings.value = listingRepository.getLatestListings(sortOption)
        }
    }
}