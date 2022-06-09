package com.example.android.sportsteamtracker.network

import com.example.android.sportsteamtracker.data.EventData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/50130162/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// use this one when needing to pull and display the JSON, the converter works well for it.
private val retrofit2 = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes the [getEvent] method
 */
interface ApiService {
    @GET("searchplayers.php")
    suspend fun getPlayerJson(
        @Query("p")
        playerName: String
    ):String

    @GET("searchteams.php")
    suspend fun getTeamJson(
        @Query("t")
        teamName: String
    ):String

    @GET("searchevents.php")
    suspend fun getEventJson(
        @Query("e")
        teamName: String
    ):String


    /**
     * Returns [EventData] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "searchevents.php" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("searchevents.php")
    suspend fun getEvent(
        @Query("e")
        teamName: String
    ): EventData
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object APIObject{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // again, this one is used for the display of the JSON only
    val retrofitServiceToSeeJson: ApiService by lazy {
        retrofit2.create(ApiService::class.java)
    }
}