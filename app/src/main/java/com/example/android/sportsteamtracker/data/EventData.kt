package com.example.android.sportsteamtracker.data

import com.squareup.moshi.Json

data class EventData(
    @Json(name = "event") val events: List<Event>
)