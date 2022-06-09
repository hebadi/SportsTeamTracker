package com.example.android.sportsteamtracker.data

import com.squareup.moshi.Json

data class Event(
    val idEvent: String,
    val dateEvent: String,
    val strEvent: String,
    val intHomeScore: Int,
    val intAwayScore: Int,
    val strResult: String,
    @Json(name = "strThumb") val imgSrcUrl: String,
//    val idHomeTeam: String?,
//    val strHomeTeam: String?,
//    val idAwayTeam: String?,
//    val strAwayTeam: String?,
//    val strLeague: String?,
)

/*
    val strSeason: String,
    val strSport: String,
    val strSquare: Any,
    val strStatus: String,
    val strTVStation: Any,
    val strTime: String,
    val strTimeLocal: String,
    val strTimestamp: String,
    val strTweet1: String,
    val strTweet2: String,
    val strTweet3: String,
    val strVenue: String,
    val strVideo: String
 */