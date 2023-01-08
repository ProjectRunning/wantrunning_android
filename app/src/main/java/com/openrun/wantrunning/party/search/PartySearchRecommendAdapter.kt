package com.openrun.wantrunning.party.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.ItemPartySearchRecommendBinding

class PartySearchRecommendAdapter:ListAdapter<Any, PartySearchRecommendAdapter.ViewHolder>(diffUtil){

    val checked = ArrayList<Any>()

    interface PartySearchRecommendItemClickListener {
        fun onPartySearchRecommendItemClick(item: Any)
        fun onStarClick(item: Any)
    }

    private var onClickListener : PartySearchRecommendItemClickListener? = null

    fun setPartySearchRecommendItemClickListener(listener: PartySearchRecommendItemClickListener) {
        onClickListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        onClickListener?.let { listener ->
            holder.bind(item, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_party_search_recommend, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPartySearchRecommendBinding.bind(itemView)

        fun bind(item: Any, onPartyListItemClickListener: PartySearchRecommendItemClickListener) {

            itemView.isClickable = true
            itemView.setOnClickListener {
                onPartyListItemClickListener.onPartySearchRecommendItemClick(item)
            }
            binding.flStar.setOnClickListener {
                onPartyListItemClickListener.onStarClick(item)
                if(checked.contains(item)) {
                    checked.remove(item)
                    binding.ivStar.visibility = View.INVISIBLE
                    binding.ivUnstar.visibility = View.VISIBLE
                } else {
                    checked.add(item)
                    binding.ivStar.visibility = View.VISIBLE
                    binding.ivUnstar.visibility = View.INVISIBLE
                }
            }
        }
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
    }
}