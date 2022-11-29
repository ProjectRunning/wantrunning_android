package com.openrun.wantrunning.feature.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PartyListAdapter : RecyclerView.Adapter<PartyListAdapter.ViewHolder>(){

    interface OnPartyListItemClickListener {
        fun onPartyListItemClick(item: Any)
    }

    private var onClickListener : OnPartyListItemClickListener? = null

    var data =  listOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnPartyListItemClickListener(listener: OnPartyListItemClickListener) {
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

        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: Any, onPartyListItemClickListener: OnPartyListItemClickListener) {
            itemView.isClickable = true
            itemView.setOnClickListener {
                onPartyListItemClickListener.onPartyListItemClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_running_party, parent, false)

                return ViewHolder(view)
            }
        }
    }
}