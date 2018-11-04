package ca.nick.redditcoroutines.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.nick.redditcoroutines.data.RedditItem.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RedditItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val thumbnailUrl: String
) {

    companion object {
        const val TABLE_NAME = "reddit_items"
        const val SELF_THUMBNAIL = "self"
    }

    fun isSelfPost() = thumbnailUrl == SELF_THUMBNAIL
}