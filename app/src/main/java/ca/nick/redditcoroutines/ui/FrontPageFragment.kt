package ca.nick.redditcoroutines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.nick.redditcoroutines.R
import ca.nick.redditcoroutines.data.RedditItem
import ca.nick.redditcoroutines.vm.FrontPageViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_front_page.*

class FrontPageFragment : BaseFragment() {

    private lateinit var viewModel: FrontPageViewModel
    private val adapter = RedditItemListAdapter()

    companion object {
        val TAG: String = FrontPageFragment::class.java.simpleName
        fun create() = FrontPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_front_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reddit_items.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FrontPageViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
                // Fallback to locally cached data, if any
                viewModel.redditItems.value?.let { items ->
                    submitList(items)
                }
            }
        })

        viewModel.progressBarState.observe(this, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.redditItems.observe(this, Observer {
            if (it.isEmpty() || (viewModel.isPersistedDataStale() && savedInstanceState == null)) {
                viewModel.fetchRedditItems()
            } else {
                submitList(it)
            }
        })
    }

    private fun submitList(items: List<RedditItem>) {
        adapter.submitList(items)
    }
}