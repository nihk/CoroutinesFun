package ca.nick.redditcoroutines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.nick.redditcoroutines.data.RedditItem
import ca.nick.redditcoroutines.data.Repository
import ca.nick.redditcoroutines.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class FrontPageViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _progressBarState = MutableLiveData<Boolean>()
    val progressBarState: LiveData<Boolean> get() = _progressBarState
    private val _errorMessage = MutableLiveData<Event<String?>>()
    val errorMessage: LiveData<Event<String?>> get() = _errorMessage
    private val _redditItems = MediatorLiveData<List<RedditItem>>()
    val redditItems: LiveData<List<RedditItem>> get() = _redditItems

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    init {
        _redditItems.addSource(repository.redditItems) {
            _redditItems.value = it
        }
    }

    fun fetchRedditItems() {
        launchDataLoad {
            repository.fetchRedditItems()
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit) = uiScope.launch {
        try {
            _progressBarState.value = true
            block()
            setLastTimeDataFetchedSuccessfully()
        } catch (error: Exception) {
            _errorMessage.value = Event(error.message)
        } finally {
            _progressBarState.value = false
        }
    }

    fun isPersistedDataStale() = repository.isPersistedDataStale()

    fun setLastTimeDataFetchedSuccessfully() = repository.setLastTimeDataFetchedSuccessfully()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}