package com.innobuzz.postapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innobuzz.postapp.R
import com.innobuzz.postapp.model.DataPost
import kotlinx.android.synthetic.main.post_item_card.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var listDataPost: List<DataPost>? = null

    fun setListData(listDataPost: List<DataPost>?) {
        this.listDataPost = listDataPost
    }

    // click listener
    var onDataUserClickListener: ((DataPost) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_item_card, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataUser = listDataPost?.get(position)
        holder.itemView.setOnClickListener {
            if (dataUser != null) {
                onDataUserClickListener?.invoke(dataUser)
            }
            true
        }
        holder.bind(listDataPost?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listDataPost == null) return 0
        return listDataPost?.size!!
    }

    class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val userIdTextView = view.userIdTextView

        fun bind(dataPost: DataPost) {
            userIdTextView.text = dataPost.title.toString()
        }
    }

    interface OnDataUserClickListener {
        fun onDataUserClick(dataPost: DataPost)
    }
}