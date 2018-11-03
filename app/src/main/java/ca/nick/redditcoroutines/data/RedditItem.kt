package ca.nick.redditcoroutines.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.nick.redditcoroutines.data.RedditItem.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RedditItem(
    @PrimaryKey
    val id: String,
    val title: String
) {
    companion object {
        const val TABLE_NAME = "reddit_items"
    }
}