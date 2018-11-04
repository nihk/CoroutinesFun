package ca.nick.redditcoroutines.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ca.nick.redditcoroutines.data.local.LocalDataCachingStrategy
import ca.nick.redditcoroutines.data.local.RedditItemDao
import ca.nick.redditcoroutines.data.remote.RedditService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val redditService: RedditService,
    private val redditItemDao: RedditItemDao,
    private val localDataCachingStrategy: LocalDataCachingStrategy
) {

    private val _redditItems = MediatorLiveData<List<RedditItem>>()
    val redditItems: LiveData<List<RedditItem>> get() = _redditItems

    init {
        _redditItems.addSource(redditItemDao.getRedditItems()) {
            _redditItems.value = it
        }
    }

    companion object {
        const val KEY_LAST_TIME_DATA_FETCHED_SUCCESSFULLY = "last_time_data_fetched_successfully"
    }

    suspend fun fetchRedditItems() {
        withContext(Dispatchers.IO) {
            val redditItems = redditService.fetchFrontPage().await()
            redditItemDao.insertEntities(redditItems)
        }
    }

    fun isPersistedDataStale() = localDataCachingStrategy.isPersistedDataStale()

    fun setLastTimeDataFetchedSuccessfully() = localDataCachingStrategy.setLastTimeDataFetchedSuccessfully()
}