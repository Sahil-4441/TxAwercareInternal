package com.example.avercare.presentation.example

import com.example.avercare.R
import com.example.avercare.core.base.BaseAdapter
import com.example.avercare.databinding.ItemTodoBinding
import com.example.avercare.domain.model.Todo

class ExampleAdapter: BaseAdapter<Todo, ItemTodoBinding>() {

    override fun getLayoutRes(): Int = R.layout.item_todo

    override fun onBind(
        binding: ItemTodoBinding,
        item: Todo,
        position: Int
    ) {
        binding.model = item
    }


}

