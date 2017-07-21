package pl.hypeapp.episodie.ui.features.top.adapter

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_top_list.view.*
import pl.hypeapp.episodie.R
import pl.hypeapp.episodie.adapter.ViewType
import pl.hypeapp.episodie.adapter.ViewTypeDelegateAdapter
import pl.hypeapp.episodie.extensions.inflate
import pl.hypeapp.episodie.extensions.loadImage
import pl.hypeapp.episodie.extensions.setTvShowRuntime
import pl.hypeapp.episodie.ui.viewmodel.TvShowViewModel

@SuppressLint("SetTextI18n")
class TopListDelegateAdapter(val onViewSelectedLister: TopListOnViewSelectedListener) : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TopListViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder.setIsRecyclable(false)
        holder as TopListDelegateAdapter.TopListViewHolder
        holder.bind(item as TvShowViewModel)
    }

    inner class TopListViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.item_top_list)) {


        fun bind(item: TvShowViewModel) = with(itemView) {
            if (adapterPosition % 2 == 0) {
                coordinator_layout_item_top_list.setBackgroundColor(ContextCompat.getColor(context, R.color.item_top_list_background))
            }
            if (adapterPosition < 9) {
                text_view_top_list_position.text = "0${(adapterPosition + 1)}"
            } else {
                text_view_top_list_position.text = "${adapterPosition + 1}"
            }
            text_view_item_top_list_title.text = item.tvShow?.name
            text_view_item_top_list_runtime.setTvShowRuntime(item.tvShow?.fullRuntime)
            image_view_item_top_list_cover.loadImage(item.tvShow?.imageMedium)
            super.itemView.setOnClickListener { onViewSelectedLister.onItemSelected() }
            image_view_item_top_list_diamond.setOnClickListener {
                onViewSelectedLister.onAddToWatched(item.tvShow?.id!!)
            }
        }
    }

}
