package ca.nick.redditcoroutines.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ca.nick.redditcoroutines.data.RedditItem
import kotlinx.android.synthetic.main.item_reddit.view.*

class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(redditItem: RedditItem) {
        itemView.title.text = redditItem.title
    }
}