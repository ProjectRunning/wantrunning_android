package com.openrun.wantrunning.party.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openrun.wantrunning.R

class PartyMemberAdapter : RecyclerView.Adapter<PartyMemberAdapter.ViewHolder>() {

    interface OnPartyMemberItemClickListener {
        fun onPartyMemberItemClick(item: Any)
    }

    private var onClickListener : OnPartyMemberItemClickListener? = null

    var data =  listOf<Any>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
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

        fun bind(item: Any, onPartyMemberItemClickListener: OnPartyMemberItemClickListener) {
            itemView.isClickable = true
            itemView.setOnClickListener {
                onPartyMemberItemClickListener.onPartyMemberItemClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_party_member, parent, false)

                return ViewHolder(view)
            }
        }
    }
}