package com.example.retrofitmyproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmyproject.databinding.ItemPostBinding

class Adapter(
    var list: List<Result>? = null,
    val itemClickListener: RecyclerViewItemClick? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemPostBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_post,
                parent,
                false
            )
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as FilmViewHolder
        viewHolder.initContent(list?.get(position))
    }

    inner class FilmViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initContent(data: Result?) {
            binding.data = data
            binding.executePendingBindings()

            binding.clMain.setOnClickListener {
                itemClickListener?.itemClick(adapterPosition, data!!)
            }
        }
    }

    fun clearAll() {
        (list as? ArrayList<Result>)?.clear()
        notifyDataSetChanged()
    }

    interface RecyclerViewItemClick {
        fun itemClick(position: Int, item: Result)
    }
}

