package ca.nick.redditcoroutines.data.local

import android.content.SharedPreferences
import ca.nick.redditcoroutines.di.StaleDataThreshold
import ca.nick.redditcoroutines.utils.CurrentTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataCachingStrategy @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val currentTime: CurrentTime,
    @StaleDataThreshold
    private val staleDataThreshold: Long
) {

    companion object {
        const val KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY = "last_time_data_fetched_successfully"
    }

    fun isPersistedDataStale(): Boolean {
        val lastTimeDataFetchedSuccessfully =
            sharedPreferences.getLong(KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY, 0L)
        return (currentTime.timeInMillis() - lastTimeDataFetchedSuccessfully) >= staleDataThreshold
    }

    fun setLastTimeDataFetchedSuccessfully() {
        sharedPreferences.edit()
            .putLong(KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY, currentTime.timeInMillis())
            .apply()
    }
}