package com.example.avercare.presentation.verification

import com.example.avercare.R
import com.example.avercare.core.base.BaseAdapter
import com.example.avercare.core.base.BaseViewHolder
import com.example.avercare.databinding.AdapterVerificationBinding

class VerificationAdapter(private var listener: VerificationListener,var mList: MutableList<VerificationModel>) :
    BaseAdapter<AdapterVerificationBinding>() {

    override fun getLayoutRes(): Int = R.layout.adapter_verification

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding = holder.binding as AdapterVerificationBinding
        binding.item = mList[position]
        binding.mainLayout.setOnClickListener {
            listener.onItemClick(position = position, data = mList[position])
        }
    }

    interface VerificationListener {
        fun onItemClick(position: Int, data: VerificationModel)
    }
}