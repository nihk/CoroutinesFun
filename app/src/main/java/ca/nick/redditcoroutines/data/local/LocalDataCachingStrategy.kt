package ca.nick.redditcoroutines.data.local

import android.content.SharedPreferences
import ca.nick.redditcoroutines.di.StaleDataThreshold
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataCachingStrategy @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val calendar: Calendar,
    @StaleDataThreshold
    private val staleDataThreshold: Long
) {

    companion object {
        const val KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY = "last_time_data_fetched_successfully"
    }

    fun isPersistedDataStale(): Boolean {
        val currentTime = calendar.timeInMillis
        val lastTimeDataFetchedSuccessfully =
            sharedPreferences.getLong(KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY, 0L)
        return (currentTime - lastTimeDataFetchedSuccessfully) >= staleDataThreshold
    }

    fun setLastTimeDataFetchedSuccessfully() {
        sharedPreferences.edit()
            .putLong(KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY, calendar.timeInMillis)
            .apply()
    }
}