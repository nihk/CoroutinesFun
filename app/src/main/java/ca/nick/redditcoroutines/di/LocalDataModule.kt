package ca.nick.redditcoroutines.di

import android.app.Application
import androidx.room.Room
import ca.nick.redditcoroutines.data.local.Db
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun db(app: Application) =
        Room.databaseBuilder(app, Db::class.java, Db.DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun redditItemDao(db: Db) = db.redditItemDao()
}