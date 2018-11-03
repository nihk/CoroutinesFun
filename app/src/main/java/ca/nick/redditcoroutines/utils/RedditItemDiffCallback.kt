package ca.nick.redditcoroutines.utils

import androidx.recyclerview.widget.DiffUtil
import ca.nick.redditcoroutines.data.RedditItem


object RedditItemDiffCallback : DiffUtil.ItemCallback<RedditItem>() {

    override fun areItemsTheSame(oldItem: RedditItem, newItem: RedditItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RedditItem, newItem: RedditItem) =
        oldItem == newItem
}