package com.example.android.sportsteamtracker.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.sportsteamtracker.data.Event
import com.example.android.sportsteamtracker.network.APIObject
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "OverviewViewModel"

enum class ApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    // The internal MutableLiveData that stores the status of the most recent request
    private val _events = MutableLiveData<List<Event>>()

    // The external immutable LiveData for the request status
    val events: LiveData<List<Event>> = _events

    /**
     * Gets event information from the API Retrofit service and updates the
     * [Event] [List] [LiveData].
     */
    fun getEventInfo(teamName: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _events.value = APIObject.retrofitService.getEvent(teamName).events
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _events.value = listOf()
                // we're clearing the recycler view in case it fails
            }
        }
    }
}
