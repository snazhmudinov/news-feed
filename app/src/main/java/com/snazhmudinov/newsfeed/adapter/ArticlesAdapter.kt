package com.snazhmudinov.newsfeed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.snazhmudinov.newsfeed.databinding.ArticleViewHolderBinding
import com.snazhmudinov.newsfeed.event.ArticleClickedEvent
import com.snazhmudinov.newsfeed.model.Article
import org.greenrobot.eventbus.EventBus

class ArticlesAdapter(context: Context, private val eventBus: EventBus) :
    PagedListAdapter<Article, ArticleViewHolder>(ArticleItemDiff()) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleViewHolderBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            article.showContent = position % 7 == 0
            holder.bind(article)

            holder.itemView.setOnClickListener {
                eventBus.post(ArticleClickedEvent(article))
            }
        }
    }

}


class ArticleViewHolder(private val binding: ArticleViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.article = article
    }
}

class ArticleItemDiff : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}