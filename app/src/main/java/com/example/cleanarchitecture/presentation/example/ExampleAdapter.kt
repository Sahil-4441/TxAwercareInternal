package com.example.cleanarchitecture.presentation.example

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.base.BaseAdapter
import com.example.cleanarchitecture.databinding.ItemTodoBinding
import com.example.cleanarchitecture.domain.model.Todo

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

