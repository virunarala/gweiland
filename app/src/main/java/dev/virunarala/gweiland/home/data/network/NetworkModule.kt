package dev.virunarala.gweiland.home.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.virunarala.gweiland.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private val BASE_URL = BuildConfig.BASE_URL
private val API_KEY = BuildConfig.API_KEY

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headerAdder = object: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("X-CMC_PRO_API_KEY",API_KEY)
                .build()
            return chain.proceed(request)
        }
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
        addInterceptor(headerAdder)
    }.build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideApiEndpoint(): ListingApiEndpoint {
        val apiEndpoint: ListingApiEndpoint by lazy {
            retrofit.create(ListingApiEndpoint::class.java)
        }
        return apiEndpoint
    }
}