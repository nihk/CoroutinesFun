package ca.nick.redditcoroutines.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

object RedditJsonAdapter {

    data class RedditJson(val data: Data1)
    data class Data1(val children: List<Child>)
    data class Child(val data: Data2)
    data class Data2(val id: String, val title: String)

    @FromJson
    fun fromJson(redditJson: RedditJson): List<RedditItem> = redditJson.data.children.map { child ->
        val data2 = child.data
        val id = data2.id
        val title = data2.title
        RedditItem(id, title)
    }.toCollection(mutableListOf())

    @ToJson
    fun toJson(redditItems: List<RedditItem>): RedditJson {
        val children = mutableListOf<Child>()
        redditItems.map { redditItem ->
            val data2 = Data2(redditItem.id, redditItem.title)
            Child(data2)
        }.toCollection(children)
        return RedditJson(Data1(children))
    }
}