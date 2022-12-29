package com.openrun.wantrunning.party.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.openrun.wantrunning.R

class PartySearchRecommendAdapter : RecyclerView.Adapter<PartySearchRecommendAdapter.ViewHolder>(){

    private var data: List<Any> = ArrayList()
    val checked = ArrayList<Any>()

    interface PartySearchRecommendItemClickListener {
        fun onPartySearchRecommendItemClick(item: Any)
        fun onStarClick(item: Any)
    }

    private var onClickListener : PartySearchRecommendItemClickListener? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Any>) {
        data = list
        notifyDataSetChanged()
    }

    fun setPartySearchRecommendItemClickListener(listener: PartySearchRecommendItemClickListener) {
        onClickListener = listener
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

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

        fun bind(item: Any, onPartyListItemClickListener: PartySearchRecommendItemClickListener) {

            itemView.isClickable = true
            itemView.setOnClickListener {
                onPartyListItemClickListener.onPartySearchRecommendItemClick(item)
            }
            val starView = itemView.findViewById<FrameLayout>(R.id.fl_star)
            starView.setOnClickListener {
                onPartyListItemClickListener.onStarClick(item)
                if(checked.contains(item)) {
                    checked.remove(item)
                    it.findViewById<ImageView>(R.id.iv_star).visibility = View.INVISIBLE
                    it.findViewById<ImageView>(R.id.iv_unstar).visibility = View.VISIBLE
                } else {
                    checked.add(item)
                    it.findViewById<ImageView>(R.id.iv_unstar).visibility = View.INVISIBLE
                    it.findViewById<ImageView>(R.id.iv_star).visibility = View.VISIBLE
                }
            }
        }
    }
}