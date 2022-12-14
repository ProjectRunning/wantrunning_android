package com.openrun.wantrunning.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PartyMemberAdapter : RecyclerView.Adapter<PartyMemberAdapter.ViewHolder>() {

    interface OnPartyMemberItemClickListener {
        fun onPartyMemberItemClick(item: Any)
    }

    private var onClickListener : PartyMemberAdapter.OnPartyMemberItemClickListener? = null

    var data =  listOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnPartyMemberItemClickListener(listener: OnPartyMemberItemClickListener) {
        onClickListener = listener
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PartyMemberAdapter.ViewHolder, position: Int) {
        val item = data[position]
        onClickListener?.let { listener ->
            holder.bind(item, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyMemberAdapter.ViewHolder {

        return PartyMemberAdapter.ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: Any, onPartyMemberItemClickListener: PartyMemberAdapter.OnPartyMemberItemClickListener) {
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