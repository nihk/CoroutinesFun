package ca.nick.redditcoroutines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.nick.redditcoroutines.data.RedditItem

@Database(entities = [RedditItem::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {

    abstract fun redditItemDao(): RedditItemDao

    companion object {
        const val DATABASE_NAME = "reddit_coroutines.db"
    }
}