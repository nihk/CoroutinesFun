package ca.nick.redditcoroutines.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.nick.redditcoroutines.data.RedditItem

@Dao
interface RedditItemDao : BaseDao<RedditItem> {

    @Query("SELECT * FROM ${RedditItem.TABLE_NAME}")
    fun getRedditItems(): LiveData<List<RedditItem>>

    @Query("DELETE FROM ${RedditItem.TABLE_NAME}")
    fun deleteAll()
}