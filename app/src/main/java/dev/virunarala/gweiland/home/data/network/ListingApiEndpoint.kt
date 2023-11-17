package dev.virunarala.gweiland.home.data.network

import dev.virunarala.gweiland.home.data.model.LatestListingsNetwork
import dev.virunarala.gweiland.home.data.model.MetadataNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListingApiEndpoint {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getLatestListings(@Query("sort") sortBy: String): Response<LatestListingsNetwork>

    @GET("v2/cryptocurrency/info")
    suspend fun getMetadata(@Query("id") id: String): Response<MetadataNetwork>
}