package ca.nick.redditcoroutines.di

import ca.nick.redditcoroutines.data.RedditJsonAdapter
import ca.nick.redditcoroutines.data.remote.RedditService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun redditJsonAdapter() = RedditJsonAdapter

    @Singleton
    @Provides
    fun moshi(redditItemAdapter: RedditJsonAdapter): Moshi =
        Moshi.Builder()
            .add(redditItemAdapter)
            .build()

    @Singleton
    @Provides
    fun redditService(moshi: Moshi): RedditService =
        Retrofit.Builder()
            .baseUrl(RedditService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RedditService::class.java)

}