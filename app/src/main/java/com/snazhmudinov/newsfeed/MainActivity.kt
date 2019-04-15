package com.snazhmudinov.newsfeed

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.snazhmudinov.newsfeed.adapter.ArticlesAdapter
import com.snazhmudinov.newsfeed.event.ArticleClickedEvent
import com.snazhmudinov.newsfeed.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLS_PORT = 2
        const val COLS_LAND = 3
    }

    private val eventBus: EventBus by inject()
    private val articlesViewModel: ArticlesViewModel by viewModel()

    private val newsAdapter by lazy {
        ArticlesAdapter(this, eventBus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(newsRecyclerView) {
            val orientation = resources.configuration.orientation
            val cols = if (orientation == Configuration.ORIENTATION_PORTRAIT) COLS_PORT else COLS_LAND
            layoutManager = GridLayoutManager(this@MainActivity, cols).apply {
                this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 7 == 0) cols else 1
                    }
                }
            }
            adapter = newsAdapter
        }

        articlesViewModel.articles.observe(this, Observer {
            newsAdapter.submitList(it)
        })
    }

    override fun onStart() {
        super.onStart()
        eventBus.register(this)
    }

    override fun onStop() {
        eventBus.unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onArticleClicked(event: ArticleClickedEvent) {
        val uri = Uri.parse(event.article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
