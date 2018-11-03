package ca.nick.redditcoroutines.data.remote

import ca.nick.redditcoroutines.data.RedditItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RedditService {

    companion object {
        const val BASE_URL = "http://www.reddit.com/"
    }

    @GET(".json")
    fun fetchFrontPage(): Deferred<List<RedditItem>>
}