package dev.virunarala.gweiland.home.data

import dev.virunarala.gweiland.common.model.NetworkResult
import dev.virunarala.gweiland.common.model.SortOptions
import dev.virunarala.gweiland.home.data.model.Listing
import dev.virunarala.gweiland.home.data.model.ListingUiModel
import dev.virunarala.gweiland.home.data.network.ListingApiEndpoint
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class ListingRepository @Inject constructor(private val apiEndpoint: ListingApiEndpoint) {

    suspend fun getLatestListings(sortOption: SortOptions): NetworkResult {
        return try {
            val apiResponse = apiEndpoint.getLatestListings(sortOption.id)
            if(apiResponse.isSuccessful) {
                val responseBody = apiResponse.body()
                val listings = responseBody!!.data as List<Listing>
                return getMetadata(listings)
            } else {
                NetworkResult.Error("Server Error: ${apiResponse.message()}")
            }
        } catch (e: UnknownHostException) {
            NetworkResult.NoInternet
        } catch (e: Exception) {
            NetworkResult.Error("Error: ${e.message}")
        }
    }

    private suspend fun getMetadata(listings: List<Listing>): NetworkResult {
        val listingIds = listings.map {
            it.id
        }
        val idParam = listingIds.joinToString(separator = ",")
        return try {
            val apiResponse = apiEndpoint.getMetadata(idParam)
            if(apiResponse.isSuccessful) {
                val responseBody = apiResponse.body()

                val metadatas = responseBody!!.data
                val listingsUi = mutableListOf<ListingUiModel>()
                metadatas.forEach { id,metadata ->
                    val listing = listings.find { it.id == id.toInt() }!!
                    listingsUi.add(
                        ListingUiModel(
                            listing.id.toString(),
                            listing.name,
                            listing.symbol,
                            listing.getRoundedUsdPrice(),
                            listing.getDayPercentChange(),
                            metadata.logo
                        )
                    )
                }
                NetworkResult.Success(listingsUi)
            } else {
                NetworkResult.Error("Server Error: ${apiResponse.message()}")
            }
        } catch (e: UnknownHostException) {
            NetworkResult.NoInternet
        } catch (e: Exception) {
            NetworkResult.Error("Error: ${e.message}")
        }
    }
}