package ca.nick.redditcoroutines.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import ca.nick.redditcoroutines.data.local.Db
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun db(app: Application) =
        Room.databaseBuilder(app, Db::class.java, Db.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun redditItemDao(db: Db) = db.redditItemDao()

    @Singleton
    @Provides
    fun sharedPrefs(application: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

    @Singleton
    @Provides
    @StaleDataThreshold
    fun staleDataThreshold() = TimeUnit.SECONDS.toMillis(15)  // Short threshold for demo purposes
}