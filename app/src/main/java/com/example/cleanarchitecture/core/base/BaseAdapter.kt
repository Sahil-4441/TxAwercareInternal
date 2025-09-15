package com.example.cleanarchitecture.core.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, DB : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder>() {
    open lateinit var binding: DB
    private var items = emptyList<T>()

    private var itemClickListener: OnItemClick? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun onBind(binding : DB, item : T, position: Int)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutRes(),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBind(binding, items[position], position)
    }

    fun submitList(newItems: List<T>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = items.size
            override fun getNewListSize() = newItems.size

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                // TODO: adjust according to your model (e.g., compare IDs)
                return items[oldPos] == newItems[newPos]
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
                return items[oldPos] == newItems[newPos]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClick {
        fun onItemClick(vararg items: Any)
    }

    fun onItemClick(vararg items: Any) {
        itemClickListener?.onItemClick(*items)
    }

    fun setOnItemClickListener(onItemClick: OnItemClick) {
        itemClickListener = onItemClick
    }

}
