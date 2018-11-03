package ca.nick.redditcoroutines.ui

import android.os.Bundle
import ca.nick.redditcoroutines.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FrontPageFragment.create(), FrontPageFragment.TAG)
                .commit()
        }
    }
}
