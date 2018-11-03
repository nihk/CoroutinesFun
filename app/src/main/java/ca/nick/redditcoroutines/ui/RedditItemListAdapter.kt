package ca.nick.redditcoroutines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.nick.redditcoroutines.R
import ca.nick.redditcoroutines.data.RedditItem
import ca.nick.redditcoroutines.utils.RedditItemDiffCallback

class RedditItemListAdapter : ListAdapter<RedditItem, RedditItemViewHolder>(RedditItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reddit, parent, false)
            .let { RedditItemViewHolder(it) }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}