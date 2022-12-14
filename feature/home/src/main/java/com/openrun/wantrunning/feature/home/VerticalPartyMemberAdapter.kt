package com.openrun.wantrunning.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VerticalPartyMemberAdapter : RecyclerView.Adapter<VerticalPartyMemberAdapter.ViewHolder>() {

    interface OnPartyMemberItemClickListener {
        fun onPartyMemberItemClick(item: Any)
    }

    private var onClickListener : VerticalPartyMemberAdapter.OnPartyMemberItemClickListener? = null

    var data =  listOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnPartyMemberItemClickListener(listener: OnPartyMemberItemClickListener) {
        onClickListener = listener
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VerticalPartyMemberAdapter.ViewHolder, position: Int) {
        val item = data[position]
        onClickListener?.let { listener ->
            holder.bind(item, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalPartyMemberAdapter.ViewHolder {

        return VerticalPartyMemberAdapter.ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: Any, onPartyMemberItemClickListener: VerticalPartyMemberAdapter.OnPartyMemberItemClickListener) {
            itemView.isClickable = true
            itemView.setOnClickListener {
                onPartyMemberItemClickListener.onPartyMemberItemClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_party_member_vertical, parent, false)

                return ViewHolder(view)
            }
        }
    }
}